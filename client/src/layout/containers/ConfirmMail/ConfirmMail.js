import styled from 'styled-components'

import Logo from './Logo'
import Text from './Text'

const ConfirmMail = styled.div`
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top: 3em;
    background: #fafafc;
`

ConfirmMail.Logo = Logo
ConfirmMail.Text = Text

export default ConfirmMail
