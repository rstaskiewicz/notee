import React from 'react'

import { Page } from '@notee/layout/shared/Page'
import { Note } from '@notee/layout/containers/Note'

import Meta from './Meta'

import Header from '@notee/components/Header'
import Editor from '@notee/components/Editor'

export default () => {

    return (
        <Page>
            <Page.Header>
                <Header />
            </Page.Header>
            <Page.Content>
                <Note>
                    <Note.Meta>
                        <Meta />
                    </Note.Meta>
                    <Note.Editor>
                        <Editor />
                    </Note.Editor>
                </Note>
            </Page.Content>
        </Page>
    )

}
