import React from 'react'

import Avatar from '@notee/layout/Avatar'
import { Image } from '@notee/elements'

import avatar from '@notee/assets/avatar.jpg'

export default ({
    image,
    status
}) => {

    return (
        <Avatar>
            <Avatar.Image>
                <Image modifiers='cover' src={image || avatar} />
            </Avatar.Image>
            {status && <Avatar.Status modifiers={status} />}
        </Avatar>
    )

}
