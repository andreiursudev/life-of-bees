const HiveCard = ({ hive }) => {
    return (
        <div className="card mb-2">
            <div className="card-body">
                <p>Hive ID: {hive.hiveId}</p>
                <p>Queen's age: {hive.ageOfQueen}</p>
                <p>Bees: {hive.bees}</p>
                <p>EggsFrame:{hive.eggsFrame}</p>
                <p>HoneyFrame:{hive.honeyFrame}</p>
                <p>Honey: {hive.honeyType}</p>
                <p>Total honey: {hive.totalHoney.toFixed(2)}</p>
                <p>Was split: {hive.itWasSplit ? 'Yes' : 'No'}</p>
               
            </div>
        </div>
    );
};

export default HiveCard;
