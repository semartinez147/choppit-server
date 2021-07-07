import logo from "./logo.svg";
import './App.css';


const getData = () => {
  fetch('/api/messages/hello')
  .then(res => (res.text()))
  .then(data => {
    console.log('getData', data)

    return data
  })
}

export function Home() {
  return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a
              className="App-link"
              href="https://reactjs.org"
              target="_blank"
              rel="noopener noreferrer"
          >
            <p>{getData()}</p>
          </a>
        </header>
      </div>
  );
}