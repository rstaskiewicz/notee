import React from 'react'
// import { BrowserRouter as Router, Switch, Route, Redirect } from 'react-router-dom'

// import SignIn from './SignIn'
// import SignUp from './SignUp'
import Dashboard from './Dashboard'

const Root = () => {

    return (
        // <Router>
        //     <Switch>
        //         <Route path='/sign-in' component={SignIn} />
        //         <Route path='/sign-up' component={SignUp} />
        //         <Route path='/dashboard' component={Dashboard} />
        //         <Redirect path="*" to="/sign-in" />
        //     </Switch>
        // </Router>
        <Dashboard />
    )

}

export default Root
