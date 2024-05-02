import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/autofix/vehiculos/');
}

const create = data => {
    return httpClient.post('/api/autofix/vehiculos/', data);
}

const get = (id) => {
    return httpClient.get(`/api/autofix/vehiculos/${id}`);
}

const update = data => {
    return httpClient.put('/api/autofix/vehiculos/', data);
}

const remove = (id) => {
    return httpClient.delete(`/api/autofix/vehiculos/${id}`);
}

export default {getAll,create,get,update,remove};