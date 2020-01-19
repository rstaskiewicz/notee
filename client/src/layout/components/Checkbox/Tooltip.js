import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const TOOLTIP_MODIFIERS = {

    'invalid': () => css`
        color: #ff1744;
    `

}

export default styled.span`
    display: inline-flex;
    font-family: 'Lato', 'sans-serif';
    font-size: .75em;
    font-weight: 500;
    color: #bbb9c7;
    top: 24px;
    position: absolute;
    ${applyStyleModifiers(TOOLTIP_MODIFIERS)}
`
