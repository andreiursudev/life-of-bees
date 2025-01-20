import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getJohnDoeGames, getGamesForUserByType } from './BeesApiService';

const ApiaryCardsRow = ({ isAuthenticated, userId, gameType, onGameClick, handleDelete }) => {
    const [games, setGames] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchGames = async () => {
            try {
                let recentGames;
                if (isAuthenticated && gameType && userId) {
                    recentGames = await getGamesForUserByType(userId, gameType);
                } else {
                    recentGames = await getJohnDoeGames();
                }
                setGames(recentGames);
            } catch (error) {
                console.error('Eroare la încărcarea jocurilor recente:', error);
            }
        };

        fetchGames();
    }, [isAuthenticated, userId, gameType]);


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
                        <div className="col-md-4 col-lg-3 mb-3" key={idx}>
                            <div className="card h-100 text-center shadow-sm card-hover">
                                <button
                                    className="btn btn-danger btn-sm delete-button"
                                    onClick={(e) => {
                                        handleDelete(game.gameId); 
                                        setGames((prevGames) => prevGames.filter((g) => g.gameId !== game.gameId)); 
                                    }}
                                    style={{
                                        position: "absolute",
                                        top: "10px",
                                        right: "10px",
                                        zIndex: 1,
                                    }}
                                >
                                    &times;
                                </button>


                                <div
                                    className="card-body"
                                    style={{ cursor: "pointer" }}
                                    onClick={() => onGameClick(game.gameId)}
                                >
                                    <h5 className="card-title">{game.gameName}</h5>
                                    <p className="card-text">Location: {game.location}</p>
                                    <p className="card-text">Hives: {game.hivesNumber}</p>
                                    <p className="card-text">Funds: {game.moneyInTheBank}$</p>
                                    <p className="card-text">Honey: {game.totalKgOfHoneyHarvested} kg</p>
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
