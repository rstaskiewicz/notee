import styled from 'styled-components'

import Item from './Item'
import Bar from './Bar'

const Menu = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    border-radius: 8px;
    background: #ffffff;
    box-shadow: 0 0 24px rgba(0, 0, 0, .16);
    box-sizing: border-box;
    padding: .75em 0;
`

Menu.Item = Item
Menu.Bar = Bar

export default Menu
