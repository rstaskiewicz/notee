import styled from 'styled-components'

import chevron from '@notee/assets/chevron-icon.svg'

export default styled.div`
    position: relative;
    display: inline-flex;
    width: 100%;
    border: none;
    outline: none;
    background: #fafafc;
    padding: .65em 1.5em;
    align-items: center;
    font-family: 'Open Sans', 'sans-serif';
    font-size: .875em;
    font-weight: 500;
    color: #bbb9c7;
    appearance: none;
    border-radius: 8px;
    background-image: url(${chevron});
    background-repeat: no-repeat;
    background-position: right 12px center;
    background-size: 24px;
    box-sizing: border-box;
    transition: all ease-in-out 250ms;
    cursor: pointer;
`
