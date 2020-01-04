import styled from 'styled-components'
import Note from './Note'

const Notebook = styled.ul`
    list-style-type: none;
    padding: 0;
    font-family: 'Lato', 'Open Sans', 'sans-serif';
    font-size: .875rem;
    color: #BBB9C7;
    margin-top: 16px;
    margin-bottom: 16px;
    margin-left 24px;
`
Notebook.Note = Note

export default Notebook
