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
    return api.singUp.register(user)
        .then(() => dispatch(userSignedUp(user)))
        .catch(error => {
            dispatch(signUpUserError(error))
            return Promise.reject(error)
        })
}
