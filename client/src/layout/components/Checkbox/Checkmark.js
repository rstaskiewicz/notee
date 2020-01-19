import styled from 'styled-components'

export default styled.span`
    box-sizing: border-box;
    position: relative;
    top: 0;
    left: 0;
    height: 16px;
    width: 16px;
    background: transparent;
    border-radius: 4px;
    border: 2px solid #bbb9c7;
    transition: all ease-in-out 100ms;

    :after {
        content: "";
        position: absolute;
        display: none;
    }
`
