import styled from 'styled-components'

import Image from './Image'
import Input from './Input'
import Icon from './Icon'
import Description from './Description'


const Headshot = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`

Headshot.Image = Image
Headshot.Input = Input
Headshot.Icon = Icon
Headshot.Description = Description

export default Headshot
