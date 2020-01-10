import { combineReducers } from 'redux'

import user from './user'
import setNoteTitle from './setNoteTitle'

export default combineReducers({
    user, setNoteTitle
})
