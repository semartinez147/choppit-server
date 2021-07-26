import axios from "axios";

export const httpConfig = axios.create();

httpConfig.interceptors.response.use(function ({data, headers, status}) {
    console.log('http status: ', status)
    console.log('http data: ', data)
    if (status < 300) {
        return data !== null
            ? {message: null, data: data, status: 200, type: " alert alert-success", headers: {...headers}}
            : {message: 'no data returned', status: 200, type: " alert alert-success", data: null, headers: {...headers}};
    }
    return {message: data.message, status: data.status, type: "alert alert-danger", data: null, headers: {...headers}}

}, function (error) {
    // Do something with response error
    console.log(error);
    return Promise.reject(error);
});