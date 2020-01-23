import styled from 'styled-components'
import { Link } from 'react-router-dom'

export default styled(Link)`
    width: 100%;
    display: flex;
    align-items: center;
    padding: .5em 1.5em;
    box-sizing: border-box;
    margin: 8px 0;
    text-decoration: none;

    &:hover {
        background: rgba(52, 84, 245, .1)
    }
`
