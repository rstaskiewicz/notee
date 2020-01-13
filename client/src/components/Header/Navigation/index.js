import React from 'react'

import Navigation from '@notee/layout/Navigation'

export default () => {

    return(
        <Navigation>
            <Navigation.List>

                <Navigation.Item>
                    <Navigation.Link modifiers="active" href="/dashboard">Dashboard</Navigation.Link>
                </Navigation.Item>

                <Navigation.Item>
                    <Navigation.Link href="/list">List</Navigation.Link>
                </Navigation.Item>

                <Navigation.Item>
                    <Navigation.Link href="/notebooks">Notebooks</Navigation.Link>
                </Navigation.Item>

            </Navigation.List>
        </Navigation>
    )

}
