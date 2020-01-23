import styled from 'styled-components'

import Title from './Title'
import List from './List'

const Dropdown = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
    border-radius: 8px;
    background: #ffffff;
    box-shadow: 0 0 24px rgba(0, 0, 0, .16);
    box-sizing: border-box;
    padding: 8px 0;
`

Dropdown.Title = Title
Dropdown.List = List

export default Dropdown
