import styled from 'styled-components'
import Header from './Header'
import Content from './Content'
import Footer from './Footer'

const Card = styled.div`
    width: 808px;
    background-color: white;
    display: flex;
    flex-direction: column;
    align-items: center;
    border-radius: 2%;
    margin-bottom: 16px;
`

Card.Header = Header
Card.Content = Content
Card.Footer = Footer

export default Card
