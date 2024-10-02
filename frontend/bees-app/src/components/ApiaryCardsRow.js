import React from 'react';
import { getGame } from './BeesApiService';

const ApiaryCardsRow = () => {
    return (
        <div className="row">
            {getGame.map((apiary, index) => (
                <div className="col-md-4" key={index}>
                    <div className="card">
                        <div className="card-body">
                            <h5 className="card-title">{apiary.gameName}</h5>
                            <p className="card-text">Location: {apiary.location}</p>
                            <p className="card-text">Hives: {apiary.hives}</p>
                            <p className="card-text">Bees: {apiary.bees}</p>
                            <p className="card-text">Honey: {apiary.honey}</p>
                        </div>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default ApiaryCardsRow;
