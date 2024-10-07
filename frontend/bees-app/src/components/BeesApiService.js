import axios from 'axios';

export const createGame = async (gameData) => {
    try {
        const response = await axios.post('http://localhost:8080/api/bees/create-hives', gameData);
        return response.data;
    } catch (error) {
        console.error('Error fetching game data:', error);
        throw error;
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


export const getGame = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/bees/create-hives'); 
        return response.data;
    } catch (error) {
        console.error('Error fetching game data:', error);
        throw error;
    }
};



export const sellHoney = [
    {
        rapeseed: "50",
        acacia: "30",
        linden: "50.6",
        wildFlower: "22.8",
        sunFlower: "50.9",
        falseIndigoFlower: "10"
    }
];
