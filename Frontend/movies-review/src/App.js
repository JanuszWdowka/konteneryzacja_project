import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';
import Layout from './components/Layout';
import { Route, Routes } from 'react-router-dom';
import Home from './components/home/Home';
import Header from './components/header/Header';
import MovieTrailer from './components/movieTrailer/MovieTrailer';
import Reviews from './components/reviews/Reviews';
import NotFound from './components/notFound/NotFound';

function App() {

  const [movies, setMovies] = useState();
  const [movie, setMovie] = useState();
  const [reviews, setReviews] = useState();

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

  const getMovie = async (movieImdbId) => {
    try {
      let response = await api.get(`/api/movies/getByImdbId/${movieImdbId}`);
      let movie = response.data;
      setMovie(movie);
      setReviews(movie.reviewIds);
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <div className="App">
      <Header />
      <Routes>
        <Route path='/' element={<Layout />}>
          <Route path='/' element={<Home movies={movies} />}></Route>
          <Route path='/MovieTrailer/:ytTrailerId' element={<MovieTrailer />}></Route>
          <Route path='/Reviews/:movieImdbId' element={<Reviews getMovie={getMovie} movie={movie} reviews={reviews} setReviews={setReviews} />}></Route>
          <Route path='*' element={<NotFound />}></Route>
        </Route>
      </Routes>
    </div>
  );
}

export default App;
