import React from 'react'

import Note from '@notee/layout/Note'
import Editor from '@notee/components/Editor'
import Header from '@notee/components/Header'



export default ({

note,
}
) => {

    return (
        <Note>
            <Header />
            <Note.Content>
                <Editor data={note}/>
            </Note.Content>
        </Note>
    )

}
