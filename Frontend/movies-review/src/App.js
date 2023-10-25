import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';
import Layout from './components/Layout';
import { Route, Routes } from 'react-router-dom';
import Home from './components/home/Home';
import Header from './components/header/Header';
import MovieTrailer from './components/movieTrailer/MovieTrailer';

function App() {

  const [movies, setMovies] = useState();
  const getMovies = async () => {
    try {
      const response = await api.get('/api/movies');
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
      <Header />
      <Routes>
        <Route path='/' element={<Layout />}>
          <Route path='/' element={<Home movies={movies} />}></Route>
          <Route path='/MovieTrailer/:ytTrailerId' element={<MovieTrailer />}></Route>
        </Route>
      </Routes>
    </div>
  );
}

export default App;
