import styled from 'styled-components'

import List from './List'
import Item from './Item'
import Link from './Link'

const Navigation = styled.nav`
    display: flex;
    height: 100%;
`

Navigation.List = List
Navigation.Item = Item
Navigation.Link = Link

export default Navigation
