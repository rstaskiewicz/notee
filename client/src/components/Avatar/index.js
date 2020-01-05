import React from 'react'

import Avatar from '@notee/layout/Avatar'
import { Image } from '@notee/elements'

import avatar from '@notee/assets/avatar.jpg'

export default () => {

    return (
        <Avatar>
            <Avatar.Image>
                <Image modifiers='cover' src={avatar} />
            </Avatar.Image>
            <Avatar.Status modifiers='active' />
        </Avatar>
    )

}
