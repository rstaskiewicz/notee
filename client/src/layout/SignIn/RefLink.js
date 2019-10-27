import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'


const REFLINK_MODFIERS = {

    'primary': () => css`
        
    `

}



export default styled.a`
    font-size: 14px;
    width: 370px;
    font-family: 'Arial', 'sans-serif';
    font-weight: 550;
    color: #bbb9c7;
    outline: none;
    background: transparent;
    padding: 15px;
    outline: 0;
    border-width: 0 0 2px;
    border-color: #bbb9c7;
    ${applyStyleModifiers(REFLINK_MODFIERS)}
`