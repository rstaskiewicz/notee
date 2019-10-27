import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'


const INPUT_MODFIERS = {

    'checkbox': () => css`
        margin-top: 50px;
        width: 0px;
        outline:2px solid gray;
        outline-offset: -2px;
        margin-left: 0;
        margin-right: 0;
    `
}



export default styled.input`
    display:inline-block
    font-size: 14px;
    width: 370px;
    font-family: 'Arial', 'sans-serif';
    font-weight: 700;
    color: #bbb9c7;
    outline: none;
    background: transparent;
    padding: 15px;
    outline: 0;
    border-width: 0 0 2px;
    border-color: #bbb9c7;
    margin-top: 50px;
    margin-left: auto;
    margin-right: auto;

    ${applyStyleModifiers(INPUT_MODFIERS)}
`