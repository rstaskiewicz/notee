import React from 'react'

import NotebookList from '../../layout/NotebookList'
import A from '../../elements/A'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faChevronRight as chevronRight,
} from '@fortawesome/free-solid-svg-icons'

export default ({


        Notebooks,

        NotebooksToList = Notebooks.map((Notebook) =>

                <NotebookList.Notebook Notes = {Notebook.notes}>{Notebook.title}
                <NotebookList.Notebook.Note><FontAwesomeIcon color="#BBB9C7" icon={chevronRight} /><A>{Notebook.notes[0]}</A></NotebookList.Notebook.Note>
                <NotebookList.Notebook.Note><FontAwesomeIcon color="#BBB9C7" icon={chevronRight} /><A>{Notebook.notes[1]}</A></NotebookList.Notebook.Note>
                </NotebookList.Notebook>
        )

}) => {

        return (
                <NotebookList>
                        {NotebooksToList}
                </NotebookList>
        )
}
