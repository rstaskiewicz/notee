import styled from 'styled-components'

import Avatar from './Avatar'
import Name from './Name'
import Actions from './Actions'
import Action from './Action'
import User from './User'
import Bar from './Bar'

const Profile = styled.div`
    display: flex;
    align-items: center;
`

Profile.Avatar = Avatar
Profile.Name = Name
Profile.Actions = Actions
Profile.Action = Action
Profile.User = User
Profile.Bar = Bar

export default Profile
