import React from 'react'

import Page from '@notee/layout/Page'
import { List } from '@notee/layout/containers/List'

import Head from './Head'
import Body from './Body'

import Header from '@notee/components/Header'

export default () => {


    return (
        <Page>
            <Page.Header>
                <Header />
            </Page.Header>
            <Page.Content>
                <List>
                    <List.Head>
                        <Head />
                    </List.Head>
                    <List.Body>
                        <Body />
                    </List.Body>
                </List>
            </Page.Content>
        </Page>
    )

}
