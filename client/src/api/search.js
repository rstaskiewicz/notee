const data = {
    notebooks: [
        {
            title: 'Pradopodobieństwo',
            author: 'Basia Kowalska',
            link: '/notebooks/1',
            createdAt: new Date()
        },
        {
            title: 'Prawdopodobieństwo w zarządzaniu organizacją',
            author: 'Basia Kowalska',
            link: '/notebooks/2',
            createdAt: new Date()
        },
        {
            title: 'Some other note',
            author: 'Basia Kowalska',
            createdAt: new Date()
        }

    ],
    notes: [
        {
            title: 'Pradopodobieństwo',
            author: 'Basia Kowalska',
            link: '/notebooks/1/note/3',
            createdAt: new Date()
        }
    ]
}

const byTitle = api => title =>
    new Promise(resolve => {
        setTimeout(500)
        return resolve(data)
    })

export default api => ({
    byTitle: byTitle(api.get)
})
