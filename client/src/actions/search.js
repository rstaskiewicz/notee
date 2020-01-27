import api from '@notee/api'

export const SEARCH_REQUEST = '@SEARCH_REQUEST'
export const SEARCH_SUCCESS = '@SEARCH_SUCCESS'
export const SEARCH_FAILURE = '@SEARCH_FAILURE'

export const search = title => dispatch => {
    dispatch({ type: SEARCH_REQUEST })

    return api.search.byTitle(title)
        .then(data => dispatch({
            type: SEARCH_SUCCESS,
            payload: data
        }))
        .catch(error => (dispatch({
            type: SEARCH_FAILURE,
            payload: error
        })))

}
