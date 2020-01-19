import styled from 'styled-components'

import Checkmark from './Checkmark'
import Input from './Input'
import Label from './Label'
import Tooltip from './Tooltip'

import checkmark from '@notee/assets/checkmark.svg'

const Checkbox = styled.label`
    display: flex;
    position: relative;
    cursor: pointer;
    align-items: center;
    transition: all ease-in-out 100ms;
    margin: 0;

    &:hover input ~ ${Checkmark} {
        border-color: #3454f5;
    }

    ${Input}:checked ~ ${Checkmark} {
        background-color: #3454f5;
        border-color: #3454f5;
    }

    ${Input}:checked ~ ${Checkmark}::after {
        display: block;
    }

    ${Checkmark}:after {
        width: 100%;
        height: 100%;
        background-image: url(${checkmark});
        background-size: 10px 7px;
        background-position: center center;
        background-repeat: no-repeat;
    }
`

Checkbox.Checkmark = Checkmark
Checkbox.Input = Input
Checkbox.Label = Label
Checkbox.Tooltip = Tooltip

export default Checkbox
