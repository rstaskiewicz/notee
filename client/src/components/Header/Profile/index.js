import React, { useState } from 'react'
import { useSelector } from 'react-redux'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faChevronDown as chevron
    // faUserFriends as friends,
    // faBell as bell
} from '@fortawesome/free-solid-svg-icons'

import { Profile } from '@notee/layout/components/Header'

import Avatar from '@notee/components/Avatar'
import Menu from '@notee/components/Menu'
import { Button } from '@notee/elements'


export default () => {

    const { data: { avatarUrl, fullName } } = useSelector(state => state.user)
    const [ isMenuOpen, setIsMenuOpen ] = useState(false)

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
                        image={avatarUrl}
                    />
                </Profile.Avatar>

                <Profile.Name>
                    {fullName}
                </Profile.Name>

                <Button.Icon
                    onClick={() => setIsMenuOpen(!isMenuOpen)}
                >
                    <FontAwesomeIcon icon={chevron} fixedWidth />
                </Button.Icon>

                <Profile.Menu>
                    {isMenuOpen && <Menu />}
                </Profile.Menu>
            </Profile.User>

        </Profile>
    )

}
