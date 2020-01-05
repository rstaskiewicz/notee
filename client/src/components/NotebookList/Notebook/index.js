import React, {useState} from 'react'
import Notebook from '../../../layout/NotebookList/Notebook'
import Note from './Note'
import P from '../../../elements/P'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
        faChevronDown as chevronDown,
        faChevronLeft as chevronLeft,
} from '@fortawesome/free-solid-svg-icons'


export default ({
    title,
    notes,

}) => {

    const [ isClicked, setIsClicked] = useState(true)

    return (
        
        <Notebook >
            <Notebook.P><P onClick={() => setIsClicked(!isClicked)}>{title}<Notebook.FontAwesomeIcon><FontAwesomeIcon color="#BBB9C7" icon={ isClicked ? chevronLeft : chevronDown} /></Notebook.FontAwesomeIcon></P></Notebook.P>
            {notes.map((note, index) => (
                <Note key={index} title={note.title} isClickedState={isClicked}/>
            ))}
        </Notebook>
    )
}
