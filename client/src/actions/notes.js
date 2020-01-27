export const LOAD_ALL_USER_NOTES_REQUEST = '@LOAD_ALL_USER_NOTES_REQUEST'
export const LOAD_ALL_USER_NOTES_SUCCESS = '@LOAD_ALL_USER_NOTES_SUCCESS'
export const LOAD_ALL_USER_NOTES_FAILURE = '@LOAD_ALL_USER_NOTES_FAILURE'

export const loadAllUserNotes = userId => ({
    types: [ LOAD_ALL_USER_NOTES_REQUEST, LOAD_ALL_USER_NOTES_SUCCESS, LOAD_ALL_USER_NOTES_FAILURE ],
    endpoint: `/creators/${userId}/notes/all`,
    payload: { userId }
})

export const LOAD_LAST_USER_NOTES_REQUEST = '@LOAD_LAST_USER_NOTES_REQUEST'
export const LOAD_LAST_USER_NOTES_SUCCESS = '@LOAD_LAST_USER_NOTES_SUCCESS'
export const LOAD_LAST_USER_NOTES_FAILURE = '@LOAD_LAST_USER_NOTES_FAILURE'

export const loadLastUserNotes = userId => ({
    types: [ LOAD_LAST_USER_NOTES_REQUEST, LOAD_LAST_USER_NOTES_SUCCESS, LOAD_LAST_USER_NOTES_FAILURE ],
    endpoint: `/creators/${userId}/notes/last`,
    payload: { userId }
})

export const LOAD_NOTE_REQUEST = '@LOAD_NOTE_REQUEST'
export const LOAD_NOTE_SUCCESS = '@LOAD_NOTE_SUCCESS'
export const LOAD_NOTE_FAILURE = '@LOAD_NOTE_FAILURE'

export const loadNote = noteId => ({
    types: [ LOAD_NOTE_REQUEST, LOAD_NOTE_SUCCESS, LOAD_NOTE_FAILURE ],
    endpoint: `/notes/${noteId}`,
    payload: { noteId }
})


export const CREATE_NOTE_REQUEST = '@CREATE_NOTE_REQUEST'
export const CREATE_NOTE_SUCCESS = '@CREATE_NOTE_SUCCESS'
export const CREATE_NOTE_FAILURE = '@CREATE_NOTE_FAILURE'

export const createNote = values => ({
    types: [ CREATE_NOTE_REQUEST, CREATE_NOTE_SUCCESS, CREATE_NOTE_FAILURE ],
    endpoint: `/notebooks/${values.notebookId}/notes`,
    payload: { ...values },
    requestParams: {},
    method: 'POST'
})

export const EDIT_NOTE_REQUEST = '@EDIT_NOTE_REQUEST'
export const EDIT_NOTE_SUCCESS = '@EDIT_NOTE_SUCCESS'
export const EDIT_NOTE_FAILURE = '@EDIT_NOTE_FAILURE'

export const editNote = (id, values) => ({
    types: [ EDIT_NOTE_REQUEST, EDIT_NOTE_SUCCESS, EDIT_NOTE_FAILURE ],
    endpoint: `/notes/${id}`,
    payload: { ...values },
    requestParams: {},
    method: 'POST'
})
