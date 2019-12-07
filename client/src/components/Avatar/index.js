import React from 'react'

import Avatar from '../../layout/Avatar'
import { Image } from '../../elements'

import avatar from '../../assets/avatar.jpg'

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
