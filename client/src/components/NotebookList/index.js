import React from 'react'

import NotebookList from '../../layout/NotebookList'
import Notebook from './Notebook'

export default ({ notebooks }) => {

        return (
                <NotebookList>
                    {Object.values(notebooks).map(({ notebookId, notebookName }) => (
                        <Notebook key={notebookId} title={notebookName} notes={[]} />))}
                </NotebookList>
        )
}
