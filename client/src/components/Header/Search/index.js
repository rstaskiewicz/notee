import React, { forwardRef, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'

import { Search } from '@notee/layout/components/Header'
import Dropdown from '@notee/components/Dropdown'
import { search } from '@notee/actions/search'

export default forwardRef(({
    placeholder
}, ref) => {

    const [ shouldOpen, setShouldOpen ] = useState(false)
    const { data, loading } = useSelector(state => state.search)
    const dispatch = useDispatch()

    const handleOnChange = event => {
        const input = event.target.value
        setShouldOpen(true)
        dispatch(search(input))
    }

    return (
        <Search>
            <Search.Input
                id="search"
                name="search"
                ref={ref}
                placeholder={placeholder}
                onChange={handleOnChange}
                onBlur={() => setShouldOpen(false)}
            />
            <Search.Label
                htmlFor="search"
            >
                {placeholder}
            </Search.Label>
            <Search.Dropdown>
                {(!loading && shouldOpen) && <Dropdown data={data} />}
            </Search.Dropdown>
        </Search>
    )
})
