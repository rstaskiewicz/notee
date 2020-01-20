import styled from 'styled-components'

import Navigation from './Navigation'
import List from './List'
import Item from './Item'
import Link from './Link'
import Button from './Button'

const Sidebar = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100%;
    position: relative;
`

Sidebar.Navigation = Navigation
Sidebar.List = List
Sidebar.Item = Item
Sidebar.Link = Link
Sidebar.Button = Button

export default Sidebar
