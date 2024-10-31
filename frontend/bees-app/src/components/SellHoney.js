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

// Componenta RowText din SellHoney.js
const RowText = ({ honeyType, quantity, price, onQuantityChange }) => {
    const [sellQuantity, setSellQuantity] = useState(0); // Setează valoarea inițială la 0

    const handleInputChange = (event) => {
        const value = Math.max(0, Math.min(Number(event.target.value), quantity)) || 0; // Asigură-te că este numeric
        setSellQuantity(value);
        onQuantityChange(value, honeyType); // Trimite valoarea vânzării și tipul mierii
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
    const navigate = useNavigate();

    useEffect(() => {
        const fetchHoneyData = async () => {
            try {
                const data = await getHoneyQuantities();
                // Transforma map-ul într-o listă de obiecte cu `honeyType` și `quantity`
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


    const updateTotalSoldValue = (sellQuantity, honeyType) => {
        setSoldValues((prevSoldValues) => ({
            ...prevSoldValues,
            [honeyType]: sellQuantity || 0, // Cantitatea, nu valoarea totală
        }));
    };
    


    const totalSoldValue = Number(
        Object.values(soldValues).reduce((acc, val) => acc + (val || 0), 0)
    ).toFixed(2);


    const handleSubmit = async () => {
        // Creăm un Map de tip { honeyType: quantity } similar cu Map<String, Object>
        const formattedSoldData = new Map(Object.entries(soldValues));

        try {
            // Trimitem Map-ul către backend
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
