import AxiosAgent from "./AxiosAgent";

const API_ROOT = "http://localhost:8595/api";


export const saveDevice = (device) => {
    return AxiosAgent.requests.post(API_ROOT + '/device', device);
};

export const findByGateway = (gatewayId) => {
    return AxiosAgent.requests.get(API_ROOT + '/device/list/' + gatewayId);
};





