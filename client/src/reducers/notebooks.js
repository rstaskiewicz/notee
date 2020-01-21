import {
    LOAD_USER_NOTEBOOKS_REQUEST,
    LOAD_USER_NOTEBOOKS_SUCCESS,
    LOAD_USER_NOTEBOOKS_FAILURE
} from '@notee/actions/notebooks'

const initialState = {
    data: [],
    loading: true,
    error: null
}

export default (state = initialState, { type, payload }) => {
    switch (type) {
        case LOAD_USER_NOTEBOOKS_REQUEST:
            return { ...state, loading: true }
        case LOAD_USER_NOTEBOOKS_SUCCESS:
            return { ...state, data: { ...state.items, ...payload._embedded.notebookViewList }, loading: false }
        case LOAD_USER_NOTEBOOKS_FAILURE:
            return { ...state, isFetching: false, error: null }
        default:
            return state
    }
}
