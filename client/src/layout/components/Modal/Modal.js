import styled from 'styled-components'

import Action from './Action'
import ButtonGroup from './ButtonGroup'
import Content from './Content'
import Footer from './Footer'
import Header from './Header'
import Input from './Input'
import Subtitle from './Subtitle'
import Title from './Title'

const Modal = styled.div`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 412px;
    height: 248px;
    background: #ffffff;
    box-shadow: 0 0 24px rgba(0, 0, 0, .16);
    box-sizing: border-box;
    border-radius: 8px;
    display: flex;
    flex-direction: column;
    z-index: 1001;
    padding: 1em 0;
    box-sizing: border-box;
`

Modal.Action = Action
Modal.ButtonGroup = ButtonGroup
Modal.Content = Content
Modal.Footer = Footer
Modal.Header = Header
Modal.Input = Input
Modal.Subtitle = Subtitle
Modal.Title = Title

export default Modal
