import styled from 'styled-components'

import Input from './Input'
import Label from './Label'
import Tooltip from './Tooltip'

const TextField = styled.div`
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

TextField.Input = Input
TextField.Label = Label
TextField.Tooltip = Tooltip

export default TextField
