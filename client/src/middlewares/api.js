import callAPI from '../utils/callApi'

export default ( {dispatch, getState }) => next => action => {
    const { types, endpoint, payload = {}, requestParams, method } = action

    if (!types) {
        return next(action)
    }
    if (
        !Array.isArray(types)
        || types.length !== 3
        || !types.every(type => typeof type === 'string')
    ) {
        throw new Error('Expected an array of three string types')
    }
    if (typeof endpoint !== 'string') {
        throw new Error('Expected endpoint to be a string')
    }
    const { auth: { data: { token } } } = getState()
    if (!token) {
        throw new Error('Expected access token')
    }

    const [ requestType, successType, failureType ] = types

    dispatch({ ...payload, type: requestType })

    return callAPI(endpoint, requestParams, token, method, payload)
        .then(({ data }) => {
            dispatch({ payload: data, type: successType })
        })
        .catch(error => {
            dispatch({ ...payload, error, type: failureType })
        })
}
