const register = api => user => {
    return api('http://localhost:8080/api/register', {
        email: user.mail,
        username: user.name,
        passoword: user.passoword,
        matchingPassword: user.confirmPassword,
        avatarUrl: user.avatar
    })
}

export default api => ({
    register: register(api.post)
})
