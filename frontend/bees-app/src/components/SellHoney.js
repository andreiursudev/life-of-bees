import React, { useState, useEffect } from 'react';
import '../App.css';
import { useNavigate } from 'react-router-dom';
import { getHoneyQuantities } from './BeesApiService';

const RowHeader = () => (
    <div className="row-text">
        <p className="btn-custom-sell mb-2">Honey Type</p>
        <p className="btn-custom-sell mb-2">Quantity[kg]</p>
        <p className="btn-custom-sell mb-2">Price[$/kg]:</p>
        <p className="btn-custom-sell mb-2">You sell:</p>
    </div>
);

const RowText = ({ honeyType, quantity, price }) => (
    <div className="row-text">
        <p className="btn-custom-sell mb-2">{honeyType}</p>
        <p className="btn-custom-sell mb-2">{quantity}</p>
        <p className="btn-custom-sell mb-2">{price}</p>
        <form>
            <input type="text" className="input-custom" id="name" name="name" />
        </form>
    </div>
);

const SellHoney = () => {
    const [honeyData, setHoneyData] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchHoneyData = async () => {
            try {
                const data = await getHoneyQuantities();
                setHoneyData(data[0]); // presupunând că răspunsul este un array și vrem primul element
            } catch (error) {
                console.error('Error fetching honey data:', error);
            }
        };
        fetchHoneyData();
    }, []);

    if (!honeyData) {
        return <p>Loading honey data...</p>;
    }

    return (
        <div className="body-sell">
            <h1>Total honey: 450kg</h1>

            <div className="container" style={{ marginTop: '50px' }}>
                <RowHeader />

                {honeyData.rapeseed !== "0" && (
                    <RowText honeyType="Rapeseed" quantity={honeyData.rapeseed} price="3" />
                )}
                {honeyData.acacia !== "0" && (
                    <RowText honeyType="Acacia" quantity={honeyData.acacia} price="6" />
                )}
                {honeyData.linden !== "0" && (
                    <RowText honeyType="Linden" quantity={honeyData.linden} price="3" />
                )}
                {honeyData.wildFlower !== "0" && (
                    <RowText honeyType="Wild Flower" quantity={honeyData.wildFlower} price="3" />
                )}
                {honeyData.sunFlower !== "0" && (
                    <RowText honeyType="Sun Flower" quantity={honeyData.sunFlower} price="3" />
                )}
                {honeyData.falseIndigoFlower !== "0" && (
                    <RowText honeyType="False Indigo Flower" quantity={honeyData.falseIndigoFlower} price="3" />
                )}
            </div>

            <button className="btn btn-danger btn-custom mb-2" onClick={() => navigate('/gameView')}>Back</button>
        </div>
    );
};

export { RowText };
export default SellHoney;
