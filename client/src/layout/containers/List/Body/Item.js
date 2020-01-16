import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const ITEM_MODIFIERS = {

    'justify-start': () => css`
        justify-self: start;
    `

}

export default styled.div`
    font-family: 'Lato', 'sans-serif';
    font-size: 14px;
    color: #bbb9c7
    ${applyStyleModifiers(ITEM_MODIFIERS)}
`
