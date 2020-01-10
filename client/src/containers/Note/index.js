import React, { useState } from 'react'

import Note from '@notee/layout/Note'
import Editor from '@notee/components/Editor'
import Header from '@notee/components/Header'
import NotebookSelector from '@notee/components/NotebookSelector'

import { useSelector, useDispatch } from 'react-redux';
import { noteTitle } from '../../actions'

const notebook1 = {
    title: "Matematyka dyskretna",
    notes: [
        {
            title: "Prawdopodobieństwo",
            content: ""
        },
        {
            title: "Prawdopodobieństwo",
            content: ""
        },
        {
            title: "Funkcję różnowartościowe",
            content: ""
        }
    ]
}

const notebook2 = {
    title: "Statystyczna analiza danych",
    notes: [
        {
            title: "note 3",
            content: ""
        },
        {
            title: "note 3",
            content: ""
        }
    ]
}

const notebook3 = {
    title: "Podstawy programowania",
    notes: [
        {
            title: "note 1",
            content: ""
        },
        {
            title: "note 2",
            content: ""
        }
    ]
}
const array = [notebook1, notebook2, notebook3];

export default ({

    note,
    
}
) => {

    const noteTitle2 = useSelector(state => state.noteTitle)
    const [title, setTitle] = useState('');

    return (
        <Note>
            <Header />
            <Note.Content>
                <Note.Content.Segregator modifiers="dashboard-left-column">
                </Note.Content.Segregator>
                <Note.Content.Segregator modifiers="dashboard-center-column">
                    <Note.Input>
                        <input onInput={e => setTitle(e.target.value)} style={{borderStyle : 'none', fontSize : '1.0rem', minWidth : '200px'}} placeholder="Set your note's title here"></input>
                    </Note.Input>
                    <Editor data={note} />
                    {/* <button onClick={() => dispatch(noteTitle(title))}>{}</button> */}
                </Note.Content.Segregator>
                <Note.Content.Segregator modifiers="dashboard-right-column">
                    <NotebookSelector notebooks={array} />
                </Note.Content.Segregator>
            </Note.Content>
        </Note>
    )

}
