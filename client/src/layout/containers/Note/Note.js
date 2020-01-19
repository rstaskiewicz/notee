import styled from 'styled-components'

import Editor from './Editor'
import Meta from './Meta'

const Note = styled.div`
    width: 100%;
    display: flex;
    justify-content: center;
    height: 100%;
`

Note.Editor = Editor
Note.Meta = Meta

export default Note
