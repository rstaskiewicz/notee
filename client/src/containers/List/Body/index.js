import React, { useEffect } from 'react'
import { useSelector, useDispatch } from 'react-redux'

import Avatar from '@notee/components/Avatar'
import { Body } from '@notee/layout/containers/List'
import { findNotes } from '@notee/actions/notes'

export default () => {

    const user = useSelector(state => state.user)
    const notes = useSelector(state => state.notes)
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(findNotes())
    }, [ dispatch ])

    return (
        <Body>
            {notes.data.map((note, index) => (
                <Body.Row key={index}>
                    <Body.Item >
                        <Avatar image={user.avatar} />
                        {/* <P>{user.name}</P> */}
                        Joanna Kowalska
                    </Body.Item>
                    <Body.Item modifiers="justify-start">
                        {note.title}
                    </Body.Item>
                    <Body.Item>{note.notebook.title}</Body.Item>
                    <Body.Item>
                        {note.labels.map(label => (
                            <p key={label.id}>{label.name}</p>
                        ))}
                    </Body.Item>
                </Body.Row>
            ))}
        </Body>
    )

}
