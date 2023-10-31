import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faVideoSlash } from '@fortawesome/free-solid-svg-icons';
import { Nav, Navbar, Container } from 'react-bootstrap';
import { NavLink } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import './Header.css';
import api from '../../api/axiosConfig';

function Header() {

    let navigate = useNavigate();

    const getMovie = async (event) => {
        try {
            if (event.key === "Enter") {
                event.preventDefault();
                await openMovie(event.currentTarget.value);
            }
        } catch (error) {
            console.log(error);
        }
    }

    const openMovie = async (movieTitle) => {
        try {
            let movie = {};
            let response = await api.get(`/api/movies/getByTitle/${movieTitle}`);
            if (response.data != null) {
                movie.imdbId = response.data.imdbId;
            } else {
                let apiResponse = await api.get(`/api/api/getByName/${movieTitle}`);
                let movieDetails = await api.get(`/api/api/getById/${apiResponse.data.results[0].id}`);
                let movieImages = await api.get(`/api/api/getImagesById/${apiResponse.data.results[0].id}`);
                let movieVideos = await api.get(`/api/api/getVideosById/${apiResponse.data.results[0].id}`);
                movie.title = apiResponse.data.results[0].title;
                movie.releaseDate = apiResponse.data.results[0].release_date;
                movie.poster = 'https://image.tmdb.org/t/p/w500' + apiResponse.data.results[0].poster_path;
                movie.imdbId = movieDetails.data.imdb_id;
                let genres = [];
                movieDetails.data.genres.forEach(genre => {
                    genres.push(genre.name);
                });
                movie.genres = genres;
                let backdrops = [];
                movieImages.data.backdrops.forEach(backdrop => {
                    backdrops.push('https://image.tmdb.org/t/p/original' + backdrop.file_path);
                });
                movie.backdrops = backdrops;
                movie.trailerLink = 'https://www.youtube.com/watch?v=' + movieVideos.data.results.filter(movie => movie.site === 'YouTube' && movie.type === 'Trailer')[0].key;
                let response = await api.post('/api/movies/addMovie', {
                    imdbId: movie.imdbId,
                    title: movie.title,
                    releaseDate: movie.releaseDate,
                    trailerLink: movie.trailerLink,
                    genres: movie.genres,
                    backdrops: movie.backdrops,
                    poster: movie.poster
                });
            }
            navigate(`/Reviews/${movie.imdbId}`);
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <Navbar bg='dark' expand='lg' variant='dark'>
            <Container fluid>
                <Navbar.Brand href='/' style={{ 'color': 'lightblue' }}>
                    <FontAwesomeIcon icon={faVideoSlash} />YourReview
                </Navbar.Brand>
                <Navbar.Collapse id='navbarScroll'>
                    <Nav className='me-auto my-2 my-lg-0'
                        style={{ 'maxHeight': '100px' }}
                        navbarScroll>
                        <NavLink className='nav-link' to='/'>Home</NavLink>
                    </Nav>
                </Navbar.Collapse>
                <form>
                    <label>Enter full movie name to open:
                        <input type='text' className='input-movie-name' placeholder='Enter full movie name' onKeyDown={getMovie} />
                    </label>
                </form>
            </Container>
        </Navbar>
    )
}

export default Header