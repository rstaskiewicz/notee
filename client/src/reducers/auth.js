import {
    SIGN_IN_REQUEST,
    SIGN_IN_SUCCESS,
    SIGN_IN_FAILURE,
    SIGN_OUT
} from '@notee/actions/auth'

const initialState = {
    data: {
        token: localStorage.getItem('token'),
        userId: localStorage.getItem('userId')
    },
    loading: true,
    error: null
}

export default (state = initialState, { type, payload }) => {
    switch (type) {
        case SIGN_IN_REQUEST:
            return { ...state, loading: true }
        case SIGN_IN_SUCCESS:
            return { ...state, data: { ...payload }, loading: false }
        case SIGN_IN_FAILURE:
            return { ...state, loading: false, error: payload }
        case SIGN_OUT:
            return { ...state, data: {} }
        default:
            return state
    }
}
