import styled from 'styled-components'

import Image from './Image'
import Status from './Status'

const Avatar = styled.div`
    position: relative;
    max-width: 124px;
    max-height: 124px;
    display: flex;
`

Avatar.Image = Image
Avatar.Status = Status

export default Avatar
