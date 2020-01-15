import {
    FIND_NOTES,
    FIND_NOTES_SUCCESS,
    FIND_NOTES_FAILD
} from '@notee/actions/notes'

const initialState = {
    data: [],
    loading: true,
    error: null
}

export default (state = initialState, { type, payload }) => {
    switch (type) {

    case FIND_NOTES:
        return { ...state, loading: true, error: null }

    case FIND_NOTES_SUCCESS:
        return { ...state, ...payload, loading: false, authenticated: true }

    case FIND_NOTES_FAILD:
        return { ...state, error: payload, loading: false, authenticated: false }

    default:
        return state
    }
}
