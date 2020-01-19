import React, { forwardRef } from 'react'

import { Search } from '@notee/layout/components/Header'

export default forwardRef(({
    placeholder
}, ref) => {
    return (
        <Search>
            <Search.Input
                id="search"
                name="search"
                ref={ref}
                placeholder={placeholder}
            />
            <Search.Label
                htmlFor="search"
            >
                {placeholder}
            </Search.Label>
        </Search>
    )
})
