import React from 'react';
import '../App.css';


const SellHoney = () => {
    return (
        <div className="container body-sell">
            <h1>Total honey: 450kg</h1>


            <div class="container" style="margin-top: 50px;">
                <div class="row">
                    <div class="col-md-3">
                        <button class="btn btn-custom-sell mb-2">Honey[kg]:</button>
                        <button class="btn btn-custom-sell mb-2">rapeseed:</button>
                        <button class="btn btn-custom-sell mb-2">acacia:</button>
                        <button class="btn btn-custom-sell mb-2">linden:</button>
                        <button class="btn btn-custom-sell mb-2">wild flower:</button>
                        <button class="btn btn-custom-sell mb-2">sunflower:</button>
                        <button class="btn btn-custom-sell mb-2">false indigo:</button>
                    </div>

                    <div class="col-md-3">
                        <button class="btn btn-custom-sell mb-2">Price[$/kg]:</button>
                        <button class="btn btn-custom-sell mb-2">3</button>
                        <button class="btn btn-custom-sell mb-2">6</button>
                        <button class="btn btn-custom-sell mb-2">3</button>
                        <button class="btn btn-custom-sell mb-2">3</button>
                        <button class="btn btn-custom-sell mb-2">3</button>
                        <button class="btn btn-custom-sell mb-2">3</button>
                    </div>

                    <div class="col-md-3">
                        <button class="btn btn-custom-sell mb-2">You sell:</button>
                        <button class="btn btn-custom-sell mb-2">30</button>
                        <button class="btn btn-custom-sell mb-2">60</button>
                        <button class="btn btn-custom-sell mb-2">30</button>
                        <button class="btn btn-custom-sell mb-2">30</button>
                        <button class="btn btn-custom-sell mb-2">30</button>
                        <button class="btn btn-custom mb-2">30</button>
                        <button class="btn btn-danger mb-3">Total honey[kg]:210</button>

                    </div>
                </div>
            </div>

            <button class="button-right-bottom" onclick="location.href='gameView.html'">Back</button>
        </div>



    );
};

            export default SellHoney;