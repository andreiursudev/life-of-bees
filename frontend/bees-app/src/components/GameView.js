import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../App.css';
import HiveCard from './HiveCard';
import { getGame, iterateWeek, submitActionsOfTheWeek } from './BeesApiService';
import rapeseedFlower from '../rapeseed-flower.jpg';
import wildFlower from '../wild-flower.jpg';
import acaciaFlower from '../acacia-flower.jpg';
import lindenFlower from '../linden-flower.jpg';
import sunFlower from '../sun-flower.jpg';
import falseIndigoFlower from '../false-indigo-flower.jpg';
import { getHoneyQuantities } from './BeesApiService';




const GameView = () => {
    const navigate = useNavigate();
    const [gameData, setGameData] = useState(null);
    const [selectedActions, setSelectedActions] = useState({});
    const [updatedGameData, setUpdatedGameData] = useState(null);

    useEffect(() => {
        async function fetchGameData() {
            try {
                console.log('Fetching game data...');
                const data = await getGame();
                console.log('Game data:', data);
                setGameData(data);
                setUpdatedGameData(data);
            } catch (error) {
                console.error('Error fetching game data:', error);
            }
        }
        fetchGameData();
    }, []);

    const handleCheckboxChange = (actionType, hiveId) => {
        setSelectedActions((prevSelectedActions) => ({
            ...prevSelectedActions,
            [`${actionType}-${hiveId}`]: !prevSelectedActions[`${actionType}-${hiveId}`],
        }));
    };


    const handleSubmit = async () => {
        try {
            const actionsByType = Object.keys(selectedActions)
                .filter(key => selectedActions[key])
                .reduce((acc, key) => {
                    // const [actionType, hiveId] = key.split('-');
                    const [actionType, sourceHiveId, destinationHiveId] = key.split('-');
                    if (!acc[actionType]) {
                        acc[actionType] = {
                            actionType,
                            data: {}
                        };
                    }

                    if (['ADD_EGGS_FRAME', 'ADD_HONEY_FRAME', 'SPLIT_HIVE'].includes(actionType)) {
                        // Convertim `sourceHiveId` într-un număr și îl adăugăm la `hiveIds`
                        if (!acc[actionType].data.hiveIds) acc[actionType].data.hiveIds = [];
                        acc[actionType].data.hiveIds.push(parseInt(sourceHiveId));
                    }

                    // Pentru acțiunea `MOVE_EGGS_FRAME`, folosim perechi de stupi
                    else if (actionType === 'MOVE_EGGS_FRAME' && sourceHiveId && destinationHiveId) {
                        if (!acc[actionType].data.hiveIdPairs) acc[actionType].data.hiveIdPairs = [];
                        acc[actionType].data.hiveIdPairs.push([parseInt(sourceHiveId), parseInt(destinationHiveId)]);
                    }

                    // Setăm `answer` pentru acțiuni de tip `FEED_BEES`, `INSECT_CONTROL`
                    else {
                        acc[actionType].data.answer = selectedActions[key];
                    }

                    return acc;
                }, {});

            // Transforma obiectul de acțiuni într-o listă și eliminăm duplicatele
            const aggregatedActions = Object.values(actionsByType).map(action => {
                if (action.data.hiveIds) {
                    action.data.hiveIds = [...new Set(action.data.hiveIds)];
                }
                if (action.data.hiveIdPairs) {
                    action.data.hiveIdPairs = [...new Set(action.data.hiveIdPairs.map(JSON.stringify))].map(JSON.parse);
                }
                return action;
            });

            console.log("Actions data being sent:", aggregatedActions);
            const response = await submitActionsOfTheWeek(aggregatedActions);
            console.log("Response from backend:", response);

            if (response) {
                console.log("Actions submitted successfully!");
                const updatedData = await getGame();
                setGameData(updatedData);
                setUpdatedGameData(updatedData);
                setSelectedActions({});
            }
        } catch (error) {
            console.error("Error submitting actions:", error);
        }
    };


    // Funcția handleYesNoChange pentru a seta "yes" sau "no"
    const handleYesNoChange = (actionType, response) => {
        setSelectedActions((prevSelectedActions) => ({
            ...prevSelectedActions,
            [actionType]: response, // Salvează "yes" sau "no" corespunzător
        }));
    };







    const handleIterateWeek = async () => {
        try {
            const updatedGameData = await iterateWeek();
            console.log('Updated game data:', updatedGameData);
            setGameData(updatedGameData);
            setUpdatedGameData(updatedGameData);
            setSelectedActions({});
        } catch (error) {
            console.error('Error iterating week:', error);
        }
    };

    const getFlowerImage = () => {
        if (!gameData || !gameData.currentDate) {
            return wildFlower;
        }

        const currentDate = new Date(gameData.currentDate);
        const month = currentDate.getMonth() + 1;
        const day = currentDate.getDate();
        if (month === 3 || month === 8 || month === 9) {
            return wildFlower;
        } else if (month === 4 && day <= 20) {
            return rapeseedFlower;
        } else if (month === 4 && day >= 21) {
            return wildFlower;
        } else if (month === 5 && day <= 20) {
            return acaciaFlower;
        } else if (month === 5 && day >= 21 && day <= 31) {
            return falseIndigoFlower;
        } else if (month === 6 && day <= 20) {
            return lindenFlower;
        } else if (month === 6 && day >= 21) {
            return wildFlower;
        } else if (month === 7) {
            return sunFlower;
        } else {
            return wildFlower;
        }
    };

    const formatActionType = (actionType) => {
        return actionType
            .toLowerCase() // Transforma tot textul în litere mici
            .replace(/_/g, ' ') // Înlocuiește underscore cu spațiu
            .replace(/\b\w/g, char => char.toUpperCase()); // Fă majusculă prima literă a fiecărui cuvânt
    };



    return (
        <div className="body-gameView">
            <div className="row">
                <div className="col-md-6">
                    <div className="row">
                        {gameData && gameData.hives && gameData.hives.length > 0 ? (
                            gameData.hives.map((hive, index) => (
                                <div className="col-md-6 mb-3" key={hive.id}>
                                    <HiveCard hive={hive} />
                                </div>
                            ))
                        ) : (
                            <p>No hives available or data not loaded yet.</p>
                        )}
                    </div>
                </div>

                {/* A doua coloană cu acțiunile săptămânale */}
                <div className="col-md-3">
                    <div className="card mb-3">
                        <div className="card-body">
                            {updatedGameData && Array.isArray(updatedGameData.actionOfTheWeek) && updatedGameData.actionOfTheWeek.length > 0 ? (
                                <div>
                                    <p>Action of the week:</p>
                                    <form>
                                        {updatedGameData.actionOfTheWeek.map((actionItem, actionIndex) => (
                                            <div key={actionIndex}>
                                                {/* Afișează mesajul acțiunii, doar dacă este definit */}
                                                {actionItem.actionOfTheWeekMessage && (
                                                    <p>{actionItem.actionOfTheWeekMessage}</p>
                                                )}

                                                {/* Afișează tipul acțiunii cu formatare corectă */}
                                                <h5>{formatActionType(actionItem.actionType)}</h5>

                                                {/* Condiții în funcție de tipul acțiunii */}
                                                {(() => {
                                                    switch (actionItem.actionType) {
                                                        case "ADD_EGGS_FRAME":
                                                        case "ADD_HONEY_FRAME":
                                                        case "SPLIT_HIVE":
                                                            return actionItem.data.hiveIds.map((hiveId, hiveIndex) => (
                                                                <div key={`${actionIndex}-${hiveIndex}`} className="form-check">
                                                                    <input
                                                                        type="checkbox"
                                                                        className="form-check-input"
                                                                        id={`hive-${actionItem.actionType}-${hiveId}`}
                                                                        checked={selectedActions[`${actionItem.actionType}-${hiveId}`] || false}
                                                                        onChange={() => handleCheckboxChange(actionItem.actionType, hiveId)}
                                                                    />
                                                                    <label className="form-check-label" htmlFor={`hive-${actionItem.actionType}-${hiveId}`}>
                                                                        Hive {hiveId}
                                                                    </label>
                                                                </div>
                                                            ));

                                                        case "MOVE_EGGS_FRAME":
                                                            // Verificăm dacă `hiveIdPairs` există și este un array înainte de a folosi `map`
                                                            return actionItem.data.hiveIdPairs && Array.isArray(actionItem.data.hiveIdPairs) ? (
                                                                actionItem.data.hiveIdPairs.map((pair, pairIndex) => {
                                                                    const checkboxKey = `${actionItem.actionType}-${pair[0]}-${pair[1]}`;
                                                                    const isSourceSelected = selectedActions[checkboxKey]; // Verifică dacă sursa a fost selectată
                                                                    const isInactive = Object.keys(selectedActions).some(key =>
                                                                        key.startsWith(`${actionItem.actionType}-${pair[0]}-`) && selectedActions[key]
                                                                    );

                                                                    return (
                                                                        <div key={pairIndex}>
                                                                            <label>
                                                                                <input
                                                                                    type="checkbox"
                                                                                    checked={!!selectedActions[checkboxKey]}
                                                                                    onChange={() => {
                                                                                        if (!isInactive) { // Dacă nu sunt inactivi
                                                                                            setSelectedActions((prevSelectedActions) => {
                                                                                                const newSelectedActions = { ...prevSelectedActions };

                                                                                                // Debifează opțiunile anterioare cu aceeași sursă
                                                                                                Object.keys(newSelectedActions).forEach(key => {
                                                                                                    if (key.startsWith(`${actionItem.actionType}-${pair[0]}-`)) {
                                                                                                        delete newSelectedActions[key];
                                                                                                    }
                                                                                                });

                                                                                                // Bifează sau debifează opțiunea curentă
                                                                                                newSelectedActions[checkboxKey] = !prevSelectedActions[checkboxKey];
                                                                                                return newSelectedActions;
                                                                                            });
                                                                                        }
                                                                                    }}
                                                                                    disabled={isInactive} // Dezactivează checkbox-ul dacă este inactiv
                                                                                />
                                                                                Move frame from hive {pair[0]} to hive {pair[1]}
                                                                            </label>
                                                                        </div>
                                                                    );
                                                                })
                                                            ) : null;


                                                        case "FEED_BEES":
                                                        case "INSECT_CONTROL":
                                                            return (
                                                                <div>
                                                                    {actionItem.data.hiveIds && Array.isArray(actionItem.data.hiveIds) && actionItem.data.hiveIds.length > 0 && (
                                                                        <p>In all your hives: {actionItem.data.hiveIds.join(', ')}</p>
                                                                    )}
                                                                    <div className="form-check">
                                                                        <label>
                                                                            <input
                                                                                type="radio"
                                                                                name={`yesNo-${actionItem.actionType}`}
                                                                                value="yes"
                                                                                checked={selectedActions[actionItem.actionType] === "yes"}
                                                                                onChange={() => handleYesNoChange(actionItem.actionType, "yes")}
                                                                            />
                                                                            Yes
                                                                        </label>
                                                                        <label>
                                                                            <input
                                                                                type="radio"
                                                                                name={`yesNo-${actionItem.actionType}`}
                                                                                value="no"
                                                                                checked={selectedActions[actionItem.actionType] === "no"}
                                                                                onChange={() => handleYesNoChange(actionItem.actionType, "no")}
                                                                            />
                                                                            No
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            );


                                                        case "HARVEST_HONEY":
                                                        case "HIBERNATE":
                                                            return (
                                                                <p>
                                                                    {actionItem.data.hiveIds.map((hiveId, hiveIndex) => (
                                                                        <span key={`${actionIndex}-${hiveIndex}`}>
                                                                            Hive {hiveId}{hiveIndex < actionItem.data.hiveIds.length - 1 ? ', ' : ''}
                                                                        </span>
                                                                    ))}
                                                                </p>
                                                            );

                                                        default:
                                                            return null;
                                                    }
                                                })()}
                                                <hr />
                                            </div>
                                        ))}

                                    </form>
                                    <button className="btn btn-success mt-3" onClick={handleSubmit}>Submit</button>
                                </div>

                            ) : (
                                <p>Weekly checking</p>
                            )}

                        </div>
                    </div>
                </div>

                {/* A treia coloană pentru detalii despre vreme și acțiuni generale */}
                <div className="col-md-3">
                    <div className="d-flex flex-column align-items-center">
                        <p className="btn-custom p-custom mb-2">Date: {gameData ? gameData.currentDate : 'Loading...'}</p>
                        <p className="btn-custom p-custom mb-2">Temp: {gameData && gameData.temperature ? gameData.temperature.toFixed(2) : 'Loading...'}</p>
                        <p className="btn-custom p-custom mb-2">Wind speed: {gameData && gameData.windSpeed ? gameData.windSpeed.toFixed(2) : 'Loading...'}</p>
                        <p className="btn-custom p-custom mb-2">Precipitation: {gameData && gameData.precipitation ? gameData.precipitation.toFixed(2) : 'Loading...'}</p>
                        <p className="btn-custom p-custom mb-2">Total honey harvested: {gameData && gameData.totalKgOfHoneyHarvested ? gameData.totalKgOfHoneyHarvested.toFixed(2) : 'Loading'}</p>
                        <p className="btn-custom p-custom mb-2">Money in the bank: {gameData && gameData.moneyInTheBank ? gameData.moneyInTheBank.toFixed(2) : 'Loading...'}</p>
                        <img src={getFlowerImage()} alt="Flower based on date" className="img-custom mb-2" />
                        <button className="btn btn-custom p-custom mb-2" onClick={handleIterateWeek}>Iterate one week</button>
                        <button className="btn btn-custom p-custom mb-2" onClick={() => navigate('/sell-honey')}>Sell Honey</button>
                        <button className="btn btn-custom mb-2">Buy Hives</button>
                        <button className="btn btn-danger btn-custom mb-2" onClick={() => navigate('/')}>Exit</button>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default GameView;
