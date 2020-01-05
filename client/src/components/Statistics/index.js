import React from 'react'

import Statistics from '../../layout/Statistics'
import Label from '../../layout/Statistics/Label'
import Segregator from '../../layout/Statistics/Segregator'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faFileAlt as file,
    faUsers as users,
} from '@fortawesome/free-solid-svg-icons'

export default ({
    createdNoteCount = 0,
    savedNoteCount = 0,
    userRegisteredCount = 0,
}) => {

    return (
        <Statistics>
            <Segregator modifiers="notes">
                <FontAwesomeIcon color="#BBB9C7" icon={file} />
                <Label >
                    {createdNoteCount} created and {savedNoteCount} saved notes
                </Label>
            </Segregator>
            <Segregator>
                <FontAwesomeIcon color="#BBB9C7" icon={users} />
                <Label>
                    {userRegisteredCount} registered users
                </Label>
            </Segregator>
        </Statistics>
    )
}
