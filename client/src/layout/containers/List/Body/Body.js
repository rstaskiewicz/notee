import styled from 'styled-components'

import Item from './Item'
import Row from './Row'
import Avatar from './Avatar'

const Body = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
    background: #ffffff;
    box-shadow: 0 0 24px rgba(188, 188, 188, 0.16);
    box-sizing: border-box;
    border-radius: 8px;
    padding: 0 3em;
`

Body.Row = Row
Body.Item = Item
Body.Avatar = Avatar

export default Body
