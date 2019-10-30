import styled from 'styled-components'

import Section from './Section'
import Credentials from './Credentials'
import Logo from './Logo'
import Form from './Form'
import Input from './Input'
import Supplementary from './Supplementary'
import ButtonGroup from './ButtonGroup'
import Terms from './Terms'

const SignIn = styled.div`
    display: flex;
    width: 100%;
    height: 100vh;
`

SignIn.Section = Section
SignIn.Credentials = Credentials
SignIn.Logo = Logo
SignIn.Form = Form
SignIn.Input = Input
SignIn.Supplementary = Supplementary
SignIn.ButtonGroup = ButtonGroup
SignIn.Terms = Terms

export default SignIn
