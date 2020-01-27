import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faTimes as remove
} from '@fortawesome/free-solid-svg-icons'

import { Label } from '@notee/layout/components/Label'

export default ({
    content,
    children,
    onRemove = () => null
}) => {
    return (
        <Label>
            <Label.Text>{content || children}</Label.Text>
            <Label.Icon onClick={onRemove}>
                &nbsp;<FontAwesomeIcon icon={remove} fixedWidth />
            </Label.Icon>
        </Label>
    )
}
