import React from 'react'

import SignUp from '../../layout/SignUp'
import { Image, P, A, Button } from '../../elements'

import background from '../../assets/login-background.svg'

import Input from '../../components/Input'
import Checkbox from '../../components/Checkbox'
import Headshot from '../../components/Headshot'

export default () => {

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

                    <SignUp.Form>

                        <SignUp.Headshot>
                            <Headshot />
                        </SignUp.Headshot>

                        <SignUp.Input>
                            <Input
                                name="full-name"
                                type="text"
                                placeholder="Full name"
                            />
                        </SignUp.Input>

                        <SignUp.Input>
                            <Input
                                name="e-mail"
                                type="e-mail"
                                placeholder="E-mail"
                            />
                        </SignUp.Input>

                        <SignUp.Input>
                            <Input
                                name="password"
                                type="password"
                                placeholder="Password"
                            />
                        </SignUp.Input>

                        <SignUp.Input>
                            <Input
                                name="confirm-password"
                                type="password"
                                placeholder="Confirm password"
                            />
                        </SignUp.Input>

                        <SignUp.Supplementary>

                            <Checkbox name="terms-of-service">
                                {() => (
                                    <>
                                        <P>I accept the&nbsp;
                                            <A modifiers="active" href="#">
                                                Terms of Service and Privacy Policy
                                            </A>
                                        </P>
                                    </>
                                )}
                            </Checkbox>

                        </SignUp.Supplementary>

                    </SignUp.Form>

                    <SignUp.ButtonGroup>

                        <Button modifiers={[ 'full-width', 'primary' ]}>Register</Button>

                    </SignUp.ButtonGroup>

                </SignUp.Credentials>

            </SignUp.Section>

        </SignUp>
    )

}
