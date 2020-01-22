import {
    LOAD_ALL_USER_NOTES_REQUEST,
    LOAD_ALL_USER_NOTES_SUCCESS,
    LOAD_ALL_USER_NOTES_FAILURE,
    LOAD_LAST_USER_NOTES_REQUEST,
    LOAD_LAST_USER_NOTES_SUCCESS,
    LOAD_LAST_USER_NOTES_FAILURE
} from '@notee/actions/notes'

const initialState = {
    data: [],
    loading: true,
    error: null
}

export default (state = initialState, { type, payload }) => {
    switch (type) {
        case LOAD_ALL_USER_NOTES_REQUEST:
            return { ...state, loading: true }
        case LOAD_ALL_USER_NOTES_SUCCESS:
            return { ...state, data: payload._embedded.noteDashboardViewList , loading: false }
        case LOAD_ALL_USER_NOTES_FAILURE:
            return { ...state, isFetching: false, error: null }
        case LOAD_LAST_USER_NOTES_REQUEST:
            return { ...state, loading: true }
        case LOAD_LAST_USER_NOTES_SUCCESS:
            return { ...state, data: payload._embedded.noteDashboardViewList , loading: false }
        case LOAD_LAST_USER_NOTES_FAILURE:
            return { ...state, isFetching: false, error: null }
        default:
            return state
    }
}
