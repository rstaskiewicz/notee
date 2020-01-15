import api from '@notee/api'

export const FIND_NOTES = 'FIND_NOTES'
export const FIND_NOTES_SUCCESS = 'FIND_NOTES_SUCCESS'
export const FIND_NOTES_FAILD = 'FIND_NOTES_FAILD'

const findingNotes = () => ({
    type: FIND_NOTES
})

const notesFound = payload => ({
    type: FIND_NOTES_SUCCESS,
    payload
})

const findingNotesError = payload => ({
    type: FIND_NOTES_FAILD,
    payload
})

export const findNotes = filters => dispatch => {
    dispatch(findingNotes())
    return api.notes.findNotes(filters)
        .then(note => {
            dispatch(notesFound(note))
            return note
        })
        .catch(error => {
            dispatch(findingNotesError(error))
            return Promise.reject(error)
        })
}
