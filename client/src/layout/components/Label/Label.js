import styled from 'styled-components'

import Text from './Text'
import Icon from './Icon'

const Label = styled.div`
    display: inline-flex;
    justify-content: center;
    align-items: center;
    padding: .375em .75em;
    background: #c6cbdb;
    border-radius: 16px;
`

Label.Text = Text
Label.Icon = Icon

export default Label
