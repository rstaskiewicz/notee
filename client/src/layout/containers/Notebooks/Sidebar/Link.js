import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const LINK_MODIFIERS = {

    'active': () => css`
        border-left: 2px solid #3454f5;
        background: #fafafc;
    `

}

export default styled.a`
    display: inline-flex;
    width: 100%;
    padding: 1.25em 0;
    font-family: 'Lato', 'sans-serif';
    font-size: 0.875em;
    color: #bbb9c7;
    justify-content: center;
    align-items: center;
    box-sizing: border-box;
    background: #ffffff;
    transition: all ease-in-out 100ms;
    font-weight: 500;
    cursor: pointer;

    &:hover {
        background: #fafafc;
    }

    ${applyStyleModifiers(LINK_MODIFIERS)}
`
