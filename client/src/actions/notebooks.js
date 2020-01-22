export const LOAD_USER_NOTEBOOKS_REQUEST = '@LOAD_USER_NOTEBOOKS_REQUEST'
export const LOAD_USER_NOTEBOOKS_SUCCESS = '@LOAD_USER_NOTEBOOKS_SUCCESS'
export const LOAD_USER_NOTEBOOKS_FAILURE = '@LOAD_USER_NOTEBOOKS_FAILURE'

export const loadUserNotebooks = userId => ({
    types: [ LOAD_USER_NOTEBOOKS_REQUEST, LOAD_USER_NOTEBOOKS_SUCCESS, LOAD_USER_NOTEBOOKS_FAILURE ],
    endpoint: `/creators/${userId}/notebooks?showNotes=true`,
    payload: { userId }
})
