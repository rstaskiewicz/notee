import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

import Header from './Header'
import Sidebar from './Sidebar'
import Content from './Content'

const PAGE_MODIFIERS = {

    'with-sidebar': () => css`
        grid-template-rows: 56px calc(100vh - 56px);
        grid-template-columns: 224px auto;
        grid-template-areas:
          "header  header"
          "sidebar content";
    `

}

const Page = styled.div`
    display: grid;
    width: 100%;
    grid-template-areas: "header" "content";
    grid-template-rows: 56px calc(100vh - 56px);
    grid-template-columns: auto;
    ${applyStyleModifiers(PAGE_MODIFIERS)}
`

Page.Header = Header
Page.Content = Content
Page.Sidebar = Sidebar

export default Page
