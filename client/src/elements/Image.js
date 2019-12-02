import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const IMAGE_MODIFIERS = {

    'cover': () => css`
        object-fit: cover;
        height: 100%;
        width: 100%;
        overflow: hidden;
        object-position: top center;
    `

}

export default styled.img`
    display: flex;
    flex-shrink: 1
    ${applyStyleModifiers(IMAGE_MODIFIERS)}
`
