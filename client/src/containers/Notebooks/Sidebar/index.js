import React, { useState } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faPlus as add,
    faTimes as remove
} from '@fortawesome/free-solid-svg-icons'

import { Sidebar } from '@notee/layout/containers/Notebooks'
import { Modal } from '@notee/layout/components/Modal'
import { Overlay, Button } from '@notee/elements'

import Portal from '@notee/components/Portal'

export default ({
    onCreateNewNotebook = () => null
}) => {

    const [ isModalOpen, setIsModalOpen ] = useState(false)
    const [ newNotebook, setNewNotebook ] = useState()

    const handleCreateNewNotebook = () => {
        onCreateNewNotebook(newNotebook)
        setIsModalOpen(false)
    }

    return (
        <>
            <Sidebar>

                <Sidebar.Navigation>
                    <Sidebar.List>

                        <Sidebar.Item>
                            <Sidebar.Link modifiers="active">
                                Notebook #1
                        </Sidebar.Link>
                        </Sidebar.Item>

                        <Sidebar.Item>
                            <Sidebar.Link>
                                Notebook #2
                        </Sidebar.Link>
                        </Sidebar.Item>


                        <Sidebar.Item>
                            <Sidebar.Link>
                                Notebook #3
                        </Sidebar.Link>
                        </Sidebar.Item>


                        <Sidebar.Item>
                            <Sidebar.Link>
                                Notebook #4
                        </Sidebar.Link>
                        </Sidebar.Item>

                    </Sidebar.List>
                </Sidebar.Navigation>

                <Sidebar.Button
                    onClick={() => setIsModalOpen(true)}
                >
                    Add notebook
                &nbsp;<FontAwesomeIcon icon={add} fixedWidth />
                </Sidebar.Button>
            </Sidebar>

            {isModalOpen && (
                <Portal id="add-notebook">
                    <Modal>
                        <Modal.Action>
                            <Button.Icon onClick={() => setIsModalOpen(false)}>
                                <FontAwesomeIcon icon={remove} fixedWidth />
                            </Button.Icon>
                        </Modal.Action>
                        <Modal.Header>
                            <Modal.Title>Create new notebook</Modal.Title>
                            <Modal.Subtitle>
                                Type in title for your new notebook
                                {' 😊'}
                            </Modal.Subtitle>
                        </Modal.Header>

                        <Modal.Content modifiers={[ 'algin-center', 'justify-center' ]}>
                            <Modal.Input
                                type="text"
                                placeholder="Notebook title..."
                                value={newNotebook}
                                onChange={e => setNewNotebook(e.target.value)}
                            />
                        </Modal.Content>

                        <Modal.Footer>
                            <Modal.ButtonGroup>
                                <Button
                                    modifiers={[ 'text' ]}
                                    onClick={() => setIsModalOpen(false)}
                                >
                                    Cancel
                                </Button>
                                <Button
                                    modifiers={[ 'text-primary' ]}
                                    onClick={handleCreateNewNotebook}
                                >
                                    Save
                                </Button>
                            </Modal.ButtonGroup>
                        </Modal.Footer>
                    </Modal>
                    <Overlay onClick={() => setIsModalOpen(false)} />
                </Portal>
            )}
        </>
    )
}
