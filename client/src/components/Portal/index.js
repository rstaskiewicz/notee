import { createPortal } from 'react-dom'

import usePortal from '../../hooks/usePortal'

export default ({ id, children }) => {

    const target = usePortal(id)

    return createPortal(
        children,
        target
    )

}
