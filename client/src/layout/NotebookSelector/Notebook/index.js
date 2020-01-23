import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const NOTEBOOK_MODFIERS = {

    'highlight': () => css`
        background: gray;
    `

}

const Notebook = styled.ul`
    list-style-type: none;
    padding: 0;
    margin: 12px;
    ${applyStyleModifiers(NOTEBOOK_MODFIERS)}
`

Notebook.P = styled.p`
    font-family: 'Lato', 'Open Sans', 'sans-serif';
    font-size: .875rem;
    color: #BBB9C7;
    word-wrap: break-word;
    ${applyStyleModifiers(NOTEBOOK_MODFIERS)}
`

Notebook.FontAwesomeIcon = styled.div`
    display: flex;
    float: right;
`

export default Notebook
