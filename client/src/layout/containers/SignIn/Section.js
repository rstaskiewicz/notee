import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const SECTION_MODIFIERS = {

    'algin-start': () => css`
        order: -1;
    `,

    'algin-end': () => css`
        order: 1;
    `

}

export default styled.div`
    width: 50%;
    position: relative;
    flex-direction: column;
    display: inline-flex;
    flex: 1 1 auto;
    align-items: center;
    justify-content: center;
    z-index: 1;
    ${applyStyleModifiers(SECTION_MODIFIERS)}
`
