
import React, { useState, useEffect } from 'react';


import {getAllStudents} from './client';

function App() {

  const [students, setStudents] = useState([]);

  useEffect(() => {
    const url = "http://localhost:8081/api/v1/students";

    const fetchData = async () => {
      try {
        const response = await fetch(url);
        const json = await response.json();
        console.log(json);
        setStudents(json);
      } catch (error) {
        console.log("error", error);
      }
    };

    fetchData();

  }, []);

  return <h1>Hello Spring Boot and React!
  
  {students.map(student => (
    <div key={student.studentId}>
      <h2>{student.firstName}</h2>
      <p>{student.email}</p>
    </div>
  ))}
  </h1>;
}

export default App;
