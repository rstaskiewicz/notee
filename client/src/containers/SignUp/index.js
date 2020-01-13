import React from 'react'
import { useHistory } from 'react-router-dom'
import { useDispatch } from 'react-redux'

import SignUp from '@notee/layout/SignUp'
import { Image } from '@notee/elements'

import background from '@notee/assets/login-background.svg'

import Form from './Form'

import { signUpUser } from '@notee/actions/user'

export default () => {

    const history = useHistory()
    const dispatch = useDispatch()

    const handleRegister = user => {
        dispatch(signUpUser(user))
            .then(() => history.push('/dashboard'))
            .catch(error => alert(error.message))
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
