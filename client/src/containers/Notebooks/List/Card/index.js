import React from 'react'

import { Card } from '@notee/layout/containers/Notebooks'
import Avatar from '@notee/components/Avatar'

export default () => {
    return (
        <Card>
            <Card.Header>
                <Card.Meta>Basia Wiśniewska - 5 czerwca 2018</Card.Meta>
                <Card.Avatar>
                    <Avatar />
                </Card.Avatar>
            </Card.Header>
            <Card.Content>
                <Card.Title>Prawdopodobieństwo</Card.Title>
                <Card.Subtitle>Matematyka dyskretna</Card.Subtitle>
            </Card.Content>
            <Card.Footer />
        </Card>
    )
}
