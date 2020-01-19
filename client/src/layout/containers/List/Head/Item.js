import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const ITEM_MODIFIERS = {

    'justify-center': () => css`
        justify-self: center;
    `

}

export default styled.div`
    font-family: 'Lato', 'sans-serif';
    font-size: .875em;
    color: #bbb9c7;
    justify-self: start;
    padding: 0 .5em;
    ${applyStyleModifiers(ITEM_MODIFIERS)}
`
