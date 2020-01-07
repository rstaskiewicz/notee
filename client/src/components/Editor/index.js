import React from 'react'

import Editor from '@notee/layout/Editor'
import EditorJS from '@stfy/react-editor.js'
import Header from '@editorjs/header'
import List from '@editorjs/list'
import Embed from '@editorjs/embed'
import Link from '@editorjs/link'
import Raw from '@editorjs/raw'
import Image from '@editorjs/image'
import Checklist from '@editorjs/checklist'
import Quote from '@editorjs/quote'
import Table from '@editorjs/table'
import Marker from '@editorjs/marker'

export default () => {

    return (
        <Editor>
            <Editor.EditorJS>
                <EditorJS
                    tools={{
                        header: Header,
                        list: List,
                        embed: Embed,
                        link: Link,
                        raw: Raw,
                        image: Image,
                        checklist: Checklist,
                        quote: Quote,
                        table: Table,
                        marker: Marker,
                    }}
                    onReady={() => console.log('starting editor.js ...')}
                    onData={e => console.log('data saved: ', e)}
                    data={{
                        "time": 1569611146631,
                        "blocks": [
                            {
                                "type": "header",
                                "data": {
                                    "text": "Edytuj mnie!",
                                    "level": 2
                                }
                            },
                        ],
                        "version": "2.15.0"
                    }}
                />
            </Editor.EditorJS>
        </Editor>
    )
}
