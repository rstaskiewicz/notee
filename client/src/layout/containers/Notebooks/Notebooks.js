import styled from 'styled-components'

import Sidebar from './Sidebar'
import List from './List'
import Preview from './Preview'

const Notebooks = styled.div`
    display: flex;
    width: 100%;
`

Notebooks.Sidebar = Sidebar
Notebooks.List = List
Notebooks.Preview = Preview

export default Notebooks
