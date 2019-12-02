import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const INPUT_MODIFIERS = {

    'primary': () => css`
        border: 2px solid #2f49cb;
    `,

    'danger': () => css`
        border: 2px solid rgba(255, 23, 68, 0.3);
    `

}

export default styled.div`
    width: 64px;
    height: 64px;
    border-radius: 50%;
    border: 2px solid rgba(187, 185, 199, 0.3);
    display: flex;
    margin: 8px;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    ${applyStyleModifiers(INPUT_MODIFIERS)}
`
