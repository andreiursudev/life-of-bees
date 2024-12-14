import React, { useEffect, useState } from 'react';
import { gameHistory } from './BeesApiService';

const ApiaryCardsRow = () => {
    const [games, setGames] = useState([]);

    useEffect(() => {
        const fetchGames = async () => {
            try {
                const recentGames = await gameHistory();
                setGames(recentGames);
            } catch (error) {
                console.error('Eroare la încărcarea jocurilor recente:', error);
            }
        };

        fetchGames();
    }, []);

    const chunkArray = (array, chunkSize) => {
        const chunks = [];
        for (let i = 0; i < array.length; i += chunkSize) {
            chunks.push(array.slice(i, i + chunkSize));
        }
        return chunks;
    };

    const chunkedGames = chunkArray(games, 6);

    return (
        <div className="container">
            {chunkedGames.map((group, index) => (
                <div className="row" key={index}>
                    {group.map((game, idx) => (
                        <div className="col-md-2" key={idx}>
                            <div className="card">
                                <div className="card-body">
                                    <h5 className="card-title">{game.gameName}</h5>
                                    <p className="card-text">Location: {game.location}</p>
                                    <p className="card-text">Hives: {game.hives}</p>
                                    <p className="card-text">Bees: {game.bees}</p>
                                    <p className="card-text">Honey: {game.honey}</p>
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
