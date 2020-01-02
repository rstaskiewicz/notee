import React, { useState } from 'react'
import Checkbox from '../../layout/Checkbox'

export default ({
    name,
    label = '',
    tooltip,
    error,
    children = () => null,
    ...props
}) => {

    const [ isMouseOver, setIsMouseOver ] = useState(false)
    const [ isActive, setIsActive ] = useState(false)

    return (
        <Checkbox
            onMouseOver={() => setIsMouseOver(true)}
            onMouseLeave={() => setIsMouseOver(false)}
            htmlFor={name}
        >
            <Checkbox.Input
                id={name}
                name={name}
                type="checkbox"
                onChange={e => setIsActive(e.target.checked)}
                {...props}
            />
            <Checkbox.Checkmark />
            <Checkbox.Label>{children({ isMouseOver, isActive, error }) || label}</Checkbox.Label>
            {(tooltip || error) && (
                <Checkbox.Tooltip
                    modifiers={[ error && 'invalid' ]}
                >
                    {tooltip || error}
                </Checkbox.Tooltip>
            )}
        </Checkbox>
    )
}
