import styled from 'styled-components'

const Modal = styled.div`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 648px;
    height: 448px;
    background: #ffffff;
    box-shadow: 0 0 24px rgba(0, 0, 0, .16);
    box-sizing: border-box;
    border-radius: 8px;
    display: flex;
    z-index: 1001;
`

export default Modal
