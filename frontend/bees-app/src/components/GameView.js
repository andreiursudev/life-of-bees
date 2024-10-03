import React, { Component } from 'react';
import { useNavigate } from 'react-router-dom';
import '../App.css';
import rapeseedFlower from '../rapeseed-flower.jpg';
import acaciaFlower from '../acacia-flower.jpg';
import lindenFlower from '../linden-flower.jpg';
import falseIndigoFlower from '../false-indigo-flower.jpg'
import sunFlower from '../sun-flower.jpg';
import wildFlower from '../wild-flower.jpg';

import HiveCard from './HiveCard';
import { getHives } from './BeesApiService';

const GameView = () => {
    const navigate = useNavigate();

    const handleAnswer = (answer) => {
        console.log(`User answered: ${answer}`);
        // daca Da se scad banii din Money in The Bank, daca nu reduc numarul albinelor cu 20%
    };

    return (
        <div className="body-gameView">
            <div className="row">
                {/* Coloană pentru stupi */}
                <div className="col-md-6">
                    <div className="row">
                        {getHives.map((hive, index) => (
                            <div className="col-md-6" key={index}>
                                <HiveCard hive={hive} />
                            </div>
                        ))}
                    </div>
                </div>

                {/* Coloană pentru acțiuni și alte informații */}
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

                {/* Coloană pentru detalii despre joc */}
                <div className="col-md-3">
                    <div className="d-flex flex-column">
                        <p className="btn-custom p-custom mb-2">Date: 1 Apr 2023</p>
                        <p className="btn-custom p-custom mb-2">Temp 22°C</p>
                        <p className="btn-custom p-custom mb-2">Wind speed: 3m/s</p>
                        <p className="btn-custom p-custom mb-2">Total honey: 75.6kg</p>
                        <p className="btn-custom p-custom mb-2">Money in the bank: 3000$</p>
                        <button className="btn btn-custom p-custom mb-2" onClick={() => navigate('/sell-honey')}>Sell honey</button>
                        <button className="btn btn-custom mb-2">Buy hives[150$/pc]</button>
                        <button className="btn btn-custom mb-2">Iterate/ Go to next week</button>
                        <button className="btn btn-danger btn-custom mb-2" onClick={() => navigate('/')}>Exit</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default GameView;
