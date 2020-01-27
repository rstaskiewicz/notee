import {
    SEARCH_REQUEST,
    SEARCH_SUCCESS,
    SEARCH_FAILURE
} from '@notee/actions/search'


const initialState = {
    data: {},
    loading: true,
    error: null
}

export default (state = initialState, { type, payload }) => {

    switch (type) {

        case SEARCH_REQUEST:
            return { ...state, loading: true, error: null }

        case SEARCH_SUCCESS:
            return { ...state, data: { ...payload }, loading: false, error: null }

        case SEARCH_FAILURE:
            return { ...state, error: payload, loading: false }

        default:
            return state

    }

}
