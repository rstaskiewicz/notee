import React, { useRef } from 'react'
import { useHistory } from 'react-router-dom'

import SignUp from '@notee/layout/SignUp'
import { Image, Button } from '@notee/elements'

import background from '@notee/assets/login-background.svg'

import Form from './Form'

export default () => {

    const history = useHistory()
    const form = useRef(null)

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

                    <Form ref={form} />

                    <SignUp.ButtonGroup>

                        <Button
                            // onClick={}
                            modifiers={[ 'full-width', 'primary' ]}
                        >
                            Register
                        </Button>

                    </SignUp.ButtonGroup>

                </SignUp.Credentials>

            </SignUp.Section>

        </SignUp>
    )

}
