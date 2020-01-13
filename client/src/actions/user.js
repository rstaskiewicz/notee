import api from '@notee/api'

export const SIGN_UP_USER = 'SIGN_UP_USER'
export const SIGN_UP_USER_SUCCESS = 'SIGN_UP_USER_SUCCESS'
export const SIGN_UP_USER_FAILD = 'SIGN_UP_USER_FAILD'

const signingUpUser = () => ({
    type: SIGN_UP_USER
})

const userSignedUp = payload => ({
    type: SIGN_UP_USER_SUCCESS,
    payload
})

const signUpUserError = payload => ({
    type: SIGN_UP_USER_FAILD,
    payload
})

export const signUpUser = user => dispatch => {
    dispatch(signingUpUser())
    return api.user.signUp(user)
        .then(({ name, mail, avatar }) => {
            const user = { id: 1, name, mail, avatar }
            dispatch(userSignedUp(user))
            return user
        })
        .catch(error => {
            dispatch(signUpUserError(error))
            return Promise.reject(error)
        })
}

export const SIGN_IN_USER = 'SIGN_IN_USER'
export const SIGN_IN_USER_SUCCESS = 'SIGN_IN_USER_SUCCESS'
export const SIGN_IN_USER_FAILD = 'SIGN_IN_USER_FAILD'

const signingInUser = () => ({
    type: SIGN_IN_USER
})

const userSignedIn = payload => ({
    type: SIGN_IN_USER_SUCCESS,
    payload
})

const signInUserError = payload => ({
    type: SIGN_IN_USER_FAILD,
    payload
})

export const signInUser = user => dispatch => {
    dispatch(signingInUser())
    return api.user.signIn(user)
        .then(({ name, mail, avatar }) => {
            const user = { id: 1, name, mail, avatar }
            dispatch(userSignedIn(user))
            return user
        })
        .catch(error => {
            dispatch(signInUserError(error))
            return Promise.reject(error)
        })
}
