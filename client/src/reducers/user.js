import {
    SIGN_UP_USER,
    SIGN_UP_USER_SUCCESS,
    SIGN_UP_USER_FAILD,
    SIGN_IN_USER,
    SIGN_IN_USER_SUCCESS,
    SIGN_IN_USER_FAILD
} from '@notee/actions/user'


const initialState = {
    id: '',
    avatar: '',
    name: '',
    mail: '',
    authenticated: false,
    loading: true,
    error: null
}

export default (state = initialState, { type, payload }) => {

    switch (type) {

        case SIGN_UP_USER:
            return { ...state, loading: true, error: null }

        case SIGN_UP_USER_SUCCESS:
            return { ...state, ...payload, loading: false, authenticated: true }

        case SIGN_UP_USER_FAILD:
            return { ...state, error: payload, loading: false, authenticated: false }

        case SIGN_IN_USER:
            return { ...state, loading: true, error: null }

        case SIGN_IN_USER_SUCCESS:
            return { ...state, ...payload, loading: false, authenticated: true }

        case SIGN_IN_USER_FAILD:
            return { ...state, error: payload, loading: false, authenticated: false }

        default:
            return state

    }

}
