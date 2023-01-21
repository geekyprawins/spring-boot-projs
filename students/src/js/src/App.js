import "./App.css";

import {getAllStudents} from './client';

function App() {
  getAllStudents().then((response) => response.json()).then((students) => console.log(students));
  return <h1>Hello Spring Boot and React.....</h1>;
}

export default App;
