import React from 'react';


const AuthModal = ({ handleClose, handleSubmit, handleInputChange, formData, isSignUp, errorMessage }) => {
    return (
        <div className="modal-backdrop">
            <div className="modal-content-auth">
                <h2>{isSignUp ? 'Sign Up' : 'Sign In'}</h2>
                <form onSubmit={(e) => {
                    e.preventDefault();
                    handleSubmit(formData.username, formData.password);

                }}>
                    <input
                        type="text"
                        name="username"
                        placeholder="Username"
                        value={formData.username}
                        onChange={handleInputChange}
                    />
                    <input
                        type="password"
                        name="password"
                        placeholder="Password"
                        value={formData.password}
                        onChange={handleInputChange}
                    />
                    {isSignUp && (
                        <input
                            type="password"
                            name="confirmPassword"
                            placeholder="Confirm Password"
                            value={formData.confirmPassword}
                            onChange={handleInputChange}
                        />
                    )}
                    <button className="primary" type="submit">
                        {isSignUp ? 'Sign Up' : 'Sign In'}
                    </button>
                    <button className="secondary" type="button" onClick={handleClose}>
                        Cancel
                    </button>
                </form>
                {errorMessage && (
                    <p className="error-message" style={{ color: 'red', marginTop: '10px' }}>
                        {errorMessage}
                    </p>
                )}
            </div>
        </div>
    );
};

export default AuthModal;
