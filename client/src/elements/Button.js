import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const BUTTON_MODFIERS = {

    'primary': () => css`
        background: #3454f5;
        color: #ffffff;
        border: none;

        &:hover {
            background: #3454f5;
        }
    `,

    'full-width': () => css`
        width: 100%;
    `,

    'card-borderless': () => css`
        min-width: 128px;
        border: none;
        color: #BBB9C7;
        text-transform: none;
        font-weight: 350;
        justify-content: space-between;
        padding: .5rem 2.5rem;
    `

}

const Button = styled.button`
    font-size: .75em;
    font-family: 'Open Sans', 'sans-serif';
    font-weight: 700;
    color: #bbb9c7;
    text-decoration: none;
    text-transform: uppercase;
    display: inline-flex;
    position: relative;
    align-items: center;
    justify-content: center;
    box-sizing: border-box;
    min-width: 128px;
    margin: 0;
    padding: .5rem 2rem;
    user-select: none;
    cursor: pointer;
    overflow: hidden;
    border-radius: 12px;
    outline: none;
    border: 2px solid #bbb9c7;
    background: transparent;
    transition: all ease-in-out 100ms;

    &:hover {
        background: rgba(187, 185, 199, .1)
    }

    ${applyStyleModifiers(BUTTON_MODFIERS)}
`

Button.Icon = styled.button``

Button.Rouded = styled.button``

export default Button
