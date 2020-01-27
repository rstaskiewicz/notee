import styled from 'styled-components'

import Input from './Input'
import Dropdown from './Dropdown'
import List from './List'
import Item from './Item'

const Select = styled.div`
    display: flex;
    width: 100%;
    position: relative;
`

Select.Input = Input
Select.Dropdown = Dropdown
Select.List = List
Select.Item = Item

export default Select
