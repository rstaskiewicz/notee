import React, { useState } from 'react'
import Notebook from '../../../layout/NotebookList/Notebook'
import P from '../../../elements/P'



export default ({
    title,

}) => {

    const [isClicked, setIsClicked] = useState(true)
    

    return (

        <Notebook>
            <Notebook.P><P onClick={() => setIsClicked(!isClicked)} >{title}</P></Notebook.P>
        </Notebook>
    )
}
