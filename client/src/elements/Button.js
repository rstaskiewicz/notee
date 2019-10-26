import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const BUTTON_MODFIERS = {

    'primary': () => css`
        background: #3454f5;
        color: #ffffff;
        border: none;
    `

}

export default styled.button`
    font-size: 14px;
    font-family: 'Open Sans', 'sans-serif';
    font-weight: 700;
    color: #bbb9c7;
    text-decoration: none;
    text-transform: uppercase;
    display: inline-flex;
    position: relative;
    align-items: center;
    box-sizing: border-box;
    min-width: 48pt;
    user-select: none;
    overflow: hidden;
    border-radius: 4pt;
    outline: none;
    border: 2px solid #bbb9c7;
    background: transparent;
    box-sizing: border-box;
    ${applyStyleModifiers(BUTTON_MODFIERS)}
`
