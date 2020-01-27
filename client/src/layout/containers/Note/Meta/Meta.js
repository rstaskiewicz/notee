import styled from 'styled-components'

import Labels from './Labels'
import Label from './Label'
import Button from './Button'
import Select from './Select'
import Input from './Input'

const Meta = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100%;
    padding: 1em;
    box-sizing: border-box;
`

Meta.Labels = Labels
Meta.Label = Label
Meta.Button = Button
Meta.Select = Select
Meta.Input = Input

export default Meta
