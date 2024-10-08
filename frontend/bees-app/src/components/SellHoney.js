import React, { Component } from 'react';
import '../App.css';
import { useNavigate } from 'react-router-dom';
import { sellHoney } from './BeesApiService';

class RowHeader extends Component {
    render() {
        return (
            <div className="row-text">
                <p className="btn-custom-sell mb-2">Honey Type</p>
                <p className="btn-custom-sell mb-2">Quantity[kg]</p>
                <p className="btn-custom-sell mb-2">Price[$/kg]:</p>
                <p className="btn-custom-sell mb-2">You sell:</p>
            </div>
        );
    }
}

class RowText extends Component {
    render() {
        const { honeyType, quantity, price } = this.props;
        return (
            <div className="row-text">
                <p className="btn-custom-sell mb-2">{honeyType}</p>
                <p className="btn-custom-sell mb-2">{quantity}</p>
                <p className="btn-custom-sell mb-2">{price}</p>
                <form>
                    <input type="text" className="input-custom" id="name" name="name" />
                </form>
            </div>
        );
    }
}

const SellHoney = () => {
    const navigate = useNavigate();
    const honeyData = sellHoney[0];
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
