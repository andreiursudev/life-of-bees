import React, { useEffect, useState } from 'react';
import { getApiaryHistory } from './BeesApiService';
import '../App.css';
import { useLocation, useNavigate } from 'react-router-dom';

const ApiaryHistory = () => {
    const [historyData, setHistoryData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const location = useLocation();
    const { gameId } = location.state || {};
    const navigate = useNavigate();

    useEffect(() => {
        if (gameId) {
            getApiaryHistory(gameId)
                .then((data) => {
                    console.log('Data fetched from backend:', data);
                    setHistoryData(data);
                    setLoading(false);
                })
                .catch(() => {
                    setError('Error fetching Apiary History.');
                    setLoading(false);
                });
        } else {
            setError('Game ID is missing');
            setLoading(false);
        }
    }, [gameId]);

    if (loading) return <div className="text-center mt-4">Loading...</div>;
    if (error) return <div className="text-center text-danger mt-4">{error}</div>;

    return (
        <div className="container mt-4">
            <h1 className="text-center mb-4">Apiary History</h1>
            <div className="table-responsive">
                <table className="table table-striped table-bordered">
                    <thead className="table-dark">
                        <tr>
                            <th style={{ minWidth: '120px' }}>Date</th>
                            <th style={{ minWidth: '100px' }}>Temperature</th>
                            <th style={{ minWidth: '100px' }}>Wind Speed</th>
                            <th style={{ minWidth: '120px' }}>Precipitation</th>
                            <th style={{ minWidth: '80px' }}>Hive ID</th>
                            <th style={{ minWidth: '100px' }}>Bees</th>
                            <th style={{ minWidth: '100px' }}>Queen Age (years)</th>
                            <th style={{ minWidth: '120px' }}>Honey Type</th>
                            <th style={{ minWidth: '100px' }}>Honey (kg)</th>
                            <th style={{ minWidth: '120px' }}>Egg Frames</th>
                            <th style={{ minWidth: '120px' }}>Honey Frames</th>
                            <th style={{ minWidth: '100px' }}>Was Split</th>
                            <th style={{ minWidth: '140px' }}>Funds</th>
                            <th style={{ minWidth: '240px' }}>Action of the Week</th>
                        </tr>
                    </thead>
                    <tbody>
                        {Array.isArray(historyData) && historyData.length > 0 ? (
                            historyData.map((entry, index) => (
                                <React.Fragment key={index}>
                                    {/* Primul rând combinat cu primul stup */}
                                    <tr>
                                        <td>{entry.currentDate}</td>
                                        <td>{entry.weatherData?.temperature?.toFixed(2) || '0.00'}°C</td>
                                        <td>{entry.weatherData?.windSpeed?.toFixed(2) || '0.00'}m/s</td>
                                        <td>{entry.weatherData?.precipitation?.toFixed(2) || '0.00'}mm</td>
                                        {entry.hive && entry.hive.length > 0 ? (
                                            <>
                                                <td>{entry.hive[0].id}</td>
                                                <td>{entry.hive[0].beesBatches.reduce((sum, batch) => sum + batch, 0)}</td>
                                                <td>{entry.hive[0].queen.ageOfQueen}</td>
                                                <td>
                                                    {entry.hive[0].honeyBatches.length > 0 ? entry.hive[0].honeyBatches[0].honeyType : ''}
                                                </td>
                                                <td>
                                                    {entry.hive[0].honeyBatches.reduce((sum, batch) => sum + batch.kgOfHoney, 0).toFixed(2)} kg
                                                </td>
                                                <td>{entry.hive[0].eggFrames.numberOfEggFrames}</td>
                                                <td>{entry.hive[0].honeyFrames.honeyFrame.length}</td>
                                                <td>{entry.hive[0].itWasSplit ? 'Yes' : 'No'}</td>
                                            </>
                                        ) : (
                                            <>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </>
                                        )}


                                        <td>${entry.moneyInTheBank}</td>
                                        <td>
                                            {entry.actionsOfTheWeek?.actions
                                                ? Object.entries(entry.actionsOfTheWeek.actions)
                                                    .map(([action, hives]) =>
                                                        Array.isArray(hives) ? `${action}: ${hives.join(', ')}` : `${action}: ${hives}`
                                                    )
                                                    .join('; ')
                                                : 'No actions'}
                                        </td>


                                    </tr>
                                    {Array.isArray(entry.hive) &&
                                        entry.hive.slice(1).map((hive) => (
                                            <tr key={hive.id}>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td>{hive.id}</td>
                                                <td>{hive.beesBatches.reduce((sum, batch) => sum + batch, 0)}</td>
                                                <td>{hive.queen.ageOfQueen}</td>
                                                <td>
                                                    {hive.honeyBatches.length > 0 ? hive.honeyBatches[0].honeyType : ''}
                                                </td>
                                                <td>
                                                    {hive.honeyBatches.reduce((sum, batch) => sum + batch.kgOfHoney, 0).toFixed(2)} kg
                                                </td>
                                                <td>{hive.eggFrames.numberOfEggFrames}</td>
                                                <td>{hive.honeyFrames.honeyFrame.length}</td>
                                                <td>{hive.itWasSplit ? 'Yes' : 'No'}</td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                        ))}
                                </React.Fragment>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="14" className="text-center">
                                    No history data available.
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
            <button
                className="btn btn-danger mt-3 float-end"
                onClick={() => navigate('/gameView', { state: { gameId } })}
            >
                Back
            </button>
        </div>
    );
};

export default ApiaryHistory;
