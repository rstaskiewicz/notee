import React from 'react'

import { Label } from '@notee/layout/components/Label'

export default ({
    content,
    children
}) => {
    return (
        <Label>
            <Label.Text>{content || children}</Label.Text>
        </Label>
    )
}
