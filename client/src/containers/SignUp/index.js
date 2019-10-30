import React from 'react'

import Input from '../../components/Input'

import SignUp from '../../layout/SignUp'

export default () => {

    return (
        <SignUp>

            <Input
                placeholder="Full name"
                type="text"
                tooltip="Your full name"
            />

        </SignUp>
    )

}
