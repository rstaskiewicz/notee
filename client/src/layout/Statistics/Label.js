import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const LABEL_MODFIERS = {


}

export default styled.label`
    font-family: 'Lato', 'Open Sans', 'sans-serif';
    font-size: .75rem;
    color: #BBB9C7;
    margin-left: 12px;
    margin-top: 2px;
    font-weight: bold;
    ${applyStyleModifiers(LABEL_MODFIERS)}
`
