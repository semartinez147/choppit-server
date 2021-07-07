package com.semartinez.choppit.service;

import com.semartinez.choppit.model.controller.exception.ConnectionFailureException;
import com.semartinez.choppit.model.controller.exception.TooManyMatchesException;
import com.semartinez.choppit.model.controller.exception.ZeroMatchesException;
import com.semartinez.choppit.model.entity.AssemblyRecipe;
import com.semartinez.choppit.model.entity.Ingredient;
import com.semartinez.choppit.model.entity.Step;
import com.semartinez.choppit.model.entity.Unit;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class JsoupMachine {

  // TODO add any new measurement enum values to the parentheses here.  Extract String to resource.
  public static final String MAGIC_INGREDIENT_REGEX = "^([\\d\\W]*)\\s(tsp|teaspoon|tbsp|tablespoon|oz|ounce|c|cup|lb|pound*)*s??\\b(.*)";
  private static final int NETWORK_THREAD_COUNT = 10;

  private List<String> listRawIngredients = new ArrayList<>();
  private List<String> listInstructions = new ArrayList<>();
  private final List<Step> steps = new ArrayList<>();
  private final List<Ingredient> ingredients = new ArrayList<>();
  private AssemblyRecipe assemblyRecipe;
  private final Executor networkPool;
  private Document document;

  private final Logger machineLogger = Logger.getLogger("machine logger");


  JsoupMachine() {
    networkPool = Executors.newFixedThreadPool(NETWORK_THREAD_COUNT);
  }

  public static JsoupMachine getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public Completable connect(String url) {
    return Completable.fromAction(() -> {
      document = null;
      try {
        document = Jsoup.connect(url).get();
      } catch (MalformedURLException e) {
        throw new ConnectionFailureException("Not a valid link");
      } catch (HttpStatusException e) {
        throw new ConnectionFailureException("There was a problem with the website.");
      } catch (IOException e) {
        throw new ConnectionFailureException("An unknown error occurred.  Please try again.");
      }
    })
        .subscribeOn(Schedulers.from(networkPool))
        .doOnError((e) -> machineLogger.severe(e.getMessage()));
  }


  public AssemblyRecipe generateStrings(String url, boolean wantHtml) {
    connect(url).blockingAwait();
    assert document != null : "null document";
    assemblyRecipe = new AssemblyRecipe();
    assemblyRecipe.setUrl(document.location());
    assemblyRecipe.setTitle(document.title());
    prepare(wantHtml).subscribeOn(Schedulers.computation()).doOnSuccess(assemblyRecipe::setReduction)
        .blockingSubscribe();
    machineLogger.info(assemblyRecipe.getReduction().toString());
    return assemblyRecipe;
  }

  public Single<List<String>> prepare(boolean wantHtml) {
    return Single.fromCallable(new TrimTheFat(wantHtml)).subscribeOn(Schedulers.computation());
  }

  public Optional<AssemblyRecipe> process(String ingredient, String instruction) {
    return Optional.of(assemble(ingredient, instruction));
  }

  private AssemblyRecipe assemble(String ingredient, String instruction) {
    //TODO Error handling: test getClass errors for 0 or >1 result.

    RunIngredients i = new RunIngredients(ingredient);
    RunSteps s = new RunSteps(instruction);

    Thread iThread = new Thread(i, "iThread");
    Thread sThread = new Thread(s, "sThread");

    iThread.start();
    sThread.start();

    try {
      iThread.join();
      sThread.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }

    assemblyRecipe.setIngredients(ingredients);
    assemblyRecipe.setSteps(steps);
    assemblyRecipe.setRecipeId(0);

    return assemblyRecipe;
  }

  private class TrimTheFat implements Callable<List<String>> {

    private final boolean wantHtml;

    private TrimTheFat(boolean wantHtml) {
      this.wantHtml = wantHtml;
    }

    @Override
    public List<String> call() throws Exception {
      if (document == null) {
        throw new NullPointerException();
        // Call reconnect if the breakpoint is ever triggered.
      }
      List<String> strings;
      document.filter(new Strainer());
      if (wantHtml) {
        strings = document.getAllElements()
            .parallelStream().filter(el -> el.ownText().length() > 0).map(Element::toString)
            .distinct()
            .collect(Collectors.toList());
      } else {
        strings = document.getAllElements().parallelStream().map(Element::ownText)
            .distinct()
            .collect(Collectors.toList());
      }
      if (strings.isEmpty()) {
        throw new ZeroMatchesException();
      }

      return strings;
    }
  }

  private class RunIngredients implements Runnable {

    String ingredient;

    public RunIngredients(String ingredient) {
      this.ingredient = ingredient;
    }

    @Override
    public void run() {
      String ingredientClass = getKlass(ingredient, "ingredient");
      listRawIngredients = getClassContents(ingredientClass); // list all ingredients
      buildIngredients();
    }
  }

  private class RunSteps implements Runnable {

    String instruction;

    public RunSteps(String instruction) {
      this.instruction = instruction;
    }

    @Override
    public void run() {
      String instructionClass = getKlass(instruction, "step");
      listInstructions = getClassContents(instructionClass); // list all ingredients
      buildSteps();
    }
  }

  /**
   * This method searches the body of the HTML {@link Document} for the text of the given {@link
   * String}, and returns the "class" HTML attribute of the first element containing that text. Runs
   * once on each text parameter. This method needs to be improved to handle no or multiple matches
   * and request additional input from the user.
   *
   * @param text is either the ingredient or instruction text input by the user
   * @return the HTML "class" attribute enclosing the input {@link String}.
   */
  protected String getKlass(String text, String type) {
    Elements e = document.select(String.format("*:containsOwn(%s)", text));
    if (e.size() == 1) {
      return e.get(0).attr("class");
    } else {
      throw e.isEmpty() ? new ZeroMatchesException(type) : new TooManyMatchesException(type);
    }
  }

  /**
   * this method takes an HTML "class" attribute and compiles as {@link Element}s the contents of
   * each matching HTML element.  Runs once on each text parameter.
   *
   * @param klass the values returned by {@link #getKlass(String, String)}
   * @return the contents of each HTML element matching the provided "class" attribute as a {@link
   * String}.
   */
  protected List<String> getClassContents(String klass) {
    Elements e = document.getElementsByClass(klass);
    return e.eachText();
  }

  protected void buildSteps() {
    for (int i = 0, j = 1; i < this.listInstructions.size(); i++, j++) {
      Step step = new Step();
      step.setRecipeOrder(j);
      step.setInstructions(this.listInstructions.get(i));
      steps.add(step);
    }
  }


  protected void buildIngredients() {
    Pattern pattern = Pattern.compile(MAGIC_INGREDIENT_REGEX);
    List<String> rawIngredients = this.listRawIngredients;
    for (String rawIngredient : rawIngredients) {
      Matcher matcher = pattern.matcher(rawIngredient);
      Ingredient ingredient = new Ingredient();
      if (matcher.find()) {
        // group 1 = measurement | group 2 = unit | group 3 = name
        ingredient.setQuantity(Objects.requireNonNull(matcher.group(1)));
        ingredient.setUnit(Unit.toUnit(matcher.group(2)));
        if (matcher.group(2) == null) {
          ingredient.setUnit(Unit.toUnit("other"));
          ingredient.setUnitAlt("");
        }
        ingredient.setName(Objects.requireNonNull(matcher.group(3)).trim());
      } else {
        ingredient.setQuantity("1");
        ingredient.setUnit(Unit.toUnit("other"));
        ingredient.setUnitAlt("");
        ingredient.setName(rawIngredient);
      }
      ingredients.add(ingredient);
    }
  }

  public void setDocument(Document document) {
    this.document = document;
  }

  private static class InstanceHolder {

    private static final JsoupMachine INSTANCE = new JsoupMachine();
  }

}
