import styled from 'styled-components'
import Content from './Content'

const Note = styled.div`
    background: #FAFAFC;
    height: 100vh;
`

Note.Input = styled.div`
    width: 300px;
    border-radius: 8%;
    background: #ffffff;
    padding: 9px 18px;
    border-style:none;
    font-family: 'Lato', 'Open Sans', 'sans-serif';
    color: #BBB9C7;
    word-wrap: break-word;
`

Note.Content = Content;

export default Note