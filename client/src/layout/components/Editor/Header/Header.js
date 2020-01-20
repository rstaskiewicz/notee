import styled from 'styled-components'

import Author from './Author'
import Avatar from './Avatar'
import Created from './Created'
import Info from './Info'
import Meta from './Meta'

const Header = styled.div`
    display: flex;
    width: 100%;
    justify-content: flex-end;
`

Header.Author = Author
Header.Avatar = Avatar
Header.Created = Created
Header.Info = Info
Header.Meta = Meta

export default Header
