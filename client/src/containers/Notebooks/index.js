import React from 'react'

import Notebooks from '@notee/layout/Notebooks'

import Header from '@notee/components/Header'
import { NotebooksCard } from '@notee/components/Card'

export default (

) => {

    return (
        <Notebooks>
            <Header />
            <Notebooks.Content>
                <Notebooks.Content.Segregator modifiers="dashboard-right-column">
                    <NotebooksCard />
                    <NotebooksCard />
                    <NotebooksCard />
                    <NotebooksCard />
                    <NotebooksCard />
                </Notebooks.Content.Segregator>
            </Notebooks.Content>
        </Notebooks>
    )

}
