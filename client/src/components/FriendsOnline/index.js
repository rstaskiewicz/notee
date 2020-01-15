import React from 'react'
import FriendsOnline from '../../layout/FriendsOnline'
import Avatar from '../Avatar'

export default ({
    friends,

}) => {

    return (
        <FriendsOnline>
            {/* {friends.map((friend, index) => (
                <FriendsOnline.Avatar><Avatar key={index} avatar={friend.avatar} /></FriendsOnline.Avatar>
            ))} */}
            <FriendsOnline.Avatar><Avatar /></FriendsOnline.Avatar>
            <FriendsOnline.Avatar><Avatar /></FriendsOnline.Avatar>
            <FriendsOnline.Avatar><Avatar /></FriendsOnline.Avatar>
            <FriendsOnline.Avatar><Avatar /></FriendsOnline.Avatar>
            <FriendsOnline.Avatar><Avatar /></FriendsOnline.Avatar>
            <FriendsOnline.Avatar><Avatar /></FriendsOnline.Avatar>
            <FriendsOnline.Avatar><Avatar /></FriendsOnline.Avatar>
        </FriendsOnline>
    )
}
