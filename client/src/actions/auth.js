import axios from 'axios'
import queryString from 'query-string'
import jwt from 'jsonwebtoken'

const AUTH_API = 'http://localhost:8081/oauth/token'

export const SIGN_IN_REQUEST = '@SIGN_IN_REQUEST'
export const SIGN_IN_SUCCESS = '@SIGN_IN_SUCCESS'
export const SIGN_IN_FAILURE = '@SIGN_IN_FAILURE'

export const signIn = ({ mail, password }) => dispatch => {
    dispatch({ type: SIGN_IN_REQUEST })

    const requestBody = {
        username: mail,
        password: password,
        client_id: 'js_client',
        client_secret: 'secret',
        grant_type: 'password'
    }
    const requestQuery = queryString.stringify(requestBody)

    return axios.post(AUTH_API, requestQuery, {
        method: 'POST',
        headers: {
            'content-type': 'application/x-www-form-urlencoded'
        },
        auth: {
            username: 'js_client',
            password: 'secret'
        }
    })
        .then(({ data: { access_token }}) => {

            const userId = jwt.decode(access_token).user_name
            const payload = { token: access_token, userId: userId }
            localStorage.setItem('token', access_token)
            localStorage.setItem('userId', userId)

            dispatch({
                type: SIGN_IN_SUCCESS,
                payload
            })

            return Promise.resolve(payload)

        })
        .catch(() => {
            const payload = new Error('Invalid login credentials!')
            dispatch({
                type: SIGN_IN_FAILURE,
                payload
            })
            return Promise.reject(payload)
        })
}

export const SIGN_OUT = '@SIGN_OUT'

export const signOut = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('userId')

    return {
        type: SIGN_IN_SUCCESS
    }
}

const REGISTER_API = 'http://localhost:8080/api/register'

export const SIGN_UP_REQUEST = '@SIGN_UP_REQUEST'
export const SIGN_UP_SUCCESS = '@SIGN_UP_SUCCESS'
export const SIGN_UP_FAILURE = '@SIGN_UP_FAILURE'

export const signUp = user => dispatch => {

    dispatch({ type: SIGN_UP_REQUEST })

    const {
        name,
        mail,
        password,
        confirmPassword,
        avatar
    } = user

    return axios.post(REGISTER_API, {
        username: name,
        email: mail,
        password,
        matchingPassword: confirmPassword,
        avatarUrl: avatar
    })
        .then(() => dispatch({
            type: SIGN_UP_SUCCESS
        }))
        .catch(error => {
            dispatch({
                type: SIGN_UP_FAILURE,
                payload: error
            })
            return Promise.reject(error)
        })

}
