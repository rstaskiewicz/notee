import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

const IMAGE_MODFIERS = {

    'LoginLogo': () => css`
    position: relative;
    margin-left: auto;
    margin-right: auto;
    width: 10%;
    `

}


export default styled.img`
    max-width: 50%;
    height: auto;
    ${applyStyleModifiers(IMAGE_MODFIERS)}
`