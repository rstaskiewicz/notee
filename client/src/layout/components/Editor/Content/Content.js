import styled from 'styled-components'

import Input from './Input'
import Note from './Note'
import Title from './Title'

const Content = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
    padding: 0 2.5em;
    box-sizing: border-box;
    flex-direction: column;
`

Content.Input = Input
Content.Note = Note
Content.Title = Title

export default Content
