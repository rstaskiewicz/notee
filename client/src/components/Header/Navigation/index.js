import React from 'react'
import { useLocation } from 'react-router-dom'

import { Navigation } from '@notee/layout/components/Header'

export default () => {

    const { pathname } = useLocation()
    const navigation = [
        { name: 'Dashboard', to: '/dashboard' },
        { name: 'List', to: '/list' },
        { name: 'Notebooks', to: '/notebooks' }
    ]

    return(
        <Navigation>
            <Navigation.List>
                {navigation.map((item, index) => (
                    <Navigation.Item
                        key={index}
                        modifiers={[ pathname === item.to && 'active' ]}
                    >
                        <Navigation.Link
                            to={item.to}
                            modifiers={[ pathname === item.to && 'active' ]}
                        >
                            {item.name}
                        </Navigation.Link>
                    </Navigation.Item>
                ))}

            </Navigation.List>
        </Navigation>
    )

}
