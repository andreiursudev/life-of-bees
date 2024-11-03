import React, { useState, useEffect } from 'react';
import '../App.css';
import { useNavigate } from 'react-router-dom';
import { sendSellHoneyQuantities, getHoneyQuantities } from './BeesApiService';

const RowHeader = () => (
    <div className="row-text">
        <p className="btn-custom-sell mb-2">Honey Type</p>
        <p className="btn-custom-sell mb-2">Quantity[kg]</p>
        <p className="btn-custom-sell mb-2">Price[$/kg]</p>
        <p className="btn-custom-sell mb-2">You sell</p>
        <p className="btn-custom-sell mb-2">Value</p>
    </div>
);

const RowText = ({ honeyType, quantity, price, onQuantityChange }) => {
    const [sellQuantity, setSellQuantity] = useState(0); 

    const handleInputChange = (event) => {
        const value = Math.max(0, Math.min(Number(event.target.value), quantity)) || 0; 
        setSellQuantity(value);
        onQuantityChange(value, honeyType, price); 
    };

    const totalValue = (sellQuantity * price).toFixed(2);

    return (
        <div className="row-text">
            <p className="btn-custom-sell mb-2">{honeyType}</p>
            <p className="btn-custom-sell mb-2">{quantity}</p>
            <p className="btn-custom-sell mb-2">{price}</p>
            <form>
                <input
                    type="number"
                    className="btn-custom-sell mb-2"
                    min="0"
                    max={quantity}
                    value={sellQuantity || ''}
                    onChange={handleInputChange}
                />
            </form>
            <p className="btn-custom-sell mb-2">${totalValue}</p>
        </div>
    );
};

const SellHoney = () => {
    const [honeyData, setHoneyData] = useState([]);
    const [soldValues, setSoldValues] = useState({});
    const [soldValueTotals, setSoldValueTotals] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        const fetchHoneyData = async () => {
            try {
                const data = await getHoneyQuantities();
                const parsedData = Object.entries(data).map(([honeyType, quantity]) => ({
                    honeyType,
                    quantity,
                }));
                setHoneyData(parsedData);
            } catch (error) {
                console.error('Error fetching honey data:', error);
            }
        };
        fetchHoneyData();
    }, []);

    const updateTotalSoldValue = (sellQuantity, honeyType, price) => {
        setSoldValues((prevSoldValues) => ({
            ...prevSoldValues,
            [honeyType]: sellQuantity || 0,
        }));
        setSoldValueTotals((prevSoldValueTotals) => ({
            ...prevSoldValueTotals,
            [honeyType]: (sellQuantity * price).toFixed(2),
        }));
    };

    const totalSoldValue = Object.values(soldValueTotals)
        .reduce((acc, val) => acc + Number(val), 0)
        .toFixed(2);

    const handleSubmit = async () => {
        const formattedSoldData = new Map(Object.entries(soldValues));

        try {
            await sendSellHoneyQuantities.updateHoneyStock(formattedSoldData, totalSoldValue);
            console.log('Total honey sold value submitted:', totalSoldValue);
            navigate('/gameView');
        } catch (error) {
            console.error('Error submitting total sold value:', error);
        }
    };

    return (
        <div className="body-sell">
            <div className="container" style={{ marginTop: '50px' }}>
                <RowHeader />
                {honeyData.map(({ honeyType, quantity }) => (
                    <RowText
                        key={honeyType}
                        honeyType={honeyType}
                        quantity={quantity}
                        price={honeyType === "Acacia" ? 6 : 3}
                        onQuantityChange={updateTotalSoldValue}
                    />
                ))}
            </div>

            <h3>Total value of honey sold: ${totalSoldValue}</h3>

            <button className="btn btn-primary mb-3" onClick={handleSubmit}>Submit</button>
            <button className="btn btn-danger button-right-bottom" onClick={() => navigate('/gameView')}>Back</button>
        </div>
    );
};

export default SellHoney;
