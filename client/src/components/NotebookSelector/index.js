import React from 'react'

import NotebookSelector from '@notee/layout/NotebookSelector'
import Notebook from './Notebook'


export default ({

        notebooks,

}) => {

        return (
                <NotebookSelector>
                        {notebooks.map((notebook, index) =>(
                                <Notebook key={index} title={notebook.title} />
                        ))}
                </NotebookSelector>
        )
}
