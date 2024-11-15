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


export const iterateWeek = async (requestData) => {
    try {
        const response = await axios.post('http://localhost:8080/api/bees/iterate/0', requestData, {
            headers: {
                'Content-Type': 'application/json'
            }
        });

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
/*
export const fetchWeatherForStartDate = async (location) => {

    const apiUrl = `https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/${location}/2023-03-01/2023-09-30?unitGroup=metric&include=days&key=E62RRYZW29UJG5CTHCU2GE8MW&contentType=json&elements=temp,precip,windspeed`;

    try {
        const response = await fetch(apiUrl, {
            method: 'GET',
        });

        if (!response.ok) {
            throw new Error(`Error: ${response.statusText}`);
        }

        const data = await response.json();
        if (data.days && data.days.length > 0) {
            return data.days.map(day => ({
                date: day.datetime,
                temperature: day.temp,
                precipitation: day.precip,
                windSpeed: day.windspeed
            }));
        } else {
            console.warn('No weather data found for this date range.');
            return null;
        }
    } catch (error) {
        console.error('Failed to fetch weather data:', error);
        return null;
    }
};
*/
export const fakeWeatherData = () => {
    
    const weatherDataMap = {};
    const intervals = [
        { startDate: new Date('2023-03-01'), endDate: new Date('2023-09-30') },
        { startDate: new Date('2024-03-01'), endDate: new Date('2024-09-30') }
    ];
  
    intervals.forEach(({ startDate, endDate }) => {
        for (let date = new Date(startDate); date <= endDate; date.setDate(date.getDate() + 1)) {
            const currentDay = date.getDate(); 
            const temperature = 10 + (currentDay * 0.5);   
            const precipitation = (currentDay * 0.3);     
            const windSpeed = (currentDay * 0.2);        
            const weatherData = {
                temperature: Number(temperature.toFixed(2)), 
                precipitation: Number(precipitation.toFixed(2)), 
                windSpeed: Number(windSpeed.toFixed(2))     
            };

            weatherDataMap[date.toISOString().split('T')[0]] = weatherData;
        }
    });

    return weatherDataMap;
};




/*
const API_KEY_OPENWEATHER = "87a529705c546bd85481e69cf6e37217";
const BASE_URL_OPENWEATHER = "http://api.openweathermap.org/data/2.5/weather";
export const fetchLocationId = async (location) => {
    try {
        const response = await axios.get(BASE_URL_OPENWEATHER, {
            params: {
                q: location,
                appid: API_KEY_OPENWEATHER
            }
        });

        if (response.data && response.data.id) {
            return response.data.id; 
        } else {
            console.error("No data found for the specified location.");
            return null;
        }
    } catch (error) {
        console.error("Error fetching location ID:", error);
        return null;
    }
};
    

const NOAA_API_TOKEN = "lfsDeMRaiEfgZddpFwUthPjpTmrATwyZ"; 

export const fetchWeatherForStartDate = async (location, startDate) => {

   // const stationId = await fetchLocationId(location);
    
   const stationId = 2651123;

    if (!stationId) {
        console.error("No station ID found for location:", location);
        return null;
    }

    const endDate = new Date().toISOString().split('T')[0]; 
    const apiUrl = `https://www.ncei.noaa.gov/cdo-web/api/v2/data?stationid=${stationId}&datasetid=GHCND&startdate=${startDate}&enddate=${endDate}&limit=1000&datatypeid=TMIN&datatypeid=TMAX&datatypeid=PRCP&datatypeid=AWND`;

    const startRange = new Date(`${new Date().getFullYear()}-03-01`);
    const endRange = new Date(`${new Date().getFullYear()}-09-30`);

    try {
        const response = await fetch(apiUrl, {
            headers: {
                'token': NOAA_API_TOKEN, 
            }
        });

        if (!response.ok) {
            throw new Error(`Error: ${response.statusText}`);
        }

        const data = await response.json();

        if (data.results && data.results.length > 0) {
            const filteredData = data.results
                .filter(record => {
                    const currentDate = new Date(record.date);
                    return currentDate >= startRange && currentDate <= endRange;
                })
                .reduce((acc, record) => {
                    const date = record.date.split('T')[0];
                    if (!acc[date]) {
                        acc[date] = { temperature: null, precipitation: null, windSpeed: null };
                    }
                    switch (record.datatype) {
                        case 'TMIN':
                        case 'TMAX':
                            acc[date].temperature = (acc[date].temperature || 0) + record.value / 10; 
                            break;
                        case 'PRCP':
                            acc[date].precipitation = record.value / 10; 
                            break;
                        case 'AWND':
                            acc[date].windSpeed = record.value / 10; 
                            break;
                    }
                    return acc;
                }, {});

            return Object.entries(filteredData).map(([date, { temperature, precipitation, windSpeed }]) => ({
                date,
                temperature,
                precipitation,
                windSpeed
            }));
        } else {
            console.warn('No weather data found for this date range.');
            return null;
        }
    } catch (error) {
        console.error('Failed to fetch weather data:', error);
        return null;
    }
};
*/

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
