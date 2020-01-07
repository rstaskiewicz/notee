import React from 'react'

import Navigation from '@notee/layout/Navigation'

export default () => {

    return(
        <Navigation>
            <Navigation.Link href="/dashboard">Dashboard</Navigation.Link>
            <Navigation.Link href="/list">List</Navigation.Link>
            <Navigation.Link href="/notebooks">Notebooks</Navigation.Link>
        </Navigation>
    )

}
