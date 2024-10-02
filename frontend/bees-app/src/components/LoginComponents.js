import React from 'react';
import { useNavigate } from 'react-router-dom';

const LoginComponents = ({ handleClose }) => {
    const navigate = useNavigate();
    return (
        <div className="modal show" style={{ display: 'block' }}>
            <div className="modal-dialog">
                <div className="modal-content">
                    <div className="modal-header">
                        <h4 className="modal-title">New Game</h4>
                        <button type="button" className="btn-close" onClick={handleClose}></button>
                    </div>
                    <div className="modal-body">
                        <form>
                            <div className="row mb-3 align-items-center">
                                <div className="col">
                                    <label htmlFor="name" className="col-form-label">Name</label>
                                </div>
                                <div className="col">
                                    <input type="text" className="form-control" id="name" name="name" />
                                </div>
                            </div>

                            <div className="row mb-3 align-items-center">
                                <div className="col">
                                    <label htmlFor="location" className="col-form-label">Location</label>
                                </div>
                                <div className="col">
                                    <input type="text" className="form-control" id="location" name="location" />
                                </div>
                            </div>

                            <div className="row mb-3 align-items-center">
                                <div className="col">
                                    <label htmlFor="startDate" className="form-label">Start Date:</label>
                                </div>
                                <div className="col">
                                    <input type="date" className="form-control" id="startDate" name="startDate" />
                                </div>
                            </div>

                            <div className="row mb-3 align-items-center">
                                <div className="col">
                                    <label htmlFor="hives" className="col-form-label">Hives</label>
                                    <span id="errorMessage" className="warning-message">(Values less than 5!)</span>
                                </div>
                                <div className="col">
                                    <input type="number" id="numberInput" className="form-control" name="numberInput" min="0" max="5" required />
                                </div>
                            </div>
                        </form>
                        <button className="btn btn-custom p-custom mb-2" onClick={() => navigate('/home')}>GameView</button>
                        <button className="btn btn-secondary" onClick={() => navigate('/home')}>Start</button>
                        <button className="btn btn-warning" onClick={() => navigate('/home')}>Random</button>
                    </div>

                    <div className="modal-footer">
                        <button type="button" className="btn btn-danger" onClick={handleClose}>Close</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginComponents;
