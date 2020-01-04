import React from 'react'

import Statistics from '../../layout/Statistics'
import Label from '../../layout/Statistics/Label'
import Segregator from '../../layout/Statistics/Segregator'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faFileAlt as file,
    faUsers as users,
} from '@fortawesome/free-solid-svg-icons'

export default () => {

    return (
        <Statistics>
            <Segregator modifiers="notes">
                <FontAwesomeIcon color="#BBB9C7" icon={file} />
                <Label >
                    54 created and 123 saved notes
                </Label>
            </Segregator>
            <Segregator>
                <FontAwesomeIcon color="#BBB9C7" icon={users} />
                <Label>
                    1 thousand users
                </Label>
            </Segregator>
        </Statistics>
    )
}
