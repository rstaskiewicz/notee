import axios from 'axios'

const API_ROOT = 'http://localhost:8080/api'

export const POST_METHOD = 'POST'
export const PUT_METHOD = 'PUT'
export const DELETE_METHOD = 'DELETE'

const getCall = (url, config) => axios.get(url, config)

const postCall = (url, config, payload) => axios.post(url, payload, { method: 'POST', ...config })

const putCall = (url, config, payload) => {}

const deleteCall = (url, config) => {}

export default (endpoint, requestParams, token, method, payload) => {
    const url = `${API_ROOT}${endpoint}`
    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
            Accept: 'application/json',
            'Content-Type': 'application/json'
        }
    }
    switch (method) {
        case POST_METHOD:
            return postCall(url, config, payload)
        case PUT_METHOD:
            return putCall(url, config, payload)
        case DELETE_METHOD:
            return deleteCall(url, config)
        default:
            return getCall(url, config)
    }
}
