import React from 'react'

import Cell from '@notee/layout/NotesTable/Cell'

export default ({

    content,
    mod

}) => {

    return (
        <Cell modifiers={mod}>{content}</Cell>
    )
}