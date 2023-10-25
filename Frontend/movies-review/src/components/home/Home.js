import React from 'react';
import Movies from '../movies/Movies';

const Home = ({ movies }) => {
    return (
        <Movies movies={movies} />
    )
}

export default Home