import React, { useEffect } from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faHeart as heart
} from '@fortawesome/free-regular-svg-icons'

import Avatar from '@notee/components/Avatar'
import { Body } from '@notee/layout/containers/List'
import { P, Button } from '@notee/elements'
import { findNotes } from '@notee/actions/notes'
import {loadUser} from '@notee/actions/user'

export default () => {

    const { userId } = useSelector(state => state.auth.data)
    const user = useSelector(state => state.user)
    const notes = useSelector(state => state.notes)
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(loadUser(userId))
        dispatch(findNotes())
    }, [ dispatch ])

    return (
        <Body>
            {notes.data.map((note, index) => (
                <Body.Row key={index}>

                    <Body.Item>
                        <Body.Avatar>
                            <Avatar image={user.avatarUrl} />
                        </Body.Avatar>
                    </Body.Item>

                    <Body.Item>
                        Joanna Kowalska
                    </Body.Item>

                    <Body.Item>
                        <P modifiers="darker">{note.title}</P>
                    </Body.Item>

                    <Body.Item>{note.notebook.title}</Body.Item>

                    <Body.Item>
                        5 December 2020
                    </Body.Item>

                    <Body.Item modifiers="justify-center">
                        <Button.Icon>
                            <FontAwesomeIcon icon={heart} fixedWidth />
                        </Button.Icon>
                    </Body.Item>

                </Body.Row>
            ))}
        </Body>
    )

}
