import React from 'react'

import { Page } from '@notee/layout/shared/Page'
import { Notebooks } from '@notee/layout/containers/Notebooks'

import Sidebar from './Sidebar'
import List from './List'
import Preview from './Preview'

import Header from '@notee/components/Header'

export default () => {
    return (
        <Page modifiers="with-sidebar">

            <Page.Header>
                <Header />
            </Page.Header>

            <Page.Sidebar>
                <Notebooks.Sidebar>
                    <Sidebar />
                </Notebooks.Sidebar>
            </Page.Sidebar>

            <Page.Content>
                <Notebooks>

                    <Notebooks.List>
                        <List />
                    </Notebooks.List>

                    <Notebooks.Preview>
                        <Preview />
                    </Notebooks.Preview>

                </Notebooks>
            </Page.Content>

        </Page>
    )
}
