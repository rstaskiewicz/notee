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
        return { ...state, data: payload, loading: false, error: null }

    case FIND_NOTES_FAILD:
        return { ...state, error: payload, loading: false }

    default:
        return state
    }
}
