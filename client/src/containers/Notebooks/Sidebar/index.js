import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faPlus as add
} from '@fortawesome/free-solid-svg-icons'

import { Sidebar } from '@notee/layout/containers/Notebooks'

export default () => {
    return (
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

            <Sidebar.Button>
                Add notebook
                &nbsp;<FontAwesomeIcon icon={add} fixedWidth />
            </Sidebar.Button>
        </Sidebar>
    )
}
