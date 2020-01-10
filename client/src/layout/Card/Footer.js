import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const FOOTER_MODFIERS = {

    'notebooks-card-footer': () => css`
        text-align: right;
        padding: 25px 0px;
    `


}

const Footer = styled.div`
    width: 100%;
    display: inline-block;
    text-align: center;
    padding: 25px;
    ${applyStyleModifiers(FOOTER_MODFIERS)}
`

export default Footer