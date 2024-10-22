const HiveCard = ({ hive }) => {
    return (
        <div className="card mb-2">
            <div className="card-body">
                <p>Hive ID: {hive.id}</p>
                <p>Queen's age: {hive.ageOfQueen}</p>
                <p>Bees: {hive.bees}</p>
                <p>EggsFrame:{hive.eggsFrame}</p>
                <p>HoneyFrame:{hive.honeyFrame}</p>
                {hive.kgOfHoney > 0 && (
                    <>
                        <p>Honey Harvested: {hive.kgOfHoney.toFixed(2)}</p>
                        <p>Honey Type: {hive.honeyType}</p>
                    </>
                )}

                <p>Was split: {hive.itWasSplit ? 'Yes' : 'No'}</p>
               
            </div>
        </div>
    );
};

export default HiveCard;
