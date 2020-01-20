import React from 'react'
import EditorJS from 'react-editor-js'

import TOOLS from '../tools'

import { Content } from '@notee/layout/components/Editor'

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
    return (
        <Content>

            <Content.Title>
                <Content.Input
                    type="text"
                    placeholder="Untitled note..."
                />
            </Content.Title>

            <Content.Note>
                <EditorJS
                    tools={TOOLS}
                    data={note.content}
                    onChange={console.log}
                    onReady={() => console.log('starting editor.js ...')}
                    onData={e => console.log('data saved: ', e)}
                />
            </Content.Note>

        </Content>
    )
}
