const HiveCard = ({ hive }) => {
    return (
        <div className="card mb-2">
            <div className="card-body">
                <p>Hive ID: {hive.hiveId}</p>
                <p>Queen's age: {hive.ageOfQueen}</p>
                <p>Bees: {hive.bees}</p>
                <p>Rapeseed honey: {hive.rapeseedHoney}</p>
                <p>Total honey: {hive.totalHoney}</p>
                <p>Eggs Frame: {hive.eggsFrame}</p>
                <p>Honey Frame: {hive.honeyFrame}</p>
            </div>
        </div>
    );
};

export default HiveCard;

