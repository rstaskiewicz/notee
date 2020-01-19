import styled from 'styled-components'

import Row from './Row'
import Item from './Item'

const Head = styled.div`
    width: 100%;
    display: flex;
    box-sizing: border-box;
    padding: 0 3em;
`

Head.Row = Row
Head.Item = Item

export default Head
