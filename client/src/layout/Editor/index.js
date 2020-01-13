import styled from 'styled-components'

import Author from './Author'
import Avatar from './Avatar'
import Content from './Content'
import Created from './Created'
import Header from './Header'
import Info from './Info'
import Input from './Input'
import Meta from './Meta'
import Note from './Note'
import Title from './Title'

const Editor = styled.div`
    display: flex;
    width: 100%;
    background: #ffffff;
    box-shadow: 0 0 24px rgba(188, 188, 188, 0.16);
    flex-direction: column;
    padding: .5em;
    box-sizing: border-box;
    border-radius: 8px;
    height: 100%;
`

Editor.Author = Author
Editor.Avatar = Avatar
Editor.Content = Content
Editor.Created = Created
Editor.Header = Header
Editor.Info = Info
Editor.Input = Input
Editor.Meta = Meta
Editor.Note = Note
Editor.Title = Title

export default Editor
