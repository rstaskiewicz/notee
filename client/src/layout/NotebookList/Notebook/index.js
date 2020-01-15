import styled from 'styled-components'

const Notebook = styled.ul`
    list-style-type: none;
    padding: 0;
    margin: 12px;

`

Notebook.P = styled.p`
    font-family: 'Lato', 'Open Sans', 'sans-serif';
    font-size: .875rem;
    color: #BBB9C7;
    word-wrap: break-word;
`

Notebook.FontAwesomeIcon = styled.div`
    display: flex;
    float: right;
`

export default Notebook
