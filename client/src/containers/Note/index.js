import React, { useEffect, useRef } from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { useParams } from 'react-router'

import { Page } from '@notee/layout/shared/Page'
import { Note } from '@notee/layout/containers/Note'

import Meta from './Meta'

import Header from '@notee/components/Header'
import Editor from '@notee/components/Editor'

import { loadNote } from '@notee/actions/notes'

const defaultLabels = [
    'math',
    'homework',
    'notes',
    'in class',
    'to do'
]

const defaultNotebooks = [
    { name: 'Notebook 1', value: '1' },
    { name: 'Notebook 2', value: '2' },
    { name: 'Notebook 3', value: '3' }
]

export default () => {

    const { id } = useParams()
    const note = useSelector(({ notes }) => notes.data[id])
    const dispatch = useDispatch()

    useEffect(() => {
        if (id)
            dispatch(loadNote(id))
    }, [ dispatch, id ])

    const titleRef = useRef(null)
    const editorRef = useRef(null)

    const handleOnSave = meta => {

        const title = titleRef.current.value
        const editor = editorRef.current.instance

        editor.save()
            .then(data => ({ title, content: data, ...meta }))
            .then(console.log)

    }

    return (
        <Page>
            <Page.Header>
                <Header />
            </Page.Header>
            <Page.Content>
                <Note>
                    <Note.Meta>
                        <Meta
                            notebooks={defaultNotebooks}
                            labels={defaultLabels}
                            onSave={handleOnSave}
                        />
                    </Note.Meta>
                    <Note.Editor>
                        <Editor
                            titleRef={titleRef}
                            editorRef={editorRef}
                        />
                    </Note.Editor>
                </Note>
            </Page.Content>
        </Page>
    )

}
