import React from 'react'

import NotebookList from '../../layout/NotebookList'
import Notebook from './Notebook'

export default ({

        notebooks,

}) => {

        return (
                <NotebookList>
                        {notebooks.map((notebook, index) =>(
                                <Notebook key={index} title={notebook.title} notes={notebook.notes} />
                        ))}
                </NotebookList>
        )
}
