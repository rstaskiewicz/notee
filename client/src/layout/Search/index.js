import styled from 'styled-components'

import Input from './Input'
import Label from './Label'

const Search = styled.div`
    position: relative;
    width: 287px;
    display: flex;
    transition: all ease-in-out 100ms;

    ${Input}:not(:placeholder-shown) ~ ${Label} {
        display: none;
        height: 0;
    }

    ${Input}:focus ~ ${Label} {
        display: none;
        height: 0;
    }

`

Search.Input = Input
Search.Label = Label

export default Search
