import React, { useEffect, useRef } from 'react';
import api from '../../api/axiosConfig';
import { useParams } from 'react-router-dom';
import { Col, Container, Row } from 'react-bootstrap';
import ReviewForm from '../reviewForm/ReviewForm';

function Reviews({ getMovie, movie, reviews, setReviews }) {
    let revText = useRef();
    let params = useParams();
    let movieImdbId = params.movieImdbId;

    useEffect(() => {
        getMovie(movieImdbId);
    }, []);

    const addReview = async (event) => {
        event.preventDefault();
        let review = revText.current;
        try {
            let response = await api.post('/api/reviews', { reviewBody: review.value, imdbId: movieImdbId });
            let updateReviews = [...reviews, { body: review.value }];
            review.value = '';
            setReviews(updateReviews);
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <Container>
            <Row>
                <Col>
                    <h3>Reviews</h3>
                </Col>
            </Row>
            <Row className='mt-2'>
                <Col>
                    <img src={movie?.poster} alt='' />
                </Col>
                <Col>
                    {
                        <>
                            <Row>
                                <Col>
                                    <ReviewForm handleSubmit={addReview} revText={revText} labelText='Put Review' />
                                </Col>
                            </Row>
                            <Row>
                                <Col>
                                    <hr />
                                </Col>
                            </Row>
                        </>
                    }
                    {
                        reviews?.map((review) => {
                            return (
                                <>
                                    <Row>
                                        <Col>
                                            {review.body}
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col>
                                            <hr />
                                        </Col>
                                    </Row>
                                </>
                            )
                        })
                    }
                </Col>
            </Row>
            <Row>
                <Col>
                    <hr />
                </Col>
            </Row>
        </Container>
    )
}

export default Reviews