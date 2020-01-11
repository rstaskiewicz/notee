import React, {useState} from 'react'

import NotesTable from '@notee/layout/NotesTable'
import Avatar from '@notee/components/Avatar'
import Cell from './Cell'
import Column from './Column'
import A from '@notee/elements/A'
import Button from '@notee/elements/Button'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

import {
    faSort as sort,
    faBookmark as bookmark,
} from '@fortawesome/free-solid-svg-icons'

export default ({
        notes,
        faIcon = <FontAwesomeIcon icon={sort} />,
}) => {
        const [isBookmarked, setBookmark] = useState(false);
        return (
                <NotesTable>
                        <table style={{ width: '1632px', tableLayout: 'fixed', textAlign: 'center' }}>
                                <tr>
                                        <Column content="Name" icon={faIcon}></Column>
                                        <Column content="Title" icon={faIcon}></Column>
                                        <Column content="Subject name" icon={faIcon}></Column>
                                        <Column content="Subject code" icon={faIcon}></Column>
                                        <Column content="Date" icon={faIcon}></Column>
                                </tr>
                                {notes.map((note, index) => (
                                        <tr key={index}>
                                                <Cell content={note.user.name}></Cell>
                                                <Cell mod="note-title" content={<A modifiers="list-note" href="#">{note.notebook.title}</A>}></Cell>
                                                <Cell content={note.notebook.code}></Cell>
                                                <Cell content={note.noteTitle}></Cell>
                                                <Cell content={note.date}></Cell>
                                        </tr>
                                ))}
                        </table>
                </NotesTable>
        )
}
