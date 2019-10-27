import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const BUTTON_MODFIERS = {

    'primary': () => css`
        background: #3454f5;
        color: #ffffff;
        border: none;
        margin-right: 100px;
    `

}

export default styled.button`
    font-size: 15px;
    font-family: 'Open Sans', 'sans-serif';
    font-weight: 700;
    color: #bbb9c7;
    text-decoration: none;
    text-transform: uppercase;
    position: relative;
    align-items: center;
    box-sizing: border-box;
    min-width: 150pt;
    min-height: 40pt;
    user-select: none;
    border-radius: 10pt;
    outline: none;
    border: 2px solid #bbb9c7;
    background: transparent;
    box-sizing: border-box;
    height: 50%;
    width: 15%;
    text-align: center;
    margin-top: 25px;
    ${applyStyleModifiers(BUTTON_MODFIERS)}
`
