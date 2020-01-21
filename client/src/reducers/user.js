import {
    LOAD_USER_REQUEST,
    LOAD_USER_SUCCESS,
    LOAD_USER_FAILURE
} from '@notee/actions/user'

const initialState = {
    data: {},
    loading: true,
    error: null
}

export default (state = initialState, { type, payload }) => {
    switch (type) {
        case LOAD_USER_REQUEST:
            return { ...state, loading: true, error: null }
        case LOAD_USER_SUCCESS:
            return { ...state, data: { ...payload }, loading: false }
        case LOAD_USER_FAILURE:
            return { ...state, error: payload, loading: false, authenticated: false }
        default:
            return state
    }
}
