export const LOAD_USER_REQUEST = '@LOAD_USER_REQUEST'
export const LOAD_USER_SUCCESS = '@LOAD_USER_SUCCESS'
export const LOAD_USER_FAILURE = '@LOAD_USER_FAILURE'

export const loadUser = id => ({
    types: [ LOAD_USER_REQUEST, LOAD_USER_SUCCESS, LOAD_USER_FAILURE ],
    endpoint: `/profiles/${id}`,
    payload: { id }
})
