import api from '@notee/api'

export const SEARCH = '@SEARCH'
export const SEARCH_SUCCESS = '@SEARCH_SUCCESS'
export const SEARCH_FAILD = '@SEARCH_FAILD'

const searching = () => ({
    type: SEARCH
})

const searched = payload => ({
    type: SEARCH_SUCCESS,
    payload
})

const searchingError = payload => ({
    type: SEARCH_FAILD,
    payload
})

export const search = title => dispatch => {
    dispatch(searching())
    return api.search.byTitle(title)
        .then(data => {
            dispatch(searched(data))
            return data
        })
        .catch(error => {
            dispatch(searchingError(error))
            return Promise.reject(error)
        })
}
