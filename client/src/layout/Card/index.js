import styled from 'styled-components'
import Header from './Header'
import Content from './Content'
import Footer from './Footer'

const Card = styled.div`
    height: auto;
    width: 50vw;
    background-color: white;
    display: flex;
    position: relative;
    flex-direction: column;
    align-items: center;
    margin: 50px;
    border-radius: 2%;
`

Card.Header = Header
Card.Content = Content
Card.Footer = Footer

export default Card
