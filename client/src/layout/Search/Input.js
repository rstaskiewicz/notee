import styled from 'styled-components'

import search from '@notee/assets/search-icon.svg'
import searchActvie from '@notee/assets/search-active-icon.svg'

export default styled.input`
    position: relative;
    display: inline-flex;
    width: 100%;
    border: none;
    outline: none;
    background: #fafafc;
    padding: .5em 3em .5em 2em;
    font-family: 'Open Sans', 'sans-serif';
    font-size: .875em;
    font-weight: 500;
    color: #bbb9c7;
    appearance: none;
    border-radius: 8px;
    background-image: url(${search});
    background-repeat: no-repeat;
    background-position: right 12px center;
    background-size: 24px;
    width: 287px;
    transition: all ease-in-out 100ms;

    ::placeholder {
        background: transparent;
        color: transparent;
    }

    :active, :focus {
        color: #3454f5;
        background-image: url(${searchActvie});
        width: 384px;
    }
`

