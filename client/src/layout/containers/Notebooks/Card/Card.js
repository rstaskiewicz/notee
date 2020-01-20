import styled from 'styled-components'

import Header from './Header'
import Meta from './Meta'
import Title from './Title'
import Avatar from './Avatar'
import Subtitle from './Subtitle'
import Content from './Content'
import Footer from './Footer'

const Card = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
    background: #ffffff;
    box-shadow: 0 0 16px rgba(188, 188, 188, 0.16);
    border-radius: 8px;
    padding: .5em;
    margin: .5em 0;
    box-sizing: border-box;
    transition: all ease-in-out 250ms;

    &:hover {
        box-shadow: 0 16px 16px rgba(188, 188, 188, 0.24);
        transform: translate(0, -4px);
    }
`

Card.Header = Header
Card.Meta = Meta
Card.Title = Title
Card.Avatar = Avatar
Card.Subtitle = Subtitle
Card.Content = Content
Card.Footer = Footer

export default Card
