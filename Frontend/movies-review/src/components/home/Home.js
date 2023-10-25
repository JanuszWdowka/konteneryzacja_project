import React from 'react';
import Movie from '../movie/Movie';

const Home = ({ movies }) => {
    return (
        <Movie movies={movies} />
    )
}

export default Home