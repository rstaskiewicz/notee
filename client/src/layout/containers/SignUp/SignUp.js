import styled from 'styled-components'

import Section from './Section'
import Credentials from './Credentials'
import Form from './Form'
import Headshot from './Headshot'
import Input from './Input'
import Supplementary from './Supplementary'
import ButtonGroup from './ButtonGroup'

const SignUp = styled.div`
    display: flex;
    width: 100%;
    height: 100vh;
`

SignUp.Section = Section
SignUp.Credentials = Credentials
SignUp.Headshot = Headshot
SignUp.Form = Form
SignUp.Input = Input
SignUp.Supplementary = Supplementary
SignUp.ButtonGroup = ButtonGroup

export default SignUp
