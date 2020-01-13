import styled from 'styled-components'

export default styled.a`
    display: flex;
    font-family: 'lato', 'sans-serif';
    font-size: 16px;
    color: #bbb9c7;
    text-decoration: none;
    padding: 0.75em 1em;
    min-width: 64px;
    text-align: center;
    justify-content: center;
    transition: all ease-in-out 250ms;
    align-items: center;

    :hover {
        background: rgba(52, 84, 245, .1);
        color: #ffffff;
        border: none;
        border-radius: 8px;
    }
`
