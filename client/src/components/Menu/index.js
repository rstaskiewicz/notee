import React from 'react'
import { useDispatch } from 'react-redux'
import { useHistory } from 'react-router-dom'

import { Menu } from '@notee/layout/components/Menu'
import { signOut } from '@notee/actions/auth'

export default () => {

    const history = useHistory()
    const dispatch = useDispatch()

    const handleSignOut = () => {
        dispatch(signOut())
        history.push('/sign-in')
    }

    return (
        <Menu>
            <Menu.Item>Profile</Menu.Item>
            <Menu.Item>Settings</Menu.Item>
            <Menu.Bar />
            <Menu.Item
                onClick={handleSignOut}
            >
                Sign out
            </Menu.Item>
        </Menu>
    )
}
