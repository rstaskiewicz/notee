import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const LABEL_MODFIERS = {

    'card-header': () => css`
        font-family: 'Lato', 'Open Sans', 'sans-serif';
        font-size: .875rem;
        color: #BBB9C7;
    `
}

export default styled.label`
    margin-left: 8px;
    margin-right: 8px;
    ${applyStyleModifiers(LABEL_MODFIERS)}
`
