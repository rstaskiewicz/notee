import styled from 'styled-components'

import Labels from './Labels'
import Label from './Label'

const Meta = styled.div`
    display: flex;
    width: 100%;
    padding: 1em;
    box-sizing: border-box;
`

Meta.Labels = Labels
Meta.Label = Label

export default Meta
