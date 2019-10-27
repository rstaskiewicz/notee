import React from 'react'

import SignIn from '../../layout/SignIn'
import { Button, Image, Label } from '../../elements'
import Side from '../../layout/SignIn/Side'
import LogoBackground from '../../assets/login-background.svg'
import LoginLogo from '../../assets/logo.svg'
import Input from '../../layout/SignIn/Input'
import RefLink from '../../layout/SignIn/RefLink'

export default () => {

    return (
        <>

            <SignIn>
                <Image src={LogoBackground} />
                <Side>
                    <Image modifiers='LoginLogo' src={LoginLogo} />
                    <Input type='email' placeholder='E-mail' />
                    <Input type='password' placeholder='Password' />
                    <div align='center'>
                        <Label><Input modifiers='checkbox' type='checkbox' /> Remember me</Label>
                        <RefLink type='href' value="asd">Forgot password</RefLink>
                    </div>
                    <div align='center'>
                        <Button modifiers="primary">Login</Button>
                        <Button>Sign In</Button>
                    </div>
                </Side>
            </SignIn>

        </>
    )

}
