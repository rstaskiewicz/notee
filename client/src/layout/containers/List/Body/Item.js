import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const ITEM_MODIFIERS = {

    'justify-center': () => css`
        justify-self: center;
    `

}

export default styled.div`
    font-family: 'Lato', 'sans-serif';
    font-size: 14px;
    color: #bbb9c7;
    padding: 0 .5em;
    justify-self: start;
    ${applyStyleModifiers(ITEM_MODIFIERS)}
`
