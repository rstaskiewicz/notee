import React from 'react'
import { Route, Redirect } from 'react-router-dom'
import { useSelector } from 'react-redux'

export default ({
    children,
    component: Component,
    ...rest
}) => {

    const { authenticated } = useSelector(state => state.user)

    return (
        <Route
            {...rest}
            render={({ location }) =>
                authenticated ? (
                    <Component /> || children
                ) : (
                        <Redirect
                            to={{
                                pathname: '/sign-in',
                                state: { from: location }
                            }}
                        />
                    )
            }
        />
    )
}
