import React from 'react'
import ReactDOM from 'react-dom'
import 'reset.css'
import Root from './containers/Root'
import * as serviceWorker from './serviceWorker'
import SignIn from './containers/SignIn'

ReactDOM.render(<SignIn />, document.getElementById('root'))
serviceWorker.unregister()
