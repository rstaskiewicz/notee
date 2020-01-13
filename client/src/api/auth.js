const login = (api, data) => {
    // api(`http://localhost:8081/oauth/token`, data)
}

export default api => ({
    login: login(api.post)
})
