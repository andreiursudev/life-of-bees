import React from 'react';
import { handleGitHubLogin, handleGoogleLogin } from './BeesApiService';

const AuthModal = ({ handleClose, handleSubmit, handleInputChange, formData, isSignUp, setIsSignUp, errorMessage, setIsAuthenticated, setUserName, setAuthMessage, setFormData }) => {
    const [passwordError, setPasswordError] = React.useState(null);

    const validatePasswords = () => {
        if (isSignUp && formData.password !== formData.confirmPassword) {
            return "Passwords do not match!";
        }
        return null;
    };

    const handleGoogleSuccess = async (credentialResponse) => {
        console.log('Google Login Success:', credentialResponse);



        try {
            const response = await handleGoogleLogin(credentialResponse);
            setUserName(response.email);
            setIsAuthenticated(true);
        } catch (error) {
            console.error('Error sending Google token to backend:', error);
        }
    };

    const handleGoogleFailure = (error) => {
        console.error('Google Login Failure:', error);
        setAuthMessage('Google Login failed. Please try again.');
    };



    return (
        <div className="modal show" style={{ display: 'block' }}>
            <div className="modal-dialog modal-sm">
                <div className="modal-content">
                    <div className="modal-body">
                        <p className="fs-4 text-center">Log in to your account</p>
                        <div className="row mb-3 align-items-center">
                            <div className="col">
                                <p className="fs-6 text-center">Don't have an account?</p>
                            </div>
                            <div className="col">
                                <p
                                    className="link-primary"
                                    style={{ cursor: 'pointer', textDecoration: 'underline' }}
                                >
                                   Sign Up
                                </p>
                            </div>
                        </div>


                        <p className="fs-6 text-center"> Or with username and password</p>
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
                            <div className="mb-3">
                                <input
                                    type="text"
                                    name="username"
                                    placeholder="Username"
                                    value={formData.username}
                                    onChange={handleInputChange}
                                    className="form-control"
                                />
                            </div>

                            <div className="mb-3">
                                <input
                                    type="password"
                                    name="password"
                                    placeholder="Password"
                                    value={formData.password}
                                    onChange={handleInputChange}
                                    className="form-control"
                                />
                            </div>

                            {isSignUp && (
                                <>
                                    <div className="mb-3">
                                        <input
                                            type="password"
                                            name="confirmPassword"
                                            placeholder="Confirm Password"
                                            value={formData.confirmPassword}
                                            onChange={handleInputChange}
                                            className="form-control"
                                        />
                                        {passwordError && (
                                            <p style={{ color: 'red', marginTop: '5px' }}>{passwordError}</p>
                                        )}
                                    </div>
                                </>
                            )}
                            <div className="d-flex justify-content-between">
                                <button type="button" className="btn btn-danger" onClick={handleClose}>Close</button>
                                <button className="btn btn-secondary" type="submit">
                                    Sign Up
                                </button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AuthModal;
