import { applyMiddleware, createStore } from 'redux'

import api from './middlewares/api'

import thunk from 'redux-thunk'
import logger from 'redux-logger'

import reducer from './reducers'

const middleware = [
    thunk,
    api,
    process.env.NODE_ENV !== 'production' && logger
]

export default createStore(reducer, applyMiddleware(...middleware))
