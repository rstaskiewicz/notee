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

    return(
        <Footer>
            <Button modifiers="card-borderless">
                <FontAwesomeIcon icon={heart}/>
                Like
            </Button>
            <Button modifiers="card-borderless">
                <FontAwesomeIcon icon={pen}/>
                Edit
            </Button>
            <Button modifiers="card-borderless">
                <FontAwesomeIcon icon={bookmark}/>
                Save
            </Button>
        </Footer>
    )

}
