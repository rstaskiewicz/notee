import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import Footer from '@notee/layout/Card/Footer'
import {
    faHeart as heart,
    faPen as pen,
    faBookmark as bookmark
} from '@fortawesome/free-solid-svg-icons'

import { Button } from '@notee/elements'

export default () => {

    return (
        <Footer>
            <Button modifiers="card-borderless">
                <Button.FontAwesomeIcon>
                    <FontAwesomeIcon icon={heart} />
                </Button.FontAwesomeIcon>
                Like
            </Button>
            <Button modifiers="card-borderless">
                <Button.FontAwesomeIcon>
                    <FontAwesomeIcon icon={pen} />
                </Button.FontAwesomeIcon>
                Edit
            </Button>
            <Button modifiers="card-borderless">
                <Button.FontAwesomeIcon>
                    <FontAwesomeIcon icon={bookmark} />
                </Button.FontAwesomeIcon>
                Save
            </Button>

        </Footer>
    )

}
