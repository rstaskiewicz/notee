import React from 'react'
import { Route, Redirect } from 'react-router-dom'
import { useSelector } from 'react-redux'

export default ({
    children,
    component: Component,
    ...rest
}) => {
    const { userId } = useSelector(state => state.auth.data)
    return (
        <Route
            {...rest}
            render={({ location }) =>
                userId ? (
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
