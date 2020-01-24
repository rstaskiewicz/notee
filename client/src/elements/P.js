import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const P_MODIFIERS = {

    'primary': () => css`
        color: #3454f5;
    `,

    'darker': () => css`
        color: #2d3032;
    `,

    'align-center': () => css`
        text-align: center;
    `

}

export default styled.p`
    font-family: 'Open Sans', 'sans-serif';
    font-size: .875rem;
    font-weight: 400;
    color: #bbb9c7;
    ${applyStyleModifiers(P_MODIFIERS)}
`
