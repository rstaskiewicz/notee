import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPlus as plus } from '@fortawesome/free-solid-svg-icons'

import Headshot from '../../layout/Headshot'

import FileInput from '../FileInput'

export default () => {

    return (
        <FileInput>
            {({ isActive, isLoaded, file }) => (
                <Headshot>
                    <Headshot.Input
                        modifiers={[ (isActive || isLoaded) && 'primary' ]}
                    >
                        {(isLoaded && file)
                            ? <Headshot.Image src={file} />
                            : <Headshot.Icon
                                modifiers={[ (isActive || isLoaded) && 'primary' ]}
                            >
                                <FontAwesomeIcon icon={plus} />
                            </Headshot.Icon>}
                    </Headshot.Input>
                    <Headshot.Description
                        modifiers={[ (isActive || isLoaded) && 'primary' ]}
                    >
                        Profile picture
                    </Headshot.Description>
                </Headshot>
            )}
        </FileInput>
    )

}
