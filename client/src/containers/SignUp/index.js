import React from 'react'
import { useHistory } from 'react-router-dom'
import { useDispatch } from 'react-redux'

import { SignUp } from '@notee/layout/containers/SignUp'
import { Image } from '@notee/elements'

import background from '@notee/assets/login-background.svg'

import Form from './Form'

import { signUp } from '@notee/actions/auth'

export default () => {

    const history = useHistory()
    const dispatch = useDispatch()

    const handleRegister = user => {
        dispatch(signUp(user))
        history.push('/confirm')
    }

    return (
        <SignUp>

            <SignUp.Section modifiers="algin-start">

                <Image
                    modifiers="cover"
                    src={background}
                />

            </SignUp.Section>

            <SignUp.Section modifiers="algin-end">

                <SignUp.Credentials>

                    <Form
                        onRegister={handleRegister}
                    />

                </SignUp.Credentials>

            </SignUp.Section>

        </SignUp>
    )

}
