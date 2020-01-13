import React from 'react'
import { useHistory } from 'react-router-dom'
import { useDispatch } from 'react-redux'

import SignIn from '@notee/layout/SignIn'
import { Image, A } from '@notee/elements'

import background from '@notee/assets/login-background.svg'
import logo from '@notee/assets/logo.svg'

import Form from './Form'

import { signInUser } from '@notee/actions/user'

export default () => {

    const history = useHistory()
    const dispatch = useDispatch()

    const handleSignIn = values => {
        dispatch(signInUser(values))
            .then(() => history.push('/dashboard'))
            .catch(error => alert(error.message))
    }

    return (
        <SignIn>

            <SignIn.Section modifiers="algin-start">

                <Image
                    modifiers="cover"
                    src={background}
                />

            </SignIn.Section>

            <SignIn.Section modifiers="algin-end">

                <SignIn.Credentials>

                    <SignIn.Logo>
                        <Image src={logo} />
                    </SignIn.Logo>

                    <Form
                        onLogin={handleSignIn}
                        onSignUp={() => history.push('/sign-up')}
                    />

                </SignIn.Credentials>

                <SignIn.Terms>
                    <A href="#">Terms of use. Privacy policy</A>
                </SignIn.Terms>

            </SignIn.Section>

        </SignIn>
    )

}
