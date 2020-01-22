import React from 'react'

import Card from '@notee/layout/Card'

import { DashboardCardHeader, NotebooksCardHeader } from './Header'
import Content from './Content'
import { DashboardCardFooter, NotebooksCardFooter } from './Footer'

const user = {
    fullname: 'Joanna Kowalska'
}


const DashboardCard = ({ noteName, createdBy, modifiedAt, noteContent }) => {
    return (
        <Card>
            <DashboardCardHeader noteName={noteName} user={createdBy} date={modifiedAt} />
            <Content>
                {noteContent}
            </Content>
            <DashboardCardFooter />
        </Card>
    )
}

const NotebooksCard = () => {
    return (
        <Card modifiers="notebooks-card">
            <NotebooksCardHeader user={user} date="4 min ago" />
            <Content>
                Penis stuck in the oven, consectetur adipiscing elit. Quisque ut vehicula arcu, nec dictum felis. Etiam a est dapibus, mollis est convallis, laoreet orci. Praesent mollis, augue quis molestie pulvinar, ante tortor facilisis metus, et placerat justo quam a nulla. Suspendisse feugiat tortor a quam imperdiet vestibulum. Maecenas commodo consectetur sapien, vel hendrerit sem gravida et. Sed tincidunt aliquam ex, id mollis dolor ullamcorper at. Vivamus dapibus enim iaculis urna eleifend, eget blandit arcu scelerisque. Nunc id ipsum non odio gravida fermentum…
            </Content>
            <NotebooksCardFooter  />
        </Card>
    )
}

export { DashboardCard, NotebooksCard }
