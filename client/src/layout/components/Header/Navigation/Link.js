import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'
import { NavLink } from 'react-router-dom'

const LINK_MODIFIERS = {

    'active': () => css`
        color: #3454F5;
    `

}

export default styled(NavLink)`
    display: flex;
    font-family: 'lato', 'sans-serif';
    font-size: 16px;
    color: #bbb9c7;
    text-decoration: none;
    padding: 0.75em 1em;
    min-width: 64px;
    text-align: center;
    justify-content: center;
    transition: all ease-in-out 250ms;
    align-items: center;

    :hover {
        background: rgba(52, 84, 245, .1);
        color: #ffffff;
        border: none;
        border-radius: 8px;
    }

    ${applyStyleModifiers(LINK_MODIFIERS)}
`
