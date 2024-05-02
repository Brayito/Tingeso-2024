import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/autofix/reparaciones/');
}

const create = data => {
    return httpClient.post('/api/autofix/reparaciones/', data);
}

const get = (id) => {
    return httpClient.get(`/api/autofix/reparaciones/${id}`);
}

const update = data => {
    return httpClient.put('/api/autofix/reparaciones/', data);
}

const remove = (id) => {
    return httpClient.delete(`/api/autofix/reparaciones/${id}`);
}

export default {getAll,create,get,update,remove};