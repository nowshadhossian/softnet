import AxiosAgent from "./AxiosAgent";

const API_ROOT = "http://localhost:8595/api";


export const findAll = () => {
    return AxiosAgent.requests.get(API_ROOT + '/study/list');
};

export const findById = (id) => {
    return AxiosAgent.requests.get(API_ROOT + '/study/' + id);
};

export const save = (studyPatientDto) => {
    return AxiosAgent.requests.post(API_ROOT + '/study', studyPatientDto);
};





