import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const SECTION_MODIFIERS = {

    'algin-start': () => css`
        order: -1;
        justify-content: flex-start;
    `,

    'algin-end': () => css`
        order: 1;
        justify-content: flex-end;
    `

}

export default styled.div`
    display: inline-flex;
    flex: 1 1 auto;
    align-items: center;
    justify-content: center;
    height: 100%;
    z-index: 1;
    ${applyStyleModifiers(SECTION_MODIFIERS)}
`
