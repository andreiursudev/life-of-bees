import axios from 'axios';

export const createGame
    = (game) =>  1

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

export const getGame(id) =>await axios.post('http://localhost:8080/api/game', id);
export const getGame={
    hives: [
        {
            id: 1,
            ageOfQueen: "0",
            bees: "12345",
            rapeseedHoney: "136.6",
            eggsFrame: "2",
            honeyFrame: "3",
            totalHoney: "316.6",
        },
        {
            id: 2,
            ageOfQueen: "0",
            bees: "12345",
            rapeseedHoney: "136.6",
            eggsFrame: "2",
            honeyFrame: "3",
            totalHoney: "316.6",
        }],
    action: {
        name: "Insect control"
    },
    date: "1-Apr-2024",
    temp: "22",
    windSpeed: "3",
    moneyInTheBank: "3000",
    flower: "rapeseed"

}



export const sellHoney = [
    {
        rapeseed: "50",
        acacia: "40",
        linden: "50.6",
        wildFlower: "22.8",
        sunFlower: "50.9",
        falseIndigoFlower: "50"
    }
];
