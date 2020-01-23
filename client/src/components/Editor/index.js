import React from 'react'
import './index.css'

import { Editor } from '@notee/layout/components/Editor'

import Header from './Header'
import Content from './Content'

export default ({ note }) => {
    if (note) {
        const { name, createdBy, createdAt, content } = note
        return (
            <Editor>
                <Editor.Header>
                    <Header createdBy={createdBy} createdAt={createdAt}/>
                </Editor.Header>

                <Editor.Content>
                    <Content name={name} content={content} />
                </Editor.Content>
            </Editor>
        )
    }
    return (
        <div>
            Loading...
        </div>
    )
}
