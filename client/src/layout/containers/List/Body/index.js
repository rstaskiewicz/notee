import styled from 'styled-components'

import Item from './Item'
import Row from './Row'

const Body = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
`

Body.Row = Row
Body.Item = Item

export default Body
