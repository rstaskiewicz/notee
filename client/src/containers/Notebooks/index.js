import React from 'react'

import { Page } from '@notee/layout/shared/Page'
import { Notebooks } from '@notee/layout/containers/Notebooks'

import Sidebar from './Sidebar'
import List from './List'
import Preview from './Preview'

import Header from '@notee/components/Header'
import Portal from '@notee/components/Portal'

import { Modal } from '@notee/layout/components/Modal'
import { Overlay } from '@notee/elements'

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

            <Portal id="add-notebook">
                <Modal></Modal>
                <Overlay />
            </Portal>

        </Page>
    )
}
