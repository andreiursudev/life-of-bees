import React from 'react';
import '../App.css';

const GameView = () => {
    return (
        <div className="container">
            <div class="container" style="margin-top: 50px;">
                <div class="row">
                    <div class="col-md-3">
                        <div class="card mb-3">
                            <div class="card-body">
                                <p>Hive 1</p>
                                <p>Bees 23456</p>
                                <p>rapeseed honey: 36.6kg</p>
                                <p>Total honey: 36.6kg</p>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-body">
                                <p>Hive 2</p>
                                <p>Bees 23456</p>
                                <p>rapeseed honey: 36.6kg</p>
                                <p>Total honey: 36.6kg</p>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-body">
                                <p>Hive 3</p>
                                <p>Bees 23456</p>
                                <p>rapeseed honey: 36.6kg</p>
                                <p>Total honey: 36.6kg</p>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-body">
                                <p>Hive 4</p>
                                <p>Bees 23456</p>
                                <p>rapeseed honey: 36.6kg</p>
                                <p>Total honey: 36.6kg</p>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-body">
                                <p>Hive 5</p>
                                <p>Bees 23456</p>
                                <p>rapeseed honey: 36.6kg</p>
                                <p>Total honey: 36.6kg</p>
                            </div>
                        </div>

                    </div>

                    <div class="col-md-3">
                        <div class="card mb-3">
                            <div class="card-body">
                                <p>Hive 6</p>
                                <p>Bees 23456</p>
                                <p>rapeseed honey: 36.6kg</p>
                                <p>Total honey: 36.6kg</p>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-body">
                                <p>Hive 7</p>
                                <p>Bees 23456</p>
                                <p>rapeseed honey: 36.6kg</p>
                                <p>Total honey: 36.6kg</p>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-body">
                                <p>Hive 8</p>
                                <p>Bees 23456</p>
                                <p>rapeseed honey: 36.6kg</p>
                                <p>Total honey: 36.6kg</p>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-body">
                                <p>Hive 9</p>
                                <p>Bees 23456</p>
                                <p>rapeseed honey: 36.6kg</p>
                                <p>Total honey: 36.6kg</p>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-body">
                                <p>Hive 10</p>
                                <p>Bees 23456</p>
                                <p>rapeseed honey: 36.6kg</p>
                                <p>Total honey: 36.6kg</p>
                            </div>
                        </div>

                    </div>

                    <div class="col-md-3">
                        <div class="card mb-3">
                            <div class="card-body">
                                <p>Action of the week:</p>
                                <p>Insect control</p>
                                <p>Yes or No?</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="d-flex flex-column">
                            <button class="btn btn-custom mb-2">Date: 1 Apr 2023</button>
                            <button class="btn btn-custom mb-2">Temp 22°C</button>
                            <button class="btn btn-custom mb-2">Wind speed: 3m/s</button>
                            <button class="btn btn-custom mb-2">Totatl honey: 75.6kg</button>

                            <img src="rapeseed flower.jpg" alt="Imagine Buton 5" class="img-custom mb-2" />

                            <button class="btn btn-custom mb-2" onclick="location.href='sellHoney.html'">Sell honey</button>
                            <button class="btn btn-custom mb-2">Money in the bank: 3000$</button>
                            <button class="btn btn-custom mb-2">Buy hives[150$/pc]</button>
                            <button class="btn btn-custom mb-2">Iterate/ Go to next week</button>
                            <button class="btn btn-danger mb-2" onclick="location.href='index.html'">Exit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    );
  };

            export default GameView;