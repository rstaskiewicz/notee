import React, { useEffect }  from 'react'
import { useSelector, useDispatch } from 'react-redux'

import Dashboard from '@notee/layout/Dashboard'

import Header from '@notee/components/Header'

import NotebookList from '@notee/components/NotebookList'
import FriendsOnline from '@notee/components/FriendsOnline'
import Statistics from '@notee/components/Statistics'
import { DashboardCard } from '@notee/components/Card'

import { loadUserNotebooks } from '@notee/actions/notebooks'

const notebook1 = {
    title: "Matematyka dyskretna",
    notes: [
        {
            title: "Prawdopodobieństwo",
            content: ""
        },
        {
            title: "Prawdopodobieństwo",
            content: ""
        },
        {
            title: "Funkcję różnowartościowe",
            content: ""
        }
    ]
}

const notebook2 = {
    title: "Statystyczna analiza danych",
    notes: [
        {
            title: "note 3",
            content: ""
        },
        {
            title: "note 3",
            content: ""
        }
    ]
}

const notebook3 = {
    title: "Podstawy programowania",
    notes: [
        {
            title: "note 1",
            content: ""
        },
        {
            title: "note 2",
            content: ""
        }
    ]
}
const array = [notebook1, notebook2, notebook3];

export default (

) => {

    const { userId } = useSelector(state => state.auth.data)
    const { data: notebooks } = useSelector(state => state.notebooks)
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(loadUserNotebooks(userId))
    }, [ dispatch ])

    return (
        <Dashboard>
            <Header />
            <Dashboard.Content>
                <Dashboard.Content.Segregator modifiers="dashboard-left-column">
                    <NotebookList notebooks={notebooks} />
                </Dashboard.Content.Segregator>
                <Dashboard.Content.Segregator modifiers="dashboard-center-column">
                    <DashboardCard />
                    <DashboardCard />
                    <DashboardCard />
                    <DashboardCard />
                </Dashboard.Content.Segregator>
                <Dashboard.Content.Segregator modifiers="dashboard-right-column">
                    <FriendsOnline friends={"asd", "dsa"} />
                    <Statistics createdNoteCount={2} savedNoteCount={4} userRegisteredCount={5} />
                </Dashboard.Content.Segregator>
            </Dashboard.Content>

        </Dashboard>
    )

}
