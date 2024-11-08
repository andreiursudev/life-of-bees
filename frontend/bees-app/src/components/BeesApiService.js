import axios from 'axios';

export const createGame = async (gameData) => {
    try {
        const response = await axios.post('http://localhost:8080/api/bees/game', gameData);
        return response.data;
    } catch (error) {
        console.error('Error fetching game data:', error);
        throw error;
    }
};

export const getGame = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/bees/game/0');
        return response.data;
    } catch (error) {
        console.error('Error fetching game data:', error);
        throw error;
    }
};

export const iterateWeek = async () => {
    try {
        const response = await axios.post('http://localhost:8080/api/bees/iterate/0');
        if (response.status !== 200) {
            throw new Error('Failed to iterate week, status: ' + response.status);
        }
        return response.data;
    } catch (error) {
        console.error('Error iterating week:', error);
        throw error;
    }
};

export const submitActionsOfTheWeek = async (actionsData) => {
    console.log('Actions data being sent:', actionsData); 
    try {
        const response = await axios.post('http://localhost:8080/api/bees/submitActionsOfTheWeek/0', actionsData);
        if (response.status !== 200) {
            throw new Error('Failed to submit actions, status: ' + response.status);
        }
        return response.data;
    } catch (error) {
        console.error('Error submitting actions:', error);
        throw error;
    }
};



export const getHoneyQuantities = async () => {
    try {
        const response = await axios.get(`http://localhost:8080/api/bees/getHoneyQuantities/0`);
        return response.data; 
    } catch (error) {
        console.error('Error fetching honey quantities:', error);
        throw error;
    }
};

export const sendSellHoneyQuantities = {
    updateHoneyStock: async (soldData, totalValue) => {
        try {
            // Creează payload-ul
            const payload = { ...soldData, totalValue };
            console.log('Payload din BeesApiService:', JSON.stringify(payload, null, 2));

            const response = await axios.post(`http://localhost:8080/api/bees/sellHoney/0`, payload);
            return response.data;
        } catch (error) {
            console.error('Error updating honey stock:', error);
            throw error;
        }
    },
};


export const buyHives = async (numberOfHives) => {
    try {
        console.log("Number of hives to buy:", numberOfHives);

        const response = await axios.post('http://localhost:8080/api/bees/buyHives/0', {
            numberOfHives: numberOfHives
        }, {
            headers: { 'Content-Type': 'application/json' }
        });
        return response.data;
    } catch (error) {
        console.error('Error buying hives:', error);
        throw error;
    }
};

export const fetchLocations = async (query) => {
    const apiKey = 'tiMHwUADw1sxyLnOfrbf2a6oyXhRHFBe';
    const url = `https://api.os.uk/search/names/v1/find?key=${apiKey}&query=${query}&fq=local_type:City`;

    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Error: ${response.statusText}`);
        }

        const data = await response.json();
        console.log(data); 
        if (data.results && Array.isArray(data.results)) {
            return data.results.map(result => result.GAZETTEER_ENTRY.NAME1);
        } else {
            console.warn('No results found in the API response');
            return [];  
        }
    } catch (error) {
        console.error('Failed to fetch locations:', error);
        return []; 
    }
};



export const getGameInfos = [
    {
        gameName: "Stefan Cel Mare Apiary",
        location: "Suceava, Romania",
        hives: "50000000000000",
        bees: "23456789",
        honey: "76677"
    },
    {
        gameName: "Vlad Tepes Apiary",
        location: "Targoviste, Romania",
        hives: "30000000000000",
        bees: "12345678",
        honey: "56677"
    },
    {
        gameName: "Mihai Viteazul Apiary",
        location: "Alba Iulia, Romania",
        hives: "20000000000000",
        bees: "34567890",
        honey: "46677"
    },
    {
        gameName: "Stefan Cel Mititel Apiary",
        location: "Suceava, Romania",
        hives: "50000000000000",
        bees: "23456789",
        honey: "76677"
    },
    {
        gameName: "Vlad Impaler Apiary",
        location: "Targoviste, Romania",
        hives: "30000000000000",
        bees: "12345678",
        honey: "56677"
    },
    {
        gameName: "Mihai Cel Fricos Apiary",
        location: "Alba Iulia, Romania",
        hives: "20000000000000",
        bees: "34567890",
        honey: "46677"
    },
    {
        gameName: "Mihai Cel Fricos Apiary",
        location: "Alba Iulia, Romania",
        hives: "20000000000000",
        bees: "34567890",
        honey: "46677"
    }
];
