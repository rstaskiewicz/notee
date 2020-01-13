import React from 'react'
import EditorJS from 'react-editor-js'
import './index.css'

import TOOLS from './tools'

import Editor from '@notee/layout/Editor'

import Avatar from '@notee/components/Avatar'
import { useSelector } from 'react-redux'

const note = {
    content: {
        time: 1550476186479,
        blocks: [
            {
                type: 'header',
                data: {
                    text: 'Sometimes is simple',
                    level: 2
                }
            },
            {
                type: 'paragraph',
                data: {
                    text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec luctus at turpis et bibendum. Aliquam a urna eget sem pharetra egestas at viverra arcu. Aenean ultricies, justo pharetra placerat blandit, tellus ex tincidunt justo, tristique luctus tellus nunc sit amet tortor. Ut sit amet venenatis nunc. Vivamus porta cursus orci at venenatis. Donec posuere nunc nisi, et semper nisi faucibus in. Fusce laoreet dui eget rutrum pulvinar. Phasellus in turpis velit. Aliquam elementum, nulla vel lacinia finibus, ante enim mollis purus, eget auctor mauris neque sed erat. Donec venenatis ante ac ex suscipit, vel feugiat diam mollis. Phasellus ut nulla in est maximus rutrum quis vitae est. In augue dui, varius ut quam ac, tempor maximus diam. Suspendisse varius id nibh in aliquet. Nunc velit ligula, tincidunt a justo eu, condimentum ultrices tellus. Integer luctus consectetur eros a congue.'
                }
            }
        ]
    }
}

export default () => {

    const user = useSelector(state => state.user)

    return (
        <Editor>

            <Editor.Header>

                <Editor.Info>
                    <Editor.Meta>
                        <Editor.Author>Author: Joanna Kowalska</Editor.Author>
                        <Editor.Created>Created: 5 June 2019</Editor.Created>
                    </Editor.Meta>
                    <Editor.Avatar>
                        <Avatar
                            status={false}
                            image={user.avatar}
                        />
                    </Editor.Avatar>
                </Editor.Info>

            </Editor.Header>

            <Editor.Content>

                <Editor.Title>
                    <Editor.Input
                        type="text"
                        placeholder="Untitled note..."
                    />
                </Editor.Title>

                <Editor.Note>
                    <EditorJS
                        tools={TOOLS}
                        data={note.content}
                        onChange={console.log}
                        onReady={() => console.log('starting editor.js ...')}
                        onData={e => console.log('data saved: ', e)}
                    />
                </Editor.Note>

            </Editor.Content>

        </Editor>
    )
}
