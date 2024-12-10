import React, { useState, useEffect } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage';
import GameView from './components/GameView';
import SellHoney from './components/SellHoney';
import { GoogleOAuthProvider, GoogleLogin } from '@react-oauth/google';

import { getGoogleClientId } from './components/BeesApiService';

function App() {
  const [googleClientId, setGoogleClientId] = useState(null);
  const handleGoogleLogin = (response) => {
    fetch('/api/auth/oauth/google', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        token: response.credential, 
      }),
    })
    .then(res => res.json())
    .then(data => {
      console.log('User authenticated:', data);
    })
    .catch(error => console.error('Error during Google OAuth login:', error));
  };
  useEffect(() => {
    const fetchGoogleClientId = async () => {
      try {
        const clientId = await getGoogleClientId();
        setGoogleClientId(clientId);
      } catch (error) {
        console.error('Failed to fetch Google Client ID:', error);
      }
    };

    fetchGoogleClientId();
  }, []);

  if (!googleClientId) {
    return <div>Loading...</div>;
  }

  return (
    <GoogleOAuthProvider clientId={googleClientId}>
      <Router>
        <div className="App">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/gameView" element={<GameView />} />
            <Route path="/sell-honey" element={<SellHoney />} />
          </Routes>
          <GoogleLogin 
            onSuccess={handleGoogleLogin}
            onError={() => console.log('Login Failed')} 
          />
        </div>
      </Router>
    </GoogleOAuthProvider>
  );
}

export default App;
