import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faVideoSlash } from '@fortawesome/free-solid-svg-icons';
import { Nav, Navbar, Container } from 'react-bootstrap';
import { NavLink } from 'react-router-dom';

function Header() {
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
            </Container>
        </Navbar>
    )
}

export default Header