import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../App.css';
import HiveCard from './HiveCard';
import { getGame } from './BeesApiService';
import rapeseedFlower from '../rapeseed-flower.jpg';

const GameView = () => {
    const navigate = useNavigate();

    const globalGameData = getGame[0];

    const handleAnswer = (answer) => {
        console.log(`User answered: ${answer}`);
        // dacă Da se scad banii din Money in The Bank, dacă nu se reduce numărul albinelor cu 20%
    };

    return (
        <div className="body-gameView">
            <div className="row">
                <div className="col-md-6">
                    <div className="row">
                        {getGame.map((hive, index) => (
                            <div className="col-md-6" key={index}>
                                <HiveCard hive={hive} />
                            </div>
                        ))}
                    </div>
                </div>
                <div className="col-md-3">
                    <div className="card mb-3">
                        <div className="card-body">
                            <p>Action of the week:</p>
                            <p>Insect control</p>
                            <p>Yes or No?</p>
                            <div>
                                <button className="btn btn-success me-2" onClick={() => handleAnswer("Yes")}>Yes</button>
                                <button className="btn btn-danger" onClick={() => handleAnswer("No")}>No</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-md-3">
                    <div className="d-flex flex-column">
                        <p className="btn-custom p-custom mb-2">Date: {globalGameData.date}</p>
                        <p className="btn-custom p-custom mb-2">Temp: {globalGameData.temp}</p>
                        <p className="btn-custom p-custom mb-2">Wind speed: {globalGameData.windSpeed}</p>
                        <p className="btn-custom p-custom mb-2">Total honey: {globalGameData.totalHoney}</p>
                        <p className="btn-custom p-custom mb-2">Money in the bank: {globalGameData.moneyInTheBank}</p>
                        <img src={rapeseedFlower} alt="Imagine Buton 5" className="img-custom mb-2" />
                        <button className="btn btn-custom p-custom mb-2" onClick={() => navigate('/sell-honey')}>Sell honey</button>
                        <button className="btn btn-custom mb-2">Buy hives [150$/pc]</button>
                        <button className="btn btn-custom mb-2">Iterate/ Go to next week</button>
                        <button className="btn btn-danger btn-custom mb-2" onClick={() => navigate('/')}>Exit</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default GameView;
