import React, { useState } from 'react'

import { Meta } from '@notee/layout/containers/Note'
import { Button } from '@notee/elements'

import Label from '@notee/components/Label'
import Select from '@notee/components/Select'

const defaultLabels = [
    'math',
    'homework',
    'notes',
    'in class',
    'to do'
]

const defaultOptions = [
    { name: 'Notebook 1', value: '1' },
    { name: 'Notebook 2', value: '2' },
    { name: 'Notebook 3', value: '3' }
]

export default () => {

    const [ inputLabel, setInputLabel ] = useState()
    const [ labels, setLabels ] = useState(defaultLabels)

    const handleLabelRemove = index => () => {
        setLabels(labels.filter((_, i) => i !== index))
    }

    const handleInputLabel = e => {
        setInputLabel(e.target.value)
    }

    const handleAddLabel = e => {

        const { value } = e.target

        if (e.key === 'Enter' && value.length > 0) {
            setLabels([ ...labels, value ])
            setInputLabel('')
        }

    }

    return (
        <Meta>

            <Meta.Select>
                <Select
                    placeholder="Pick notebook..."
                    options={defaultOptions}
                />
            </Meta.Select>

            <Meta.Input
                type="text"
                placeholder="Add label..."
                value={inputLabel}
                onChange={handleInputLabel}
                onKeyPress={handleAddLabel}
            />

            <Meta.Labels>

                {labels.map((label, index) => (
                    <Meta.Label key={index}>
                        <Label
                            onRemove={handleLabelRemove(index)}
                        >
                            {label}
                        </Label>
                    </Meta.Label>
                ))}
            </Meta.Labels>

            <Meta.Button>
                <Button
                    modifiers={[ 'primary', 'full-width' ]}
                >
                    Save
                </Button>
            </Meta.Button>

        </Meta>
    )
}
