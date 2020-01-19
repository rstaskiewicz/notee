import styled from 'styled-components'

export default styled.div`
    width: 100%;
    display: grid;
    grid-template-columns: 1fr 2fr 1fr 1fr auto;
    justify-items: center;
    align-items: center;
    height: 64px;
    border-bottom: 1px solid #efefef;

    &:last-of-type {
        border-bottom: none;
    }
`
