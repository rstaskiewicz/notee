import React, { forwardRef } from 'react'

import Search from '@notee/layout/Search'

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
