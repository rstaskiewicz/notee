import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const SEGREGATOR_MODFIERS = {

    'dashboard-right-column': () => css`
       margin-top: 36px;
       margin-right: 167px;
    `,

    'dashboard-left-column': () => css`
        margin-left: 244px;
        margin-top: 36px;
    `,

    'dashboard-center-column': () => css`
        margin: 36px 24px;
        display: flex;
        align-items: center;
    `,


}

export default styled.div`
    background: #FAFAFC;
    display: flex;
    flex-direction: column;
    ${applyStyleModifiers(SEGREGATOR_MODFIERS)}
`
