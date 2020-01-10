const initialState = {
    name: ''
}

export default (state = initialState, { type, payload }) => {
    switch (type) {
        case 'NOTE_TITLE':
            return payload;
        default:
            return state;
    }

}

