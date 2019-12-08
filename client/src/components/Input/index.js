import React, { forwardRef } from 'react'

import TextField from '../../layout/TextField'

export default forwardRef(({
    label,
    name,
    placeholder,
    tooltip,
    error,
    ...props
}, ref) => {
    return (
        <TextField>
            <TextField.Input
                id={name}
                name={name}
                ref={ref}
                placeholder={placeholder}
                modifiers={[ error && 'invalid' ]}
                {...props}
            />
            <TextField.Label
                htmlFor={name}
                modifiers={[ error && 'invalid' ]}
            >
                {placeholder}
            </TextField.Label>
            {(tooltip || error) && (
                <TextField.Tooltip
                    modifiers={[ error && 'invalid' ]}
                >
                    {tooltip || error}
                </TextField.Tooltip>
            )}
        </TextField>
    )
})
