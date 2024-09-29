import React from 'react';
import '../App.css';

const HomePage = () => {
    return (
        <div className="container">
            <h1>Life of Bees</h1>
            <button className="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#publicModal">Create
                public
                game
            </button>
            <button className="btn btn-secondary btn-lg" data-bs-toggle="modal" data-bs-target="#privateModal">Create
                private game
            </button>

            <div className="pt-3">
                <ul className="nav nav-tabs pt-3">
                    <li className="nav-item">
                        <a className="nav-link active" href="#">List</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">Map</a>
                    </li>
                </ul>
            </div>

            <div className="row pt-3">
                <div className="col">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
            </div>
            <div className="row pt-3">
                <div className="col">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
            </div>

            <div className="modal" id="publicModal">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h4 className="modal-title">New Game</h4>
                        </div>
                        <div className="modal-body">
                            <form>
                                <div className="row mb-3 align-items-center">
                                    <div className="col">
                                        <label htmlFor="name" className="col-form-label">Name</label>
                                    </div>
                                    <div className="col">
                                        <input type="text" className="form-control" id="name" name="name"/>
                                    </div>
                                </div>

                                <div className="row mb-3 align-items-center">
                                    <div className="col">
                                        <label htmlFor="location" className="col-form-label">Location</label>
                                    </div>
                                    <div className="col">
                                        <input type="text" className="form-control" id="location" name="location"/>
                                    </div>
                                </div>

                                <div className="row mb-3 align-items-center">
                                    <div className="col">
                                        <label htmlFor="startDate" className="form-label">Start Date:</label>
                                    </div>
                                    <div className="col">
                                        <input type="date" className="form-control" id="startDate" name="startDate"/>
                                    </div>
                                </div>

                                <div className="row mb-3 align-items-center">
                                    <div className="col">
                                        <label htmlFor="hives" className="col-form-label">Hives</label>
                                        <span id="errorMessage" className="warning-message">(Values less then 5!)</span>

                                    </div>
                                    <div className="col">
                                        <input type="number" id="numberInput" className="form-control" name="numberInput" min="0" max="5"
                                               required/>
                                    </div>
                                </div>
                            </form>
                            <button className="btn btn-secondary" onClick="location.href='gameView.html'">Start</button>
                            <button className="btn btn-warning" onClick="location.href='gameView.html'">Random</button>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-danger" data-bs-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="modal" id="privateModal">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h4 className="modal-title">New Game</h4>
                        </div>
                        <div className="modal-body">
                            <form>
                                <div className="row mb-3 align-items-center">
                                    <div className="col">
                                        <label htmlFor="name" className="col-form-label">Name</label>
                                    </div>
                                    <div className="col">
                                        <input type="text" className="form-control" id="name" name="name"/>
                                    </div>
                                </div>

                                <div className="row mb-3 align-items-center">
                                    <div className="col">
                                        <label htmlFor="location" className="col-form-label">Location</label>
                                    </div>
                                    <div className="col">
                                        <input type="text" className="form-control" id="location" name="location"/>
                                    </div>
                                </div>

                                <div className="row mb-3 align-items-center">
                                    <div className="col">
                                        <label htmlFor="startDate" className="form-label">Start Date:</label>
                                    </div>
                                    <div className="col">
                                        <input type="date" className="form-control" id="startDate" name="startDate"/>
                                    </div>
                                </div>

                                <div className="row mb-3 align-items-center">
                                    <div className="col">
                                        <label htmlFor="hives" className="col-form-label">Hives</label>
                                        <span id="errorMessage" className="warning-message">(Values less then 5!)</span>
                                    </div>
                                    <div className="col">
                                        <input type="number" className="form-control" id="numberInput" name="numberInput" min="0" max="5"
                                               required/>
                                    </div>
                                </div>
                            </form>

                            <button className="btn btn-secondary" onClick="location.href='gameView.html'">Start</button>
                            <button className="btn btn-warning" onClick="location.href='gameView.html'">Random</button>
                        </div>

                        <div className="modal-footer">
                            <button type="button" className="btn btn-danger" data-bs-dismiss="modal">Close</button>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    );
};

export default HomePage;
