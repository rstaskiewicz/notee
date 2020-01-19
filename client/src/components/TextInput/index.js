import React, { forwardRef } from 'react'

import { TextInput } from '@notee/layout/components/TextInput'

export default forwardRef(({
    label,
    name,
    placeholder,
    tooltip,
    error,
    ...props
}, ref) => {
    return (
        <TextInput>
            <TextInput.Input
                id={name}
                name={name}
                ref={ref}
                placeholder={placeholder}
                modifiers={[ error && 'invalid' ]}
                {...props}
            />
            <TextInput.Label
                htmlFor={name}
                modifiers={[ error && 'invalid' ]}
            >
                {placeholder}
            </TextInput.Label>
            {(tooltip || error) && (
                <TextInput.Tooltip
                    modifiers={[ error && 'invalid' ]}
                >
                    {tooltip || error}
                </TextInput.Tooltip>
            )}
        </TextInput>
    )
})
