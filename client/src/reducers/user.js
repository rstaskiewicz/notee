import {
    LOAD_USER_REQUEST,
    LOAD_USER_SUCCESS,
    LOAD_USER_FAILURE
} from '@notee/actions/user'

const initialState = {
    data: {
        username: 'Basia Kowalska',
        avatarUrl: 'https://images.unsplash.com/photo-1510227272981-87123e259b17?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=933&q=80'
    },
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
