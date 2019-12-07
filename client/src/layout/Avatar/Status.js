import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const STATUS_MODIFIERS = {

    'active': () => css`
        background: #75E900;
    `

}

export default styled.div`
    position: absolute;
    bottom: 0;
    right: 0;
    width: 8px;
    height: 8px;
    border: 2px solid #ffffff;
    border-radius: 50%;
    ${applyStyleModifiers(STATUS_MODIFIERS)}
`
