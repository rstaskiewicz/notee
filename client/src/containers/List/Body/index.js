import React, { useEffect } from 'react'
import { useSelector } from 'react-redux'

import Body from '@notee/layout/containers/List/Body'

import { findNotes } from '@notee/actions/notes'

export default () => {

    const user = useSelector(state => state.user)
    const notes = useSelector(state => state.notes)

    useEffect(() => {
        findNotes()
    }, [])

    return (
        <Body>
            {notes.data.map(note => (
                <Body.Row key={note.id}>
                    <Body.Item>{user.name}</Body.Item>
                    <Body.Item>{note.title}</Body.Item>
                </Body.Row>
            ))}
        </Body>
    )

}
