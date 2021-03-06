import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const LABEL_MODIFIERS = {

    'invalid': () => css`
        color: #ff1744;
    `

}

export default styled.label`
    box-sizing: border-box;
    cursor: default;
    display: inset-block;
    font-family: 'Open Sans', 'sans-serif';
    width: 100%;
    font-size: .875em;
    font-weight: 400;
    position: absolute;
    display: block;
    color: #BBB9C7;
    bottom: 16px;
    ${applyStyleModifiers(LABEL_MODIFIERS)}
`
