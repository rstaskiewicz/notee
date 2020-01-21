import React from 'react'
import { useSelector } from 'react-redux'

import { Header } from '@notee/layout/components/Editor'
import Avatar from '@notee/components/Avatar'

export default () => {

    const user = useSelector(state => state.user)

    return (
        <Header>

            <Header.Info>
                <Header.Meta>
                    <Header.Author>Author: Joanna Kowalska</Header.Author>
                    <Header.Created>Created: 5 June 2019</Header.Created>
                </Header.Meta>
                <Header.Avatar>
                    <Avatar
                        status={false}
                        image={user.avatarUrl}
                    />
                </Header.Avatar>
            </Header.Info>

        </Header>
    )
}
