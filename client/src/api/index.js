import axios from 'axios'

import auth from './auth'
import user from './user'
import notes from './notes'

const api = axios.create({
    baseURL: 'http://localhost:8080'
})

const setHeader = api => (key, value) => {
    api.defaults.headers[key] = value
    return api
}

const removeHeader = api => key => {
    delete api.defaults.headers[key]
    return api
}

export default {
    auth: auth(api),
    user: user(api),
    notes: notes(api),
    setHeader: setHeader(api),
    removeHeader: removeHeader(api)
}
