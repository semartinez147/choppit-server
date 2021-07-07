package com.semartinez.choppit.model.entity;

public enum Unit {
  DASH,
  TSP,
  TBSP,
  C,
  PT,
  QT,
  GAL,
  OZ,
  LB,
  OTHER; // Will generate a text field for things like "sprig" or "leg".


  public static String toString(Unit value) {
    return (value != null) ? value.name() : null;

  }

  public static Unit toUnit(String value) {
    if (value == null) {
      return Unit.OTHER;
    } else {
      try {
        return Unit.valueOf(value);
      } catch (IllegalArgumentException e) {
        switch (value) {
          case "teaspoon":
          case "teaspoons":
            return Unit.TSP;
          case "tablespoon":
          case "tablespoons":
            return Unit.TBSP;
          case "cup":
          case "cups":
            return Unit.C;
          case "pint":
          case "pints":
            return Unit.PT;
          case "quart":
          case "quarts":
            return Unit.QT;
          case "gallon":
          case "gallons":
            return Unit.GAL;
          case "ounce":
          case "ounces":
          case "fluid ounce":
          case "fluid ounces":
            return Unit.OZ;
          case "pound":
          case "pounds":
            return Unit.LB;
          default:
            return Unit.OTHER;
        }
      }
    }
  }
}
