import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faChevronDown as chevron
    // faUserFriends as friends,
    // faBell as bell
} from '@fortawesome/free-solid-svg-icons'

import { Profile } from '@notee/layout/components/Header'

import Avatar from '@notee/components/Avatar'
import { Button } from '@notee/elements'
import { useSelector } from 'react-redux'

export default () => {

    const user = useSelector(state => state.user)

    return (
        <Profile>

            <Profile.Bar />
{/*
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

            </Profile.Actions> */}

            <Profile.User>
                <Profile.Avatar >
                    <Avatar
                        status="active"
                        image={user.avatarUrl}
                    />
                </Profile.Avatar>

                <Profile.Name>
                    {user.name}
                </Profile.Name>

                <Button.Icon>
                    <FontAwesomeIcon icon={chevron} fixedWidth />
                </Button.Icon>
            </Profile.User>

        </Profile>
    )

}
