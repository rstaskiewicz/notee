import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const BUTTON_MODFIERS = {

    'primary': () => css`
        background: #3454f5;
        color: #ffffff;
        border: none;

        &:hover {
            background: #2b46d1;
        }
    `,

    'full-width': () => css`
        width: 100%;
    `,

    'small': () => css`
        min-width: 48px;
        padding: .25em .75em;
        font-size: .375em;
    `,

    'big': () => css`
        min-width: 128px;
        padding: .5rem 2rem;
        font-size: .75em;
        font-weight: 700;
        text-transform: uppercase;
    `

}

const Button = styled.button`
    font-size: .625em;
    font-family: 'Open Sans', 'sans-serif';
    font-weight: 600;
    color: #bbb9c7;
    text-decoration: none;
    display: inline-flex;
    position: relative;
    align-items: center;
    justify-content: center;
    box-sizing: border-box;
    margin: 0;
    padding: .625em 1em;
    min-width: 96px;
    user-select: none;
    cursor: pointer;
    overflow: hidden;
    border-radius: 8px;
    outline: none;
    border: 2px solid #bbb9c7;
    background: transparent;
    transition: all ease-in-out 100ms;

    &:hover {
        background: rgba(187, 185, 199, .1)
    }

    ${applyStyleModifiers(BUTTON_MODFIERS)}
`

Button.Icon = styled(Button)`
    font-weight: 500;
    font-size: 1rem;
    min-width: 0;
    border: none;
    background: transparent;
    padding: .25rem .125rem;

    &:hover {
        background: transparent;
    }
    ${applyStyleModifiers(BUTTON_MODFIERS)}
`

export default Button
