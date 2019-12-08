import React from 'react'
import ReactDOM from 'react-dom'
import 'reset.css'
import Root from './containers/Root'
import store from './store'
import * as serviceWorker from './serviceWorker'

ReactDOM.render(<Root store={store} />, document.getElementById('root'))
serviceWorker.unregister()
