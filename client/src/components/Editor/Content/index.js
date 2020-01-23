import React from 'react'
import EditorJS from 'react-editor-js'

import TOOLS from '../tools'

import { Content } from '@notee/layout/components/Editor'

export default ({ name, content }) => {
    return (
        <Content>

            <Content.Title>
                <Content.Input
                    type="text"
                    placeholder={name}
                />
            </Content.Title>

            <Content.Note>
                <EditorJS
                    tools={TOOLS}
                    data={content}
                    onChange={console.log}
                    onReady={() => console.log('starting editor.js ...')}
                    onData={e => console.log('data saved: ', e)}
                />
            </Content.Note>

        </Content>
    )
}
