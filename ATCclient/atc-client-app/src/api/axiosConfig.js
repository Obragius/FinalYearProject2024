import axios from 'axios';

export default axios.create({
    baseURL:"http://localhost:8080",
    validateStatus: function(status) {return status < 500;},
    headers: {
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Headers': '*',}
})