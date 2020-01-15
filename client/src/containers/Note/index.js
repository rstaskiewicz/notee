import React from 'react'

import Page from '@notee/layout/Page'
import Note from '@notee/layout/containers/Note'

import Editor from './Editor'
import Meta from './Meta'

import Header from '@notee/components/Header'

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
