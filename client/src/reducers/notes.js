import { mapKeys } from 'lodash'

import {
    LOAD_ALL_USER_NOTES_REQUEST,
    LOAD_ALL_USER_NOTES_SUCCESS,
    LOAD_ALL_USER_NOTES_FAILURE,
    LOAD_LAST_USER_NOTES_REQUEST,
    LOAD_LAST_USER_NOTES_SUCCESS,
    LOAD_LAST_USER_NOTES_FAILURE,
    LOAD_NOTE_REQUEST,
    LOAD_NOTE_SUCCESS,
    LOAD_NOTE_FAILURE
} from '@notee/actions/notes'

const initialState = {
    data: {},
    loading: true,
    error: null
}

export default (state = initialState, { type, payload }) => {
    switch (type) {
        case LOAD_ALL_USER_NOTES_REQUEST:
            return { ...state, loading: true }
        case LOAD_ALL_USER_NOTES_SUCCESS:
            return { ...state, data: { ...state.data, ...mapKeys(payload._embedded.noteDashboardViewList, 'noteId') } , loading: false }
        case LOAD_ALL_USER_NOTES_FAILURE:
            return { ...state, isFetching: false, error: null }
        case LOAD_LAST_USER_NOTES_REQUEST:
            return { ...state, loading: true }
        case LOAD_LAST_USER_NOTES_SUCCESS:
            return { ...state, data: { ...state.data, ...mapKeys(payload._embedded.noteDashboardViewList, 'noteId') } , loading: false }
        case LOAD_LAST_USER_NOTES_FAILURE:
            return { ...state, isFetching: false, error: null }
        case LOAD_NOTE_REQUEST:
            return { ...state, loading: true }
        case LOAD_NOTE_SUCCESS:
            return { ...state, data: { ...state.data, [payload.id]: payload} , loading: false }
        case LOAD_NOTE_FAILURE:
            return { ...state, isFetching: false, error: null }
        default:
            return state
    }
}
