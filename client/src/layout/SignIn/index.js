import styled from 'styled-components'

import Side from './Side'
import Input from './Input'
import RefLink from './RefLink'

const SignIn = styled.div`
    display: flex;
    width: 100%;
    flex-direction: row;
    height: 100vh;
`

SignIn.Side = Side
SignIn.Input = Input
SignIn.RefLink = RefLink

export default SignIn