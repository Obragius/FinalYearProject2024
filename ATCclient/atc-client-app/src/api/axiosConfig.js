import axios from 'axios';

export default axios.create({
    baseURL:"http://jamtech.dev:8080",
    validateStatus: function(status) {return status < 500;},
    headers: {
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Headers': '*',}
})