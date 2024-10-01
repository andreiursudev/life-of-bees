import React, { Component } from 'react';
import '../App.css';
import rapeseedFlower from '../rapeseed-flower.jpg';
import acaciaFlower from '../acacia-flower.jpg';
import lindenFlower from '../linden-flower.jpg';
import falseIndigoFlower from '../false-indigo-flower.jpg'
import sunFlower from '../sun-flower.jpg';
import wildFlower from '../wild-flower.jpg';


class CardGame extends Component {
    render() {
        return (

            <div className="card mb-2">
                <div className="card-body">
                    <p>Hive 1000</p>
                    <p>Bees 123456</p>
                    <p>rapeseed honey: 136.6kg</p>
                    <p>Total honey: 316.6kg</p>
                </div>
            </div>

        );
    }
}

const handleAnswer = (answer) => {
    console.log(`User answered: ${answer}`);
    // daca Da se scad banii din Money in The Bank, daca nu reduc numarul albinelor cu 20%
  };
  

const GameView = () => {
    return (
        <div className="body-gameView">
            <div className="row">
                <div className="col-md-3">
                    <CardGame />
                    <CardGame />
                    <CardGame />
                    <CardGame />
                    <CardGame />
                </div>
                <div className="col-md-3">
                    <CardGame />
                    <CardGame />
                    <CardGame />
                    <CardGame />
                    <CardGame />
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
                        <p className="btn-custom p-custom mb-2">Date: 1 Apr 2023</p>
                        <p className="btn-custom p-custom mb-2">Temp 22°C</p>
                        <p className="btn-custom p-custom mb-2">Wind speed: 3m/s</p>
                        <p className="btn-custom p-custom mb-2">Total honey: 75.6kg</p>
                        <p className="btn-custom p-custom mb-2">Money in the bank: 3000$</p>
                        <img src={rapeseedFlower} alt="Imagine Buton 5" className="img-custom mb-2" />
                        <button className="btn btn-custom p-custom mb-2" onClick={() => window.location.href = 'sellHoney.html'}>Sell honey</button>

                        <button className="btn btn-custom mb-2">Buy hives[150$/pc]</button>
                        <button className="btn btn-custom mb-2">Iterate/ Go to next week</button>
                        <button className="btn btn-danger btn-custom mb-2" onClick={() => window.location.href = 'index.html'}>Exit</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default GameView;