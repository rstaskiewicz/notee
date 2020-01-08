import React from 'react'

import Header from '@notee/layout/Card/Header'
import Avatar from '@notee/components/Avatar'
import Card from '@notee/layout/Card'

export default ({
    user: { fullname },
    date
}) => {

    return (
        <Header>
            <Card.Avatar>
                <Avatar />
            </Card.Avatar>
            <div display="block" style={{lineHeight : '1.5'}}>
                <Card.Title>{fullname} created new note titled Prawdopodobieństwo</Card.Title>
                <Card.Subtitle>{date}</Card.Subtitle>
            </div>
            {/* <HeaderLeft>
                <Avatar modifiers="card-avatar" />
            </HeaderLeft>
            <HeaderRight>
                <A modifiers="card-header">USER_NAME</A>
                <Label modifiers="card-header">created new note titled</Label>
                <A modifiers="card-header">NOTE_TITLE</A>
            </HeaderRight> */}
        </Header>
    )

}
