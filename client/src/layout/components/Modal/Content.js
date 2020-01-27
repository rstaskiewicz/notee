import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const CONTENT_MODIFIERS = {

    'justify-center': () => css`
        justify-content: center;
    `,

    'algin-center': () => css`
        align-content: center;
    `

}

export default styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    padding: .5em 1.5em;
    box-sizing: border-box;
    ${applyStyleModifiers(CONTENT_MODIFIERS)}
`
