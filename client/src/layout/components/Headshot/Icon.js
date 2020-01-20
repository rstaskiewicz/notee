import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const ICON_MODIFIERS = {

    'primary': () => css`
        color: #2f49cb;
    `,

    'danger': () => css`
        color: '#ff1744';
    `

}

export default styled.div`
    color: #bbb9c7;
    ${applyStyleModifiers(ICON_MODIFIERS)}
`
