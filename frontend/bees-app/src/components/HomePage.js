import React, { Component, useState } from 'react';
import '../App.css';
import LoginComponents from './LoginComponents';
import ApiaryCardsRow from './ApiaryCardsRow';


const HomePage = () => {
    const [showPublicModal, setShowPublicModal] = useState(false);
    const [showPrivateModal, setShowPrivateModal] = useState(false);
    const handlePublicGameClick = () => {
        setShowPublicModal(true);
    };
    const handlePrivateGameClick = () => {
        setShowPrivateModal(true);
    };
    const handleCloseModal = () => {
        setShowPublicModal(false);
        setShowPrivateModal(false);
    };

    return (
        <div className="container">
            <h1>Life of Bees</h1>
            <button className="btn btn-primary btn-lg" onClick={handlePublicGameClick}>Create public game
            </button>
            <button className="btn btn-secondary btn-lg" onClick={handlePrivateGameClick}>Create private game
            </button>
            <div className="pt-3">
                <ul className="nav nav-tabs pt-3">
                    <li className="nav-item">
                        <a className="nav-link active" href="#">List</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Map</a>
                    </li>
                </ul>
            </div>

            <ApiaryCardsRow />//TODO GamesList

            {showPublicModal && (
                <LoginComponents handleClose={handleCloseModal} />
            )}
            {showPrivateModal && (
                <LoginComponents handleClose={handleCloseModal} />
            )}
        </div>

    );
};

export default HomePage;
