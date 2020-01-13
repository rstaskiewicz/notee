import React from 'react'

import Meta from '@notee/layout/Meta'

import Label from '@notee/components/Label'

const labels = [
    'math',
    'homework',
    'notes',
    'in class',
    'to do'
]

export default () => {
    return (
        <Meta>
            <Meta.Labels>

                {labels.map((label, index) => (
                    <Meta.Label key={index}>
                        <Label>{label}</Label>
                    </Meta.Label>
                ))}

            </Meta.Labels>
        </Meta>
    )
}
