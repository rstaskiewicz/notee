import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const ITEM_MODIFIERS = {

    'active': () => css`
        color: #3454F5;
        border-bottom: 2px solid #3454F5;
        border-radius: 0;
    `

}

export default styled.li`
    display: flex;
    height: 100%;
    box-sizing: border-box;
    justify-content: center;
    align-items: center;
    ${applyStyleModifiers(ITEM_MODIFIERS)}
`
