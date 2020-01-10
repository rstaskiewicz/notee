import React from 'react'
import { BrowserRouter as Router, Switch, Route, Redirect } from 'react-router-dom'
import { Provider } from 'react-redux'

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
                    <Route path='/dashboard' component={Dashboard} />
                    <Route path='/note' component={Note} />
                    <Route path='/notebooks' component={Notebooks} />
                    <Route path='/list' component={List} />
                    <Redirect path="*" to="/sign-in" />
                </Switch>
            </Router>
        </Provider>
    )

}
