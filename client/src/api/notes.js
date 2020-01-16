
// Static data for testing
const note = {
    id: 1,
    title: 'Lorem ipsum dolor sit amtet',
    notebook: {
        title: 'My notebook title'
    },
    labels: [
        { id: 1, name: 'Homework' },
        { id: 2, name: 'To do'},
        { id: 3, name: 'Math' },
        { id: 4, name: 'Discrete Mathematics' }
    ]
}

const notes = [
    note,
    note,
    note,
    note,
    note,
    note,
    note,
]

const findNotes = api => filters => {
    return Promise.resolve([ ...notes, ...notes, ...notes ])
}

export default api => ({
    findNotes: findNotes(api.get)
})
