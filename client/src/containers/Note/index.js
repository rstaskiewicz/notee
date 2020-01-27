import React, { useEffect } from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { useParams } from 'react-router'

import { Page } from '@notee/layout/shared/Page'
import { Note } from '@notee/layout/containers/Note'

import Meta from './Meta'

import Header from '@notee/components/Header'
import Editor from '@notee/components/Editor'

import { loadNote } from '@notee/actions/notes'

export default () => {

    const { id } = useParams()
    const note = useSelector(({ notes }) => notes.data[id])
    const dispatch = useDispatch()

    useEffect(() => {
        if (id)
            dispatch(loadNote(id))
    }, [ dispatch, id ])

    return (
        <Page>
            <Page.Header>
                <Header />
            </Page.Header>
            <Page.Content>
                <Note>
                    <Note.Meta>
                        <Meta />
                    </Note.Meta>
                    <Note.Editor>
                        <Editor />
                    </Note.Editor>
                </Note>
            </Page.Content>
        </Page>
    )

}
