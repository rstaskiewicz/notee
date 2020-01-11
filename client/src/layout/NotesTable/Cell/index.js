import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const CELL_MODFIERS = {

    'note-title' : () => css`
        color: black;
    `,


}



const Cell = styled.td`
    background: #ffffff;
    padding: 24px;
    color: #9E9CA7;
    font-size: .875rem;
    font-family: 'Lato' ,'Open Sans', 'sans-serif';
    ${applyStyleModifiers(CELL_MODFIERS)}
`

export default Cell
