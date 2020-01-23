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

