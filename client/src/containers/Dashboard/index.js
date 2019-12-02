import React from 'react'

import { Image } from '../../elements'

import mock from '../../assets/Dashboard.png'
import styled from 'styled-components'

const Wrapper = styled.div`
    width: 100%;
    height: 100vh;
`

export default () => {

    return (
        <Wrapper>
            <Image modifiers="cover" src={mock} />
        </Wrapper>
    )

}
