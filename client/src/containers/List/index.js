import React from 'react'

import List from '@notee/layout/List'

import Header from '@notee/components/Header'
import NotesTable from '@notee/components/NotesTable'


const note1 = {
    user: {
        name: "Joanna Kowalska",
    },
    notebook: {
        title: "notebook1",
        code: "ASD"
    },
    noteTitle: "note1",
    date: "11-01-2020"
}

const array = [note1, note1]

export default (

) => {

    return (
        <List>
            <Header />
            <List.Content>
                <NotesTable notes={array} />
            </List.Content>
        </List>
    )

}
