import styled from 'styled-components'

import Logo from './Logo'
import Profile from './Profile'
import Navigation from './Navigation'
import Search from './Search'
import Section from './Section'
import Add from './Add'

const Header = styled.div`
    width: 100%;
    display: flex;
    align-items: center;
    height: 56px;
    background: white;
    box-shadow: 0 0 24px rgba(188, 188, 188, 0.1);
    box-sizing: border-box;
    position: fixed;
    z-index: 999;
`

Header.Logo = Logo
Header.Profile = Profile
Header.Navigation = Navigation
Header.Search = Search
Header.Section = Section
Header.Add = Add

export default Header
