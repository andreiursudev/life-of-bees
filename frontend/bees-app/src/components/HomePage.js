import React from 'react';
import '../App.css';

const HomePage = () => {
  return (
    <div className="container">
      <h1>Life of Bees</h1>


      <div class="container">
        <button class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#publicModal">Create public
            game</button>

        <div class="modal" id="publicModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">New Game</h4>
                      </div>
                    <div class="modal-body">
                        <form>
                            <div class="row mb-3 align-items-center">
                                <div class="col">
                                    <label for="name" class="col-form-label">Name</label>
                                </div>
                                <div class="col">
                                    <input type="text" class="form-control" id="name" name="name"/>
                                </div>
                            </div>

                            <div class="row mb-3 align-items-center">
                                <div class="col">
                                    <label for="location" class="col-form-label">Location</label>
                                </div>
                                <div class="col">
                                    <input type="text" class="form-control" id="location" name="location"/>
                                </div>
                            </div>

                            <div class="row mb-3 align-items-center">
                                <div class="col">
                                    <label for="startDate" class="form-label">Start Date:</label>
                                </div>
                                <div class="col">
                                    <input type="date" class="form-control" id="startDate" name="startDate"/>
                                </div>
                            </div>

                            <div class="row mb-3 align-items-center">
                                <div class="col">
                                    <label for="hives" class="col-form-label">Hives</label>
                                    <span id="errorMessage" class="warning-message">(Values less then 5!)</span>
                                    
                                </div>
                                <div class="col">
                                    <input type="number" id="numberInput" name="numberInput" min="0" max="5"required/>
                                </div>
                            </div>
                        </form>
                            <button class="btn btn-secondary" onclick="location.href='gameView.html'">Start</button>
                            <button class="btn btn-warning" onclick="location.href='gameView.html'">Random</button>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                            </div>                        
                    </div>
                </div>
            </div>
        </div>


        <button class="btn btn-secondary btn-lg" data-bs-toggle="modal" data-bs-target="#privateModal">Create private
            game</button>
        <div class="modal" id="privateModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">New Game</h4>
                      </div>
                    <div class="modal-body">
                        <form>
                            <div class="row mb-3 align-items-center">
                                <div class="col">
                                    <label for="name" class="col-form-label">Name</label>
                                </div>
                                <div class="col">
                                    <input type="text" class="form-control" id="name" name="name"/>
                                </div>
                            </div>

                            <div class="row mb-3 align-items-center">
                                <div class="col">
                                    <label for="location" class="col-form-label">Location</label>
                                </div>
                                <div class="col">
                                    <input type="text" class="form-control" id="location" name="location"/>
                                </div>
                            </div>

                            <div class="row mb-3 align-items-center">
                                <div class="col">
                                    <label for="startDate" class="form-label">Start Date:</label>
                                </div>
                                <div class="col">
                                    <input type="date" class="form-control" id="startDate" name="startDate"/>
                                </div>
                            </div>

                            <div class="row mb-3 align-items-center">
                                <div class="col">
                                    <label for="hives" class="col-form-label">Hives</label>
                                    <span id="errorMessage" class="warning-message">(Values less then 5!)</span>
                                </div>
                                <div class="col">
                                    <input type="number" id="numberInput" name="numberInput" min="0" max="5"required/>
                                </div>
                            </div>
                        </form>

                        <button class="btn btn-secondary" onclick="location.href='gameView.html'">Start</button>
                        <button class="btn btn-warning" onclick="location.href='gameView.html'">Random</button>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div>


        <div class="container pt-3">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a class="nav-link active" href="#">List</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Map</a>
                </li>
            </ul>
        </div>

        <div class="container">
            <div class="row pt-3">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row pt-3">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Dacic Apiary</h4>
                            <p>Location: Galati, Romania</p>
                            <p>Hives:5</p>
                            <p>Bees:156790</p>
                            <p>Honey:567.9kg</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      </div>.
    </div> 
     
  );
};

export default HomePage;
