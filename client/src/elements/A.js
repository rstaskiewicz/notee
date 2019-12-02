import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const A_MODIFIERS = {

    'active': () => css`
        color: #3454f5;
    `

}

export default styled.a`
    font-family: 'Open Sans', 'sans-serif';
    font-size: .875rem;
    font-weight: 400;
    color: #bbb9c7;
    transition: all ease-in-out 100ms;

    &:hover {
        color: #3454f5;
    }

    ${applyStyleModifiers(A_MODIFIERS)}

`
