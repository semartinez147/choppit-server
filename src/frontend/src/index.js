import "bootstrap/dist/css/bootstrap.css"
import ReactDOM from 'react-dom';
import './index.css';
import store from './store/store'
import reportWebVitals from './etc/reportWebVitals';
import {App} from "./App.js";

ReactDOM.render(App(store), document.querySelector('#root'));

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
