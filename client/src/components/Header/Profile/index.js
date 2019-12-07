import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faChevronDown as chevron,
    faUserFriends as friends,
    faBell as bell
} from '@fortawesome/free-solid-svg-icons'

import Profile from '../../../layout/Profile'

import Avatar from '../../Avatar'
import { Button } from '../../../elements'

export default () => {

    return (
        <Profile>

            <Profile.Actions>

                <Button.Icon>
                    <FontAwesomeIcon icon={friends} />
                </Button.Icon>

                <Button.Icon>
                    <FontAwesomeIcon icon={bell} />
                </Button.Icon>

            </Profile.Actions>

            <Profile.Avatar>
                <Avatar />
            </Profile.Avatar>

            <Profile.Name>
                Joanna Kowalska
            </Profile.Name>

        </Profile>
    )

}
