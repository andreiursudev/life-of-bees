import React, { Component } from 'react';
import '../App.css';
import { useNavigate } from 'react-router-dom';

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
        return (
            <div className="row-text">
                <p className="btn-custom-sell mb-2">rapeseed:</p>
                <p className="btn-custom-sell mb-2">3</p>
                <form>
                    <input type="text" className="input-custom" id="name" name="name" />
                </form>
            </div>
        );
    }
}
const SellHoney = () => {
    const navigate = useNavigate();
    return (
        <div className="body-sell">
            <h1>Total honey: 450kg</h1>

            <div className="container" style={{ marginTop: '50px' }}>
                <RowHeader />
                <RowText />
                <RowText />
                <RowText />
                <RowText />
                <RowText />
                <RowText />
            </div>

            <button className="btn btn-danger btn-custom mb-2" onClick={() => navigate('/home')}>Back</button>
        </div>
    );
};

export { RowText };
export default SellHoney; 
