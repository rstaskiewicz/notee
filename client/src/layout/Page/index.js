import styled from 'styled-components'

import Header from './Header'
import Content from './Content'

const Page = styled.div`
    display: flex;
    width: 100%;
    flex-direction: column;
`

Page.Header = Header
Page.Content = Content

export default Page
