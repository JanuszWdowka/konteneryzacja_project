import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';

function App() {

  const [movies, setMovies] = useState();
  const getMovies = async () => {
    try {
      const response = await api.get('/api/movies');
      console.log(response.data);//pokazanie danych
      setMovies(response.data);
    } catch (e) {
      console.log(e);
    }
  }

  useEffect(() => {
    getMovies();
  });

  return (
    <div className="App">

    </div>
  );
}

export default App;
