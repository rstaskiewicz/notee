import React, {useState} from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import Header from '@notee/layout/Card/Header'
import Avatar from '@notee/components/Avatar'
import Card from '@notee/layout/Card'
import {
    faBookmark as bookmark
} from '@fortawesome/free-solid-svg-icons'

import { Button } from '@notee/elements'

const DashboardCardHeader = ({noteName, user, date}) => {
    return (
        <Header>
            <Card.Avatar>
                <Avatar />
            </Card.Avatar>
            <div display="block" style={{ lineHeight: '1.5' }}>
                <Card.Title>{user} created new note titled {noteName}</Card.Title>
                <Card.Subtitle>{date}</Card.Subtitle>
            </div>
        </Header>
    )
}

const NotebooksCardHeader = ({
    user: { fullname },
    date
}) => {

    const [ isBookmarked, setBookmark ] = useState(false)
    return (
        <Header>
            <Card.Avatar>
                <Avatar />
            </Card.Avatar>
            <div display="block" style={{ lineHeight: '1.5', width: '300px'}}>
                <Card.Title>{fullname}</Card.Title>
                <Card.Subtitle>{date}</Card.Subtitle>
            </div>
                <Button onClick={() => setBookmark(!isBookmarked)} modifiers="notebooks-card-borderless" style={{ float: 'right'}}>
                    <Button.FontAwesomeIcon modifiers={isBookmarked ? 'highlight' : ''}>
                        <FontAwesomeIcon icon={bookmark} />
                    </Button.FontAwesomeIcon>
                </Button>
        </Header>
    )
}

export { DashboardCardHeader, NotebooksCardHeader }
