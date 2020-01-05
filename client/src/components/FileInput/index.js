import React, { useState } from 'react'

import FileInput from '@notee/layout/FileInput'

export default ({
    accept = null,
    onFile = () => null,
    validate = () => true,
    children = () => null
}) => {

    const [ isActive, setIsActive ] = useState(false)
    const [ isLoaded, setIsLoaded ] = useState(false)
    const [ file, setFile ] = useState(null)

    const handleOnChange = e => {

        const reader = new FileReader()
        const file = e.dataTransfer
            ? e.dataTransfer.files[0]
            : e.target.files[0]

        if (!validate(file))
            return

        reader.onload = () => {
            setFile(reader.result)
            setIsLoaded(true)
            onFile(({ target: { value: reader.result } }))
        }

        reader.readAsDataURL(file)

    }

    const handleOnDrop = e => {
        e.preventDefault()
        setIsActive(false)
        handleOnChange(e)
    }

    return (
        <FileInput.Label
            onDragEnter={() => setIsActive(true)}
            onDragLeave={() => setIsActive(false)}
            onDragOver={e => e.preventDefault()}
            onDrop={handleOnDrop}
        >

            {children({ isActive, isLoaded, file })}

            <FileInput.Input
                type="file"
                accept={accept}
                onChange={handleOnChange}
            />

        </FileInput.Label>
    )

}
