import {
    SEARCH,
    SEARCH_SUCCESS,
    SEARCH_FAILD
} from '@notee/actions/search'


const initialState = {
    data: {},
    loading: true,
    error: null
}

export default (state = initialState, { type, payload }) => {

    switch (type) {

        case SEARCH:
            return { ...state, loading: true, error: null }

        case SEARCH_SUCCESS:
            return { ...state, data: payload, loading: false, error: null }

        case SEARCH_FAILD:
            return { ...state, error: payload, loading: false }

        default:
            return state

    }

}
