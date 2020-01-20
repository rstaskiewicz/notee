import styled from 'styled-components'

import Content from './Content'
import Header from './Header'

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

Editor.Header = Header
Editor.Content = Content

export default Editor
