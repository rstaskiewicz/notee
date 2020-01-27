import React, { useState } from 'react'
import './index.css'

import { Editor } from '@notee/layout/components/Editor'

import Header from './Header'
import Content from './Content'

export default ({
    id,
    note,
    titleRef,
    editorRef
}) => {

    if (id && note) {
        const { name, createdBy, createdAt, content } = note
        return (
            <Editor>
                <Editor.Header>
                    <Header author={createdBy} createdAt={createdAt}/>
                </Editor.Header>

                <Editor.Content>
                    <Content title={name}
                             content={content}
                             titleRef={titleRef}
                             editorRef={editorRef} />
                </Editor.Content>
            </Editor>
        )

    } else if (!id) {
        return (
            <Editor>
                <Editor.Header>
                    <Header/>
                </Editor.Header>

                <Editor.Content>
                    <Content
                             titleRef={titleRef}
                             editorRef={editorRef} />
                </Editor.Content>
            </Editor>
        )
    }
    return (<div>Loading...</div>)
}
