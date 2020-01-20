import React, { useState } from 'react'

import { Page } from '@notee/layout/shared/Page'
import { Note } from '@notee/layout/containers/Note'

import Meta from './Meta'

import { Button } from '@notee/elements'

import Header from '@notee/components/Header'
import Editor from '@notee/components/Editor'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faSave as save,
} from '@fortawesome/free-solid-svg-icons'

export default () => {

    const [isClicked, setIsClicked] = useState(false)

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
