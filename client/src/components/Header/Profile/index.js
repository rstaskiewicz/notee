import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faChevronDown as chevron,
    faUserFriends as friends,
    faBell as bell
} from '@fortawesome/free-solid-svg-icons'

import Profile from '@notee/layout/Profile'

import Avatar from '@notee/components/Avatar'
import { Button } from '@notee/elements'

export default () => {

    return (
        <Profile>

            <Profile.Bar />

            <Profile.Actions>

                <Profile.Action>
                    <Button.Icon modifiers="light">
                        <FontAwesomeIcon icon={friends} fixedWidth />
                    </Button.Icon>
                </Profile.Action>

                <Profile.Action>
                    <Button.Icon modifiers="light">
                        <FontAwesomeIcon icon={bell} fixedWidth />
                    </Button.Icon>
                </Profile.Action>

            </Profile.Actions>

            <Profile.User>
                <Profile.Avatar>
                    <Avatar />
                </Profile.Avatar>

                <Profile.Name>
                    Joanna Kowalska
                </Profile.Name>

                <Button.Icon>
                    <FontAwesomeIcon icon={chevron} fixedWidth />
                </Button.Icon>
            </Profile.User>

        </Profile>
    )

}
