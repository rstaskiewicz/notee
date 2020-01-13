import React from 'react'
import { BrowserRouter as Router, Switch, Route, Redirect } from 'react-router-dom'
import { Provider } from 'react-redux'

import PrivateRoute from '@notee/components/PrivateRoute'

import SignIn from './SignIn'
import SignUp from './SignUp'
import Dashboard from './Dashboard'
import Note from './Note'
import Notebooks from './Notebooks'
import List from './List'

export default ({ store }) => {

    return (
        <Provider store={store}>
            <Router>
                <Switch>
                    <Route path='/sign-in' component={SignIn} />
                    <Route path='/sign-up' component={SignUp} />
                    <PrivateRoute path='/dashboard' component={Dashboard} />
                    <PrivateRoute path='/note/new' component={Note} />
                    <PrivateRoute path='/note/:id' component={Note} />
                    <PrivateRoute path='/notebooks' component={Notebooks} />
                    <PrivateRoute path='/list' component={List} />
                    <Redirect path="*" to="/sign-in" />
                </Switch>
            </Router>
        </Provider>
    )

}
