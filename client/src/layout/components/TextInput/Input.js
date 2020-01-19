import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const INPUT_MODIFIERS = {

    'invalid': () => css`
        border-bottom: 2px solid rgba(255, 23, 68, 0.3);
        color: #ff1744;

        :active, :focus {
            color: #ff1744;
            border-bottom: 2px solid rgba(255, 23, 68, 0.3);
        }

    `

}

export default styled.input`
    position: relative;
    display: flex;
    width: 100%;
    border: none;
    outline: none;
    border-bottom: 2px solid rgba(187, 185, 199, .3);
    background: transparent;
    padding: .75em 0;
    font-family: 'Open Sans', 'sans-serif';
    font-size: .875em;
    font-weight: 400;
    color: #bbb9c7;
    appearance: none;

    ::placeholder {
        background: transparent;
        color: transparent;
    }

    :active, :focus {
        border-bottom: 2px solid rgba(52, 84, 245, .3);
        color: #3454f5;
    }

    ${applyStyleModifiers(INPUT_MODIFIERS)}
`

