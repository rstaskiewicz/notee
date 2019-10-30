import React, { forwardRef } from 'react'

import TextField from '../../layout/TextField'

export default forwardRef(({
    label,
    name,
    placeholder,
    tooltip = '',
    ...props
}, ref) => {
    return (
        <TextField>
            <TextField.Input
                id={name}
                name={name}
                ref={ref}
                placeholder={placeholder}
                {...props}
            />
            <TextField.Label htmlFor={name}>{placeholder}</TextField.Label>
            {tooltip && <TextField.Tooltip>{tooltip}</TextField.Tooltip>}
        </TextField>
    )
})
