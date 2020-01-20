import React from 'react'
import './index.css'

import { Editor } from '@notee/layout/components/Editor'

import Header from './Header'
import Content from './Content'

export default () => {

    return (
        <Editor>

            <Editor.Header>
                <Header />
            </Editor.Header>

            <Editor.Content>
                <Content />
            </Editor.Content>

        </Editor>
    )
}
