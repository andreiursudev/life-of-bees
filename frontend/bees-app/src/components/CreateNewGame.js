import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createGame } from './BeesApiService';

const NewGameModal = ({ handleClose }) => {
    const [gameName, setgameName] = useState('');
    const [location, setLocation] = useState('');
    const [numberOfStartingHives, setNumberOfStartingHives] = useState(0);
    const navigate = useNavigate();

    const [numYears, setNumYears] = useState(1); // Numărul de ani implicit
    const currentYear = new Date().getFullYear();
    const startYear = currentYear - numYears; // Anul de start calculat automat

    const handleSubmit = async (event) => {
        event.preventDefault();

        const startDate = `${startYear}-03-01`; // Setăm automat data de început la 1 martie în anul calculat

        try {
            const gameData = await createGame({
                gameName,
                location,
                startDate,
                numberOfStartingHives
            });
            console.log('Game started:', gameData);
            navigate('/gameView');
        } catch (error) {
            console.error('Error starting game:', error);
        }
    };

    return (
        <div className="modal show" style={{ display: 'block' }}>
            <div className="modal-dialog">
                <div className="modal-content">
                    <div className="modal-header">
                        <h4 className="modal-title">New Game</h4>
                        <button type="button" className="btn-close" onClick={handleClose}></button>
                    </div>
                    <div className="modal-body">
                        <form onSubmit={handleSubmit}>
                            <div className="row mb-3 align-items-center">
                                <div className="col">
                                    <label htmlFor="name" className="col-form-label">Name</label>
                                </div>
                                <div className="col">
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="gameName"
                                        name="gameName"
                                        value={gameName}
                                        onChange={(e) => setgameName(e.target.value)}
                                    />
                                </div>
                            </div>

                            <div className="row mb-3 align-items-center">
                                <div className="col">
                                    <label htmlFor="location" className="col-form-label">Location</label>
                                </div>
                                <div className="col">
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="location"
                                        name="location"
                                        value={location}
                                        onChange={(e) => setLocation(e.target.value)}
                                    />
                                </div>
                            </div>

                            <div className="row mb-3 align-items-center">
                                <div className="col">
                                    <label htmlFor="numYears" className="form-label">Years to play:</label>
                                </div>
                                <div className="col">
                                    <input
                                        type="number"
                                        className="form-control"
                                        id="numYears"
                                        name="numYears"
                                        value={numYears}
                                        onChange={(e) => setNumYears(Math.min(20, Math.max(1, e.target.value)))}
                                        min="1"
                                        max="20"
                                        required
                                    />
                                </div>
                            </div>

                            <div className="row mb-3 align-items-center">
                                <div className="col">
                                    <label htmlFor="numberOfStartingHives" className="col-form-label">Hives</label>
                                </div>
                                <div className="col">
                                    <input
                                        type="number"
                                        className="form-control"
                                        id="numberOfStartingHives"
                                        name="numberOfStartingHives"
                                        value={numberOfStartingHives}
                                        onChange={(e) => setNumberOfStartingHives(e.target.value)}
                                        min="0"
                                        max="5"
                                        required
                                    />
                                </div>
                            </div>

                            <button type="submit" className="btn btn-secondary">Start</button>
                        </form>
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-danger" onClick={handleClose}>Close</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default NewGameModal;
