import React from 'react';

const HiveCard = ({ hive }) => {
    return (
        <div className="card mb-2">
            <div className="card-body">
                <p>Hive ID: {hive.hiveId}</p>
                <p>Bees: {hive.bees}</p>
                <p>Rapeseed honey: {hive.rapeseedHoney}</p>
                <p>Total honey: {hive.totalHoney}</p>
            </div>
        </div>
    );
};

export default HiveCard;
