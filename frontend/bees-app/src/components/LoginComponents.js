import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { getGame } from './BeesApiService';

const LoginComponents = ({ handleClose }) => {//TODO rename to createNewGame
    const navigate = useNavigate();

    // State-uri pentru inputurile din formular
    const [name, setName] = useState('');
    const [location, setLocation] = useState('');
    const [startDate, setStartDate] = useState('');
    const [hives, setHives] = useState('');

   
    const handleSubmit = async (e) => {
        e.preventDefault();
    
        const game = {
            name: name,
            location: location,
            startDate: startDate,
            hives: parseInt(hives) 
        };
    
        try {
            const response = await axios.post('http://localhost:8080/api/createNewGame', game);//move this line to BeesApiService.js
            navigate('/home/'+response.data);
        } catch (error) {
            console.error('Error submitting the form', error);
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
                                    <input type="text" className="form-control" id="name" name="name" value={name} onChange={(e) => setName(e.target.value)} />
                                </div>
                            </div>

                            <div className="row mb-3 align-items-center">
                                <div className="col">
                                    <label htmlFor="location" className="col-form-label">Location</label>
                                </div>
                                <div className="col">
                                    <input type="text" className="form-control" id="location" name="location" value={location} onChange={(e) => setLocation(e.target.value)} />
                                </div>
                            </div>

                            <div className="row mb-3 align-items-center">
                                <div className="col">
                                    <label htmlFor="startDate" className="form-label">Start Date:</label>
                                </div>
                                <div className="col">
                                    <input type="date" className="form-control" id="startDate" name="startDate" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
                                </div>
                            </div>

                            <div className="row mb-3 align-items-center">
                                <div className="col">
                                    <label htmlFor="hives" className="col-form-label">Hives</label>
                                </div>
                                <div className="col">
                                    <input type="number" className="form-control" id="hives" name="hives" value={hives} onChange={(e) => setHives(e.target.value)} min="0" max="5" required />
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

export default LoginComponents;
