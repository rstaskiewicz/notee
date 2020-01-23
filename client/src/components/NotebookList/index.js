import React from 'react'

import NotebookList from '../../layout/NotebookList'
import Notebook from './Notebook'

export default ({ notebooks }) => (
    <NotebookList>
        {notebooks.map(({ notebookId, notebookName, notes }) => (
            <Notebook key={notebookId} notebookName={notebookName} notes={notes} />))}
    </NotebookList>
)
