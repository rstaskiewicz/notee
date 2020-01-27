import React from 'react'
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
    onChange = () => null
}) => {

    const handleTitleChange = e => {
        onChange({
            title: e.target.value,
            content
        })
    }

    const handleContentChange = e => {
        console.log(e)
        onChange({
            title
        })
    }

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
                    title={title}
                    content={content}
                    isEditable={isEditable}
                    handleTitleChange={handleTitleChange}
                    handleContentChange={handleContentChange}
                />
            </Editor.Content>
        </Editor>
    )
}
