import React, { Fragment } from 'react'

import { Dropdown } from '@notee/layout/components/Dropdown'

import List from './List'

export default ({
    data = {}
}) => {
    return (
        <Dropdown>
            {Object.entries(data)
                .map(([ title, values = [] ], index) => (
                    <Fragment key={index}>
                        <Dropdown.Title>
                            {title.charAt(0).toUpperCase() + title.slice(1)}
                        </Dropdown.Title>
                        <Dropdown.List>
                            <List data={values} />
                        </Dropdown.List>
                    </Fragment>
                ))}
        </Dropdown>
    )

}
