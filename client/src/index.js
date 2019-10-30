import React from 'react'
import ReactDOM from 'react-dom'
import 'reset.css'
import Root from './containers/Root'
import * as serviceWorker from './serviceWorker'

ReactDOM.render(<Root />, document.getElementById('root'))
serviceWorker.unregister()
