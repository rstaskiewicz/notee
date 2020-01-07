import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faPlus as add
} from '@fortawesome/free-solid-svg-icons'

import Header from '@notee/layout/Header'
import { Image } from '@notee/elements'

import logo from '@notee/assets/logo.svg'

import Profile from './Profile'
import Navigation from './Navigation'
import Search from './Search'

export default () => {

    return (
        <Header>

            <Header.Section modifiers="algin-start">
                <Header.Logo>
                    <Image modifiers="contain" src={logo} />
                </Header.Logo>

                <Header.Navigation>
                    <Navigation />
                </Header.Navigation>
            </Header.Section>

            <Header.Section modifiers="algin-end">

                <a href="/note"><Header.Add href="/note" modifiers={['rounded', 'primary']} >
                    <FontAwesomeIcon icon={add} fixedWidth />
                </Header.Add>
                </a>
                <Header.Search>
                    <Search placeholder="Search…" />
                </Header.Search>

                <Header.Profile>
                    <Profile />
                </Header.Profile>
            </Header.Section>

        </Header>
    )

}
