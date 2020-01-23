import React, {useState} from 'react'
import Notebook from '../../../layout/NotebookList/Notebook'
import Note from './Note'
import P from '../../../elements/P'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
        faChevronDown as chevronDown,
        faChevronLeft as chevronLeft
} from '@fortawesome/free-solid-svg-icons'


export default ({ notebookName, notes }) => {

    const [ isClicked, setIsClicked ] = useState(true)

    return (
        <Notebook >
            <Notebook.P><P onClick={() => setIsClicked(!isClicked)}>{notebookName}<Notebook.FontAwesomeIcon><FontAwesomeIcon color="#BBB9C7" icon={ isClicked ? chevronLeft : chevronDown} /></Notebook.FontAwesomeIcon></P></Notebook.P>
            {notes.map(({ noteId, noteName }) => (
                <Note key={noteId} noteId={noteId} noteName={noteName} isClickedState={isClicked}/>
            ))}
        </Notebook>
    )
}
