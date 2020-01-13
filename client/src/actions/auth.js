import api from '../api'
import jwt from 'jsonwebtoken'

export const SING_IN = 'SING_IN'
export const SING_IN_FULFILLED = 'SING_IN_FULFILLED'
export const SING_IN_REJECTED = 'SING_IN_REJECTED'

const signingIn = () => ({
    type: SING_IN
})

const signedIn = payload => ({
    type: SING_IN_FULFILLED,
    payload
})

const signingInError = payload => ({
    type: SING_IN_REJECTED,
    payload
})

export const signIn = user => dispatch => {
    dispatch(signingIn)
    return api.auth.login(user)
        .then(data => {
            localStorage.setItem('token', data.token)
            api.setHeader({ 'Authorization': `Bearer ${data.token}` })
            return jwt.decode(data.token)
        })
        .then(user => dispatch(signedIn({ id: user.id, authenticated: true })))
        .catch(error => dispatch(signingInError(error)))

}

export const USE_TOKEN = 'USE_TOKEN'
export const USE_TOKEN_FULFILLED = 'USE_TOKEN_FULFILLED'
export const USE_TOKEN_REJECTED = 'USE_TOKEN_REJECTED'

const usingToken = () => ({
    type: USE_TOKEN
})

const tokenUsed = payload => ({
    type: USE_TOKEN_FULFILLED,
    payload
})

const tokenUsageError = payload => ({
    type: USE_TOKEN_REJECTED,
    payload
})

export const useToken = token => dispatch => {
    dispatch(usingToken)
    return Promise.resolve(jwt.decode(token))
        .then(user => dispatch(tokenUsed({ id: user.id, authenticated: true })))
        .catch(error => dispatch(tokenUsageError(error)))

}

export const SING_OUT = 'SING_OUT'

const signingOut = payload => ({
    type: SING_OUT,
    payload
})

export const signOut = () => dispatch => {

    dispatch(signingOut({
        id: '',
        profile: '',
        name: '',
        notebook: {},
        authenticated: false
    }))

    localStorage.removeItem('token')
    api.removeHeader('Authorization')
    return Promise.resolve()

}
