import React from 'react'

import Header from '../../layout/Header'
import { Image } from '../../elements'

import logo from '../../assets/logo.svg'

import Profile from './Profile'
import Navigation from './Navigation'

export default () => {

    return (
        <Header>

            <Header.Logo>
                <Image modifiers="contain" src={logo} />
            </Header.Logo>

            <Header.Navigation>
                <Navigation />
            </Header.Navigation>

            <Header.Profile>
                <Profile />
            </Header.Profile>

        </Header>
    )

}
