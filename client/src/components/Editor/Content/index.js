import React, { useState } from 'react'
import EditorJS from 'react-editor-js'

import TOOLS from '../tools'

import { Content } from '@notee/layout/components/Editor'

export default ({
    title = 'Untitled',
    content = {},
    onSave = () => null
}) => {

    const [ currentTitle, setCurrentTitle ] = useState(title)

    const handleTitleChange = e => {
        setCurrentTitle(e.target.value)
    }

    return (
        <Content>

            <Content.Title>
                <Content.Input
                    type="text"
                    value={currentTitle}
                    onChange={handleTitleChange}
                />
            </Content.Title>

            <Content.Note>
                <EditorJS
                    tools={TOOLS}
                    data={content}
                    placeholder="Let`s write an awesome story!"
                />
            </Content.Note>

        </Content>
    )
}
