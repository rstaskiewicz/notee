import React, { useState } from 'react'

import { Select } from '@notee/layout/components/Select'

export default ({
    placeholder = '',
    options = [],
    onSelect = () => null
}) => {

    const [ isOpen, setIsOpen ] = useState(false)
    const [ selected, setSelected ] = useState({
        name: placeholder,
        value: null
    })

    const handleClick = index => () => {
        const picked = options[index]
        setSelected(picked)
        setIsOpen(false)
        onSelect(picked)
    }

    return (
        <Select>
            <Select.Input
                onClick={() => setIsOpen(!isOpen)}
            >
                {selected.name}
            </Select.Input>
            {isOpen && (
                <Select.Dropdown>
                    <Select.List>
                        {options.map((item, index) => (
                            <Select.Item
                                key={index}
                                onClick={handleClick(index)}
                            >
                                {item.name}
                            </Select.Item>
                        ))}
                    </Select.List>
                </Select.Dropdown>
            )}
        </Select>
    )
}
