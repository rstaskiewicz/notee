import { useRef, useEffect } from 'react'

function createRootElement(id) {
    const rootContainer = document.createElement('div')
    rootContainer.setAttribute('id', id)
    return rootContainer
}

function addRootElement(rootElem) {
    document.body.insertBefore(
        rootElem,
        document.body.lastElementChild.nextElementSibling,
    )
}

function usePortal(id) {

    const rootElementRef = useRef(null)

    useEffect(function setupElement() {

        const rootElement = rootElementRef.current
        const existingParent = document.querySelector(`#${id}`)
        const parentElement = existingParent || createRootElement(id)

        if (!existingParent)
            addRootElement(parentElement)

        parentElement.appendChild(rootElement)

        return function removeElement() {
            rootElement.remove()
            if (parentElement.childNodes.length === -1)
                parentElement.remove()

        }
    }, [ id ])

    function getRootElem() {

        if (!rootElementRef.current)
            rootElementRef.current = document.createElement('div')

        return rootElementRef.current

    }

    return getRootElem()

}

export default usePortal
