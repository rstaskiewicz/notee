import React, { useEffect }  from 'react'
import { useSelector, useDispatch } from 'react-redux'

import Dashboard from '@notee/layout/Dashboard'

import Header from '@notee/components/Header'

import NotebookList from '@notee/components/NotebookList'
import FriendsOnline from '@notee/components/FriendsOnline'
import Statistics from '@notee/components/Statistics'
import { DashboardCard } from '@notee/components/Card'

import { loadUserNotebooks } from '@notee/actions/notebooks'
import { loadLastUserNotes } from '@notee/actions/notes'

export default (

) => {

    const { userId } = useSelector(state => state.auth.data)
    const { notebooks } = useSelector(state => state)
    const { notes } = useSelector(state => state)
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(loadUserNotebooks(userId))
        dispatch(loadLastUserNotes(userId))
    }, [ dispatch ])

    return (
        <Dashboard>
            <Header />
            <Dashboard.Content>
                <Dashboard.Content.Segregator modifiers="dashboard-left-column">
                    <NotebookList notebooks={notebooks.data} />
                </Dashboard.Content.Segregator>
                <Dashboard.Content.Segregator modifiers="dashboard-center-column">
                    {!notes.isLoading && notes.data.map(({ noteName, createdBy, modifiedAt, noteContent }) => (
                        <DashboardCard
                            noteName={noteName}
                            createdBy={createdBy}
                            modifiedAt={modifiedAt}
                            noteContent={noteContent}/> ))}
                </Dashboard.Content.Segregator>
                <Dashboard.Content.Segregator modifiers="dashboard-right-column">
                    <FriendsOnline friends={"asd", "dsa"} />
                    <Statistics createdNoteCount={2} savedNoteCount={4} userRegisteredCount={5} />
                </Dashboard.Content.Segregator>
            </Dashboard.Content>

        </Dashboard>
    )

}
