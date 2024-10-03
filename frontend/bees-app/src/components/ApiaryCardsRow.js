import React from 'react';
import { showGames } from './BeesApiService';

const ApiaryCardsRow = () => {
    const chunkArray = (array, chunkSize) => {
        const chunks = [];
        for (let i = 0; i < array.length; i += chunkSize) {
            chunks.push(array.slice(i, i + chunkSize));
        }
        return chunks;
    };

    const chunkedApiaries = chunkArray(showGames, 6);

    return (
        <div className="container">
            {chunkedApiaries.map((group, index) => (
                <div className="row" key={index}>
                    {group.map((apiary, idx) => (
                        <div className="col-md-2" key={idx}>
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
            ))}
        </div>
    );
};

export default ApiaryCardsRow;
