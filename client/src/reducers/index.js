import { combineReducers } from 'redux'

import auth from './auth'
import user from './user'
import notes from './notes'
import notebooks from './notebooks'

export default combineReducers({
    auth,
    user,
    notes,
    notebooks
})
