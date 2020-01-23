import React, { useEffect } from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faHeart as heart
} from '@fortawesome/free-regular-svg-icons'

import Avatar from '@notee/components/Avatar'
import { Body } from '@notee/layout/containers/List'
import { P, Button } from '@notee/elements'
import { loadAllUserNotes } from '@notee/actions/notes'
import {loadUser} from '@notee/actions/user'

export default () => {

    const { userId } = useSelector(({ auth }) => auth.data)
    const user = useSelector(state => state.user)
    const notes = useSelector(({ notes }) => Object.values(notes.data))
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(loadUser(userId))
        dispatch(loadAllUserNotes(userId))
    }, [ dispatch ])

    return (
        <Body>
            {notes.map(({ noteId, noteName, createdBy, createdAt, notebookName }) => (
                <Body.Row key={noteId}>

                    <Body.Item>
                        <Body.Avatar>
                            <Avatar image={user.avatarUrl} />
                        </Body.Avatar>
                    </Body.Item>

                    <Body.Item>
                        {createdBy}
                    </Body.Item>

                    <Body.Item>
                        <P modifiers="darker">{noteName}</P>
                    </Body.Item>

                    <Body.Item>{notebookName}</Body.Item>

                    <Body.Item>
                        {createdAt}
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
