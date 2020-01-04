import React from 'react'

import FriendsOnline from '../../layout/FriendsOnline'
import Statistics from '../../layout/Statistics'
import Avatar from '../Avatar'

export default ({
    friends,

}) => {

    return (
        <FriendsOnline>
            <Avatar modifiers="dashboard-statistics" />
            <Avatar modifiers="dashboard-statistics" />
            <Avatar modifiers="dashboard-statistics" />
            <Avatar modifiers="dashboard-statistics" />
            <Avatar modifiers="dashboard-statistics" />
            <Avatar modifiers="dashboard-statistics" />
        </FriendsOnline>
    )
}
