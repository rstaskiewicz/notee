import styled from 'styled-components'

import Input from './Input'
import Label from './Label'
import Tooltip from './Tooltip'

const TextInput = styled.div`
    position: relative;

    ${Input}:not(:placeholder-shown) ~ ${Label} {
        display: none;
        height: 0;
    }

    ${Input}:focus ~ ${Label} {
        display: none;
        height: 0;
    }

`

TextInput.Input = Input
TextInput.Label = Label
TextInput.Tooltip = Tooltip

export default TextInput
