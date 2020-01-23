import styled from 'styled-components'

import Input from './Input'
import Label from './Label'
import Dropdown from './Dropdow'

const Search = styled.div`
    position: relative;
    width: 100%;
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
Search.Dropdown = Dropdown

export default Search
