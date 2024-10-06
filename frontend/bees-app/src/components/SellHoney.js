import React, { Component } from 'react';
import '../App.css';
import { useNavigate } from 'react-router-dom';
import { sellHoney } from './BeesApiService';  

class RowHeader extends Component {
    render() {
        return (
            <div className="row-text">
                <p className="btn-custom-sell mb-2">Honey[kg]:</p>
                <p className="btn-custom-sell mb-2">Price[$/kg]:</p>
                <p className="btn-custom-sell mb-2">You sell:</p>
            </div>
        );
    }
}

class RowText extends Component {
    render() {
        const { honeyType, price } = this.props;  
        return (
            <div className="row-text">
                <p className="btn-custom-sell mb-2">{honeyType}</p>
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
    const honeyData = sellHoney[0];  // Accesează datele din BeesApiService

    return (
        <div className="body-sell">
            <h1>Total honey: 450kg</h1>

            <div className="container" style={{ marginTop: '50px' }}>
                <RowHeader />
                if(rapeseed > 0){
                    <RowText honeyType={honeyData.rapeseed} price="3"/>
                }
                <RowText honeyType={honeyData.acaciaHoney} price="6" />
                <RowText honeyType={honeyData.lindenHoney} price="3" />
                <RowText honeyType={honeyData.wildFlowerHoney} price="3" />
                <RowText honeyType={honeyData.sunFlowerHoney} price="3" />
                <RowText honeyType={honeyData.falseIndigoFlowerHoney} price="3" />
            </div>

            <button className="btn btn-danger btn-custom mb-2" onClick={() => navigate('/home')}>Back</button>
        </div>
    );
};

export { RowText };
export default SellHoney;
