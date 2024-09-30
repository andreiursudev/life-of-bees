import React, { Component, useState } from 'react';
import '../App.css';
import FormularStart from './FormularStart';

class CardGame extends Component {
    render() {
        return (

            <div className="card">
                <div className="card-body">
                    <h4 className="card-title">Dacic Apiary</h4>
                    <p>Location: Galati, Romania</p>
                    <p>Hives:12</p>
                    <p>Bees:156790</p>
                    <p>Honey:5kg</p>
                </div>
            </div>

        );
    }
}


const HomePage = () => {
    const [showPublicModal, setShowPublicModal] = useState(false);
    const [showPrivateModal, setShowPrivateModal] = useState(false);

    const handlePublicGameClick = () => {
        setShowPublicModal(true);
    };
    
    const handlePrivateGameClick = () => {
        setShowPrivateModal(true);
    };

    const handleCloseModal = () => {
        setShowPublicModal(false);
        setShowPrivateModal(false);
    };

    return (
        <div className="container">
            <h1>Life of Bees</h1>
            <button className="btn btn-primary btn-lg" onClick={handlePublicGameClick}>Create public game
            </button>
            <button className="btn btn-secondary btn-lg" onClick={handlePrivateGameClick}>Create private game
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
                    <CardGame />
                </div>
                <div className="col">
                    <CardGame />
                </div>
                <div className="col">
                    <CardGame />
                </div>
                <div className="col">
                    <CardGame />
                </div>
            </div>
            <div className="row pt-3">
                <div className="col">
                    <CardGame />
                </div>
                <div className="col">
                    <CardGame />
                </div>
                <div className="col">
                    <CardGame />
                </div>
                <div className="col">
                    <CardGame />
                </div>
            </div>

            {showPublicModal && (
                <FormularStart handleClose={handleCloseModal} />
            )}
            {showPrivateModal && (
                <FormularStart handleClose={handleCloseModal} />
            )}
        </div>

    );
};

export default HomePage;
