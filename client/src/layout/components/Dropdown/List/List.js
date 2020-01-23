import styled from 'styled-components'

import Title from './Title'
import Avatar from './Avatar'
import Item from './Item'
import Subtitle from './Subtitle'
import Info from './Info'

const List = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
`

List.Title = Title
List.Avatar = Avatar
List.Item = Item
List.Subtitle = Subtitle
List.Info = Info

export default List
