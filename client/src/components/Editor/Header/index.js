import React from 'react'
import { useSelector } from 'react-redux'

import { Header } from '@notee/layout/components/Editor'
import Avatar from '@notee/components/Avatar'

export default ({ createdBy, createdAt }) => {

    const user = useSelector(state => state.user)

    return (
        <Header>

            <Header.Info>
                <Header.Meta>
                    <Header.Author>Author: {createdBy}</Header.Author>
                    <Header.Created>Created: {createdAt}</Header.Created>
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
