import React from 'react'

import { Preview } from '@notee/layout/containers/Notebooks'
import Editor from '@notee/components/Editor'

export default () => {
    return (
        <Preview>
            <Preview.Note>
                <Editor />
            </Preview.Note>
        </Preview>
    )
}
