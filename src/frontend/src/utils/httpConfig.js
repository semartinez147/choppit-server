import axios from "axios";

export const httpConfig = axios.create();

httpConfig.interceptors.response.use(function ({data, headers, status}) {
    let response
    if (status < 300) {
         data !== null
            ? response = {message: null, data: data, status: 200, type: " alert alert-success", headers: {...headers}}
            : response = {message: 'no data returned', status: 200, type: " alert alert-success", data: null, headers: {...headers}};
    } else {
        response = {
            message: data.message,
            status: data.status,
            type: "alert alert-danger",
            data: null,
            headers: {...headers}
        }
    }
    return response;
}, function (error) {
    // Do something with response error
    console.log(error);
    return Promise.reject(error);
});