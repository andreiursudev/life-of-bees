import React from 'react';
import { GoogleOAuthProvider, GoogleLogin } from '@react-oauth/google';
import { handleGitHubLogin } from './BeesApiService';

const AuthModal = ({ handleClose, handleSubmit, handleInputChange, formData, isSignUp,setIsSignUp, errorMessage, setIsAuthenticated, setUserName, setAuthMessage, setFormData }) => {
    const [passwordError, setPasswordError] = React.useState(null);

    const validatePasswords = () => {
        if (isSignUp && formData.password !== formData.confirmPassword) {
            return "Passwords do not match!";
        }
        return null;
    };
    const handleGoogleSuccess = (response) => {
        console.log('Google Login Success:', response);
        setIsAuthenticated(true);
        setUserName('GoogleUser');
    };


    const handleGoogleFailure = (error) => {
        console.error('Google Login Failure:', error);
        setAuthMessage('Google Login failed. Please try again.');
    };

    const toggleSignUp = () => {
        setIsSignUp((prev) => !prev); 
    };
    


    return (
        <div className="modal show" style={{ display: 'block' }}>
            <div className="modal-dialog">
                <div className="modal-content">
                    <div className="modal-header">
                        <h1 class="modal-title text-center">Life of Bees</h1>

                    </div>
                    <div className="modal-body">
                        <p class="fs-4 text-center">Log in to your account</p>
                        <div className="row mb-3 align-items-center">
                            <div className="col">
                                <p className="fs-6 text center">{isSignUp ? 'Already have an account?' : "Don't have an account?"}</p>
                            </div>
                            <div className="col">
                                <p
                                    className="link-primary"
                                    style={{ cursor: 'pointer', textDecoration: 'underline' }}
                                    onClick={toggleSignUp}
                                >
                                    {isSignUp ? 'Sign In' : 'Sign Up'}
                                </p>

                            </div>
                        </div>

                        <div className="row mb-3 align-items-center">
                            <div className="col">
                                <GoogleLogin
                                    onSuccess={handleGoogleSuccess}
                                    onError={handleGoogleFailure}
                                />
                            </div>
                            <div className="col">
                                <button
                                    onClick={handleGitHubLogin}
                                    className="btn btn-dark"
                                >
                                    Login with GitHub
                                </button>
                            </div>
                        </div>
                        <p class="fs-6 text-center"> Or with username and password</p>
                        <form
                            onSubmit={(e) => {
                                e.preventDefault();
                                const error = validatePasswords();
                                if (error) {
                                    setPasswordError(error);
                                    return;
                                }
                                setPasswordError(null);
                                handleSubmit(formData.username, formData.password);
                            }}
                        >
                            <div className="row mb-3 align-items-center">
                                <div className="col">
                                    <input
                                        type="text"
                                        name="username"
                                        placeholder="Username"
                                        value={formData.username}
                                        onChange={handleInputChange}
                                    />
                                </div>
                                <div className="col">
                                    <input
                                        type="password"
                                        name="password"
                                        placeholder="Password"
                                        value={formData.password}
                                        onChange={handleInputChange}

                                    />
                                </div>
                            </div>

                            {isSignUp && (
                                <>
                                    <input
                                        type="password"
                                        name="confirmPassword"
                                        placeholder="Confirm Password"
                                        value={formData.confirmPassword}
                                        onChange={handleInputChange}
                                    />
                                    {passwordError && (
                                        <p style={{ color: 'red', marginTop: '5px' }}>{passwordError}</p>
                                    )}
                                </>
                            )}

                            <div className="d-flex justify-content-end">
                                <button className="btn btn-secondary" type="submit">
                                    {isSignUp ? 'Sign Up' : 'Sign In'}
                                </button>
                            </div>

                        </form>
                        {errorMessage && (
                            <p className="error-message" style={{ color: 'red', marginTop: '10px' }}>
                                {errorMessage}
                            </p>
                        )}
                    </div>
                    <div className="modal-footer d-flex justify-content-start">
                        <button type="button" className="btn btn-danger" onClick={handleClose}>Close</button>
                    </div>

                </div>
            </div>
        </div >
    );
};

export default AuthModal;
