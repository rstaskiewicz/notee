import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

import Header from './Header'
import Content from './Content'
import Footer from './Footer'
import Title from './Title'
import Subtitle from './Subtitle'
import Avatar from './Avatar'


const NOTE_MODFIERS = {

    'notebooks-card': () => css`
        width: 486px;
        margin: 16px;
    `


}



const Card = styled.div`
    width: 808px;
    background-color: white;
    display: flex;
    flex-direction: column;
    align-items: center;
    border-radius: 2%;
    margin-bottom: 16px;
    box-shadow: 0, 0, 24px rgba(188, 188, 188, .16);
    ${applyStyleModifiers(NOTE_MODFIERS)}
`

Card.Header = Header
Card.Title = Title
Card.Subtitle = Subtitle
Card.Avatar = Avatar
Card.Content = Content
Card.Footer = Footer


export default Card
