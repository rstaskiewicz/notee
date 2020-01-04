import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const SEGREGATOR_MODFIERS = {

    'notes': () => css`
        margin-left: 28px;
    `
}


export default styled.div`
    display: flex;
    flex-direction: row;
    margin: 18px 24px;
    ${applyStyleModifiers(SEGREGATOR_MODFIERS)}
`