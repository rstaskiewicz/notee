import React from 'react'

import { Menu } from '@notee/layout/components/Menu'

export default () => {
    return (
        <Menu>
            <Menu.Item>Profile</Menu.Item>
            <Menu.Item>Settings</Menu.Item>
            <Menu.Bar />
            <Menu.Item
                onClick={() => console.log('Signing out')}
            >
                Sign out
            </Menu.Item>
        </Menu>
    )
}
