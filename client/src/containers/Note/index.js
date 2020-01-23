import React, { useState, useEffect } from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { useParams } from 'react-router'

import { Page } from '@notee/layout/shared/Page'
import { Note } from '@notee/layout/containers/Note'

import Meta from './Meta'

import { Button } from '@notee/elements'

import Header from '@notee/components/Header'
import Editor from '@notee/components/Editor'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faSave as save
} from '@fortawesome/free-solid-svg-icons'

import { loadNote } from '@notee/actions/notes'

export default () => {

    const [ isClicked, setIsClicked ] = useState(false)
    const { id } = useParams()
    const note = useSelector(({ notes }) => notes.data[id])
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(loadNote(id))
    }, [ dispatch ])

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
                        <Editor note={note}/>
                        <div style={{background: "#ffffff", margin: "24px 0px", textAlign: "left", borderRadius: "6px"}}>
                            <Button onClick={() => setIsClicked(true)} modifiers="note-save">
                                <Button.FontAwesomeIcon>
                                    <FontAwesomeIcon icon={save} />
                                </Button.FontAwesomeIcon>
                                SAVE NOTE </Button>
                            <span style={{ fontFamily: "Lato", fontSize: ".825rem", color: "#bbb9c7", fontWeight: "550" }}>{isClicked ? "Note saved!" : "Rember to save your note!"}</span>
                        </div>
                    </Note.Editor>
                </Note>
            </Page.Content>
        </Page>
    )

}
