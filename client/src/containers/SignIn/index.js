import React from 'react'

import SignIn from '../../layout/SignIn'
import { Button, Image, A, P } from '../../elements'

import background from '../../assets/login-background.svg'
import logo from '../../assets/logo.svg'

import Input from '../../components/Input'
import Checkbox from '../../components/Checkbox'

export default () => {

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

                    <SignIn.Form>

                        <SignIn.Input>
                            <Input
                                name="mail"
                                type="mail"
                                placeholder="E-mail"
                            />
                        </SignIn.Input>

                        <SignIn.Input>
                            <Input
                                name="password"
                                type="password"
                                placeholder="Password"
                            />
                        </SignIn.Input>

                        <SignIn.Supplementary>

                            <Checkbox name="remember-me">
                                {({ isActive, isMouseOver }) => (
                                    <P modifiers={[ (isActive || isMouseOver) && 'primary' ]}>
                                        Remember me
                                    </P>
                                )}
                            </Checkbox>

                            <A href="#">Forgot password</A>

                        </SignIn.Supplementary>

                    </SignIn.Form>

                    <SignIn.ButtonGroup>

                        <Button modifiers="primary">Login</Button>
                        <Button>Sing Up</Button>

                    </SignIn.ButtonGroup>

                </SignIn.Credentials>

                <SignIn.Terms>
                    <A href="#">Terms of use. Privacy policy</A>
                </SignIn.Terms>

            </SignIn.Section>

        </SignIn>
    )

}
