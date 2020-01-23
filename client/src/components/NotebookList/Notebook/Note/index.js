import React from 'react'
import { Link } from 'react-router-dom'

import Note from '../../../../layout/NotebookList/Note'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
        faChevronRight as chevronRight
} from '@fortawesome/free-solid-svg-icons'

export default ({ noteId, noteName, isClickedState }) => {

    return (
        <Note modifiers={ isClickedState ? 'hidden' : ''}>
            <Note.FontAwesomeIcon><FontAwesomeIcon color="#BBB9C7" icon={chevronRight} /></Note.FontAwesomeIcon>
            <Link to={`/note/${noteId}`}>{noteName}</Link>
        </Note>
    )
}
