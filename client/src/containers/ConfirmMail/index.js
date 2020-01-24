import React from 'react'

import { ConfirmMail } from '@notee/layout/containers/ConfirmMail'
import { Image, P, Link } from '@notee/elements'

import logo from '@notee/assets/logo.svg'

export default () => {
    return (
        <ConfirmMail>
            <ConfirmMail.Logo>
                <Image
                    modifiers="contain"
                    src={logo}
                />
            </ConfirmMail.Logo>
            <ConfirmMail.Text>
                <P modifiers="align-center">
                    Please check your email to verify that you own this address and unlock the power of seemless note creation. Already verified go to <Link to="/sign-in">Sign In</Link>
                </P>
            </ConfirmMail.Text>
        </ConfirmMail>
    )
}
