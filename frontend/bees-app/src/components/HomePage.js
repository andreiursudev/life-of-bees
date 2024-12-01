import React, { useState } from 'react';
import '../App.css';
import NewGameModal from './CreateNewGame';
import ApiaryCardsRow from './ApiaryCardsRow';
import AuthModal from './AuthModal';

import { authenticateUser, registerUser } from './BeesApiService';

const HomePage = () => {
    const [showPublicModal, setShowPublicModal] = useState(false);
    const [showPrivateModal, setShowPrivateModal] = useState(false);
    const [showAuthModal, setShowAuthModal] = useState(false);
    const [isSignUp, setIsSignUp] = useState(false);
    const [authMessage, setAuthMessage] = useState(null);
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [userId, setUserId] = useState(null);

    // Datele utilizatorului
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        confirmPassword: '',
    });

    const handlePublicGameClick = () => {
        if (isAuthenticated) setShowPublicModal(true);
    };

    const handlePrivateGameClick = () => {
        if (isAuthenticated) setShowPrivateModal(true);
    };

    const handleAuthClick = (signUp) => {
        setIsSignUp(signUp);
        setShowAuthModal(true);
    };

    const handleCloseModal = () => {
        setShowPublicModal(false);
        setShowPrivateModal(false);
        setShowAuthModal(false);
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleSignIn = async (username, password) => {
        try {
            const response = await authenticateUser({ username, password });
            console.log('SignIn response:', response); // Verifică structura răspunsului
            setAuthMessage('User authenticated successfully!');
            setIsAuthenticated(true);
            setShowAuthModal(false);
            setUserId(response.userId); // Asigură-te că `userId` există în răspuns
        } catch (error) {
            console.error('Error in SignIn:', error);
            setAuthMessage(error.response?.data || 'Failed to sign in. Please try again.');
            setIsAuthenticated(false);
        }
    };

    const handleSignUp = async (username, password) => {
        try {
            const response = await registerUser({ username, password });
            console.log('SignUp response:', response); // Verifică structura răspunsului
            setAuthMessage('User registered successfully!');
            setIsAuthenticated(true);
            setShowAuthModal(false);
            setUserId(response); // Asigură-te că `userId` există în răspuns
            console.log('userId setat este:', response);
        } catch (error) {
            console.error('Error in SignUp:', error);
            setAuthMessage(error.response?.data?.error || 'Failed to register. Please try again.');
            setIsAuthenticated(false);
        }
    };


    return (
        <div className="container">
            <h1>Life of Bees</h1>
            <button
                className="btn btn-primary btn-lg"
                onClick={handlePublicGameClick}
                disabled={!isAuthenticated}
            >
                Create public game
            </button>
            <button
                className="btn btn-secondary btn-lg"
                onClick={handlePrivateGameClick}
                disabled={!isAuthenticated}
            >
                Create private game
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

            <div className="auth-buttons-container">
                <div className="auth-buttons">
                    <button className="btn btn-success" onClick={() => handleAuthClick(false)}>
                        Sign In
                    </button>
                    <button className="btn btn-info" onClick={() => handleAuthClick(true)}>
                        Sign Up
                    </button>
                </div>
            </div>

            <ApiaryCardsRow />

            {showPublicModal && (
                <NewGameModal
                    isPublic={true}
                    handleClose={handleCloseModal}
                    userId={userId} // Transmite userId către NewGameModal
                />
            )}
            {showPrivateModal && (
                <NewGameModal
                    isPublic={false}
                    handleClose={handleCloseModal}
                    userId={userId} // Transmite userId către NewGameModal
                />
            )}


            {showAuthModal && (
                <AuthModal
                    handleClose={handleCloseModal}
                    handleSubmit={(username, password) =>
                        isSignUp ? handleSignUp(username, password) : handleSignIn(username, password)
                    }
                    handleInputChange={handleInputChange}
                    formData={formData}
                    isSignUp={isSignUp}
                    errorMessage={authMessage}
                />
            )}
            {authMessage && <p className="auth-message">{authMessage}</p>}
        </div>
    );
};

export default HomePage;
