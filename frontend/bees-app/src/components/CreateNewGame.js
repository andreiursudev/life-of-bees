import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { createGame, fetchLocations, fetchWeatherForStartDate, getGame } from './BeesApiService';

const NewGameModal = ({ handleClose }) => {
    const [gameName, setgameName] = useState('');
    const [location, setLocation] = useState('');
    const [suggestions, setSuggestions] = useState([]);
    const [numberOfStartingHives, setNumberOfStartingHives] = useState(0);
    const navigate = useNavigate();

    const [numYears, setNumYears] = useState(1);
    const currentYear = new Date().getFullYear();
    const startYear = currentYear - numYears;
    const startDate = `${startYear}-03-01`;

    
    const handleSubmit = async (event) => {
        event.preventDefault();
        
        try {
            const gameData = {
                gameName,
                location,
                startDate,
                numberOfStartingHives,
            };
        
            console.log('Game data being sent:', gameData);
        
            // Crearea jocului și obținerea răspunsului cu id-ul jocului
            const response = await createGame(gameData);
            console.log('Datele initiale trimise de React:', response);
        
            const gameId = response; // Extragerea id-ului jocului din răspuns
            console.log('id-ul jocului este: ',gameId)
        
            // Apelarea funcției getGame cu id-ul jocului
            const gameDetails = await getGame(gameId); // Trimite gameId la getGame
            console.log('detaliile jocului sunt:', gameDetails);
            console.log('Jocul a inceput in Game View:', gameData);
            navigate('/GameView', {
                state: {
                    gameId: gameId // Transmite doar gameId
                }
            });
            
        
        
        } catch (error) {
            console.error('Error starting game in CreateNewgame:', error);
        }
    };
    
    

    const handleLocationChange = async (e) => {
        const query = e.target.value;
        setLocation(query);

        if (query.length >= 3) {
            const locationSuggestions = await fetchLocations(query);
            setSuggestions(locationSuggestions);
        } else {
            setSuggestions([]);
        }
    };

    const handleSuggestionClick = (suggestion) => {
        setLocation(suggestion);
        setSuggestions([]);
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
                                        onChange={handleLocationChange}
                                    />
                                    {suggestions.length > 0 && (
                                        <ul className="suggestions-list">
                                            {suggestions.map((suggestion, index) => (
                                                <li key={index} onClick={() => handleSuggestionClick(suggestion)}>
                                                    {suggestion}
                                                </li>
                                            ))}
                                        </ul>
                                    )}
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
