import React from 'react'

import Navigation from '@notee/layout/Navigation'

export default () => {

    return(
        <Navigation>
            <Navigation.Link>Dashboard</Navigation.Link>
            <Navigation.Link>List</Navigation.Link>
            <Navigation.Link>Notebooks</Navigation.Link>
        </Navigation>
    )

}
