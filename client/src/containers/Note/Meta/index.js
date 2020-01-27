import React, { useState } from 'react'

import { Meta } from '@notee/layout/containers/Note'
import { Button } from '@notee/elements'

import Label from '@notee/components/Label'
import Select from '@notee/components/Select'

export default ({
    notebooks = [],
    labels = [],
    onSave = () => null
}) => {

    const [ inputLabel, setInputLabel ] = useState()
    const [ currentLabels, setCurrentLabels ] = useState(labels)
    const [ selectedNotebook, setSelectedNotebook ] = useState(null)

    const handleLabelRemove = index => () => {
        setCurrentLabels(labels.filter((_, i) => i !== index))
    }

    const handleInputLabel = e => {
        setInputLabel(e.target.value)
    }

    const handleAddLabel = e => {

        const { value } = e.target

        if (e.key === 'Enter' && value.length > 0) {
            setCurrentLabels([ ...labels, value ])
            setInputLabel('')
        }

    }

    const handleNotebookSelect = notebook => {
        setSelectedNotebook(notebook)
    }

    const handleSave = () => {
        onSave({
            labels: currentLabels,
            notebook: selectedNotebook
        })
    }

    return (
        <Meta>

            <Meta.Select>
                <Select
                    placeholder="Pick notebook..."
                    options={notebooks}
                    onSelect={handleNotebookSelect}
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

                {currentLabels.map((label, index) => (
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
                    onClick={handleSave}
                >
                    Save
                </Button>
            </Meta.Button>

        </Meta>
    )
}
