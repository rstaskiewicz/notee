import React from 'react'

import Notebook from '../../layout/Notebook'


export default ({

    Notes,

    NotesToList = Notes.map((Note) =>
        <Notebook.Note>{Note}</Notebook.Note>
    )

}) => {

    // const [ isClicked, setIsClicked] = useState(false)

    return (
        <Notebook>
            {NotesToList}
        </Notebook>
    )
}
