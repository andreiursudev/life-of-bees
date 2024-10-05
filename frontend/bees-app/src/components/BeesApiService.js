import axios from 'axios';

export const createGame
    = [{
        gameName: "Dacic Apiary",
        location: "Sarmizegetusa, Romania",
        startDate: "1-Apr-2023",
        hives: "5",
    }]

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

export const getGame = [
    {
        hiveId: "1",
        ageOfQueen: "0",
        bees: "12345",
        rapeseedHoney: "136.6kg",
        eggsFrame: "2",
        honeyFrame: "3",
        totalHoney: "316.6kg",
        date: "1-Apr-2024",
        temp: "22°C",
        windSpeed: "3m/s",
        moneyInTheBank: "3000$",
        actions: {
            imageSrc: "../rapeseed-flower.jpg",
            sellHoneyText: "Sell honey",
            buyHivesText: "Buy hives [150$/pc]",
            iterateText: "Iterate/ Go to next week",
            exitText: "Exit"
        }
    }
];


export const sellHoney = [
    {
        rapeseedHoney: "rapeseed: 50",
        acaciaHoney: "acacia: 40",
        lindenHoney: " linden: 50.6",
        wildFlowerHoney: "  wildFlower: 22.8",
        sunFlowerHoney: " sunFlower: 50.9",
        falseIndigoFlowerHoney: "falseIndigo: 50"
    }
];
