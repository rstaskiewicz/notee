import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import Footer from '@notee/layout/Card/Footer'
import {
    faHeart as heart,
    faPen as pen,
    faBookmark as bookmark,
    faTrashAlt as trash
} from '@fortawesome/free-solid-svg-icons'

import { Button } from '@notee/elements'

const DashboardCardFooter = () => {

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

const NotebooksCardFooter = () => {

    return (
        <Footer modifiers="notebooks-card-footer">
            <Button modifiers="notebooks-card-borderless">
                <Button.FontAwesomeIcon>
                    <FontAwesomeIcon icon={pen} />
                </Button.FontAwesomeIcon>
            </Button>
            <Button modifiers="notebooks-card-borderless">
                <Button.FontAwesomeIcon>
                    <FontAwesomeIcon icon={trash} />
                </Button.FontAwesomeIcon>
            </Button>

        </Footer>
    )

}

export {DashboardCardFooter, NotebooksCardFooter}