import React from 'react'
import { useSelector } from 'react-redux'
import * as moment from 'moment'

import { Header } from '@notee/layout/components/Editor'
import Avatar from '@notee/components/Avatar'

export default ({
    author,
    createdAt
}) => {

    const { avatarUrl } = author || useSelector(({ user }) => user.data)
    const created = moment(createdAt) || moment()

    return (
        <Header>

            <Header.Info>
                <Header.Meta>
                    <Header.Author>Author: {author}</Header.Author>
                    <Header.Created>Created: {created.format('lll').toString()}</Header.Created>
                </Header.Meta>
                <Header.Avatar>
                    <Avatar
                        image={avatarUrl}
                    />
                </Header.Avatar>
            </Header.Info>

        </Header>
    )
}
