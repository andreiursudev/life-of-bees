import React, { useRef } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage';
import GameView from'./components/GameView';
import SellHoney from './components/SellHoney';



function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<HomePage/>} />
          <Route exact path="/another-page" component={<GameView/>}/>
          <Route exact path="/another-page" component={<SellHoney/>}/>
          
        </Routes>
      </div>
    </Router>
  );
}

export default App;
