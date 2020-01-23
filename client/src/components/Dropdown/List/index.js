import React from 'react'

import { List } from '@notee/layout/components/Dropdown'
import Avatar from '@notee/components/Avatar'

export default ({
    data
}) => {
    return (
        <List>
            {data.map((item, index) => (
                <List.Item key={index}>
                    <List.Avatar>
                        <Avatar />
                    </List.Avatar>
                    <List.Info>
                        <List.Title>
                            {item.title.length > 36
                                ? `${item.title.slice(0, 36)}...`
                                : item.title}
                        </List.Title>
                        <List.Subtitle>{item.author}</List.Subtitle>
                    </List.Info>
                </List.Item>
            ))}
        </List>
    )
}
