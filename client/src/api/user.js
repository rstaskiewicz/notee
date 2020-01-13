
const signUp = api => user => {
    const retrived = JSON.parse(localStorage.getItem(user.mail))
    if (retrived)
        return Promise.reject(new Error('Email has already been taken.'))
    localStorage.setItem(user.mail, JSON.stringify(user))
    return Promise.resolve(user)
}

const signIn = api => user => {
    const retrived = JSON.parse(localStorage.getItem(user.mail))
    if (!retrived || (retrived.password !== user.password))
        return Promise.reject(new Error('Invalid login or password.'))
    return Promise.resolve(retrived)
}

export default api => ({
    signUp: signUp(api),
    signIn: signIn(api)
})
