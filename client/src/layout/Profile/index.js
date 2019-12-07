import styled from 'styled-components'

import Avatar from './Avatar'
import Name from './Name'
import Actions from './Actions'

const Profile = styled.div`
    display: flex;
    align-items: center;
`

Profile.Avatar = Avatar
Profile.Name = Name
Profile.Actions = Actions

export default Profile
