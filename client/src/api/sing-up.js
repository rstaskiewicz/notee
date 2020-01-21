const register = api => user => {
    console.log('register')
    return api('http://localhost:8080/api/register', {
        ...user,
        avatarUrl: user.avatar
    })
}

export default api => ({
    register: register(api.post)
})
