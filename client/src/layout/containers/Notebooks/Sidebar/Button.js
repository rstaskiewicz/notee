import styled from 'styled-components'

export default styled.button`
    width: 100%;
    height: 40px;
    display: inline-flex;
    position: absolute;
    bottom: 0;
    left: 0;
    outline: none;
    border: none;
    text-align: center;
    justify-content: center;
    align-items: center;
    box-sizing: border-box;
    border-top: 1px solid #efefef;
    font-family: 'Lato', 'sans-serif';
    font-size: 0.75em;
    font-weight: 400;
    color: #9e9ca7;
    transition: all ease-in-out 200ms;

    &:hover {
        background: rgba(187, 185, 199, .1)
    }
`
