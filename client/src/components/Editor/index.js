import React, { useState } from 'react'
import './index.css'

import { Editor } from '@notee/layout/components/Editor'

import Header from './Header'
import Content from './Content'

export default ({
    title,
    author,
    createdAt,
    content,
    isEditable = true,
    titleRef,
    editorRef
}) => {

    return (
        <Editor>
            <Editor.Header>
                <Header
                    author={author}
                    createdAt={createdAt}
                />
            </Editor.Header>

            <Editor.Content>
                <Content
                    titleRef={titleRef}
                    editorRef={editorRef}
                    title={title}
                    content={content}
                    isEditable={isEditable}
                />
            </Editor.Content>
        </Editor>
    )
}
