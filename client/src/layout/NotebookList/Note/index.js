import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const NOTE_MODFIERS = {

    'hidden': () => css`
        display: none;
    `

}

const Note = styled.li`
    margin-top: 16px;
    margin-bottom: 0px;
    margin-left 8px;
    font-family: 'Lato', 'Open Sans', 'sans-serif';
    font-size: .75rem;
    color: #BBB9C7;
    ${applyStyleModifiers(NOTE_MODFIERS)}
`

Note.FontAwesomeIcon = styled.div`
    display: inline;
    margin-right: 8px;
`


export default Note
