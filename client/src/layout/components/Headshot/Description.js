import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const DESCRIPTION_MODIFIERS = {

    'primary': () => css`
        color: #2f49cb;
    `,

    'danger': () => css`
        color: '#ff1744';
    `

}

export default styled.p`
    text-align: center;
    font-family: 'lato';
    font-weight: 500;
    font-size: 14px;
    color: #bbb9c7;
    margin: 8px 0;
    ${applyStyleModifiers(DESCRIPTION_MODIFIERS)}
`
