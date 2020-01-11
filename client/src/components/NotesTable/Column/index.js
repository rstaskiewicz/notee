import React from 'react'

import Column from '@notee/layout/NotesTable/Column'

export default ({

    content,
    icon

}) => {

    return (
    <Column>{content} {icon}</Column>
    )
}