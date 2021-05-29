import axios from 'axios';


axios.defaults.headers.post['Content-Type'] = 'application/json';


const requests = {
    get: (url) => {
    return axios.get(url)
        .then(function (response) {
            return requestUtil.handleRequest(response);
        })
        .catch(function (error) {
            return requestUtil.errorHandling(error);
        })
        .finally(function () {
            // always executed
        });
},
post: (url, param) => {
    return axios.post(url, param)
        .then(function (response) {
            // handle success
            return requestUtil.handleRequest(response);
        })
        .catch(function (error) {
            // handle error
            console.log("Error: ", error);
            return requestUtil.errorHandling(error);
        })
        .finally(function () {
            // always executed
        });
},
put: (url, param) => {
    return axios.put(url, param)
        .then(function (response) {
            // handle success
            return requestUtil.handleRequest(response);
        })
        .catch(function (error) {
            // handle error
            console.log(error);
            return requestUtil.errorHandling(error);
        })
        .finally(function () {
            // always executed
        });
},
delete: (url) => {
    return axios.delete(url)
        .then(function (response) {
            // handle success
            return requestUtil.handleRequest(response);
        })
        .catch(function (error) {
            // handle error
            console.log("Error: ", error);
            return requestUtil.errorHandling(error);
        })
        .finally(function () {
            // always executed
        });
},
};

const requestUtil = {
    handleRequest: (response) => {
    const contentType = response.headers["content-type"];
if (contentType && contentType.indexOf("json") !== -1) {
    return response.data;
} else if (contentType && contentType.indexOf("text/plain") !== -1) {
    return response.data;
} else if (contentType && contentType.indexOf("text/xml") !== -1) {
    return response.blob().then(data => data);
} else {
    return {"errorMayBe": "I guess so"};
}
},
errorHandling: (error) => {
    return {
        data: {
            type: "serverError",
            payload: {
                message: "Server Error Happened " + error
            }
        },
        response: error.response
    };
}
};

export default {requests,};
