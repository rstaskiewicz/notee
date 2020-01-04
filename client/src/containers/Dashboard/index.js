import React from 'react'

import Dashboard from '../../layout/Dashboard'

import Header from '../../components/Header'
import Card from '../../components/Card'

import NotebookList from '../../components/NotebookList'
import FriendsOnline from '../../components/FriendsOnline'
import Statistics from '../../components/Statistics'

const Notebook1 = {
    title: "Notebook1",
    notes: ["note 1", "note 2"]
}
const Notebook2 = {
    title: "Notebook2",
    notes: ["note 3", "note 4"]
}
const array = [Notebook1, Notebook2];

export default (

) => {

    return (
        <Dashboard>

            <Header />
            <Dashboard.Content>
                <Dashboard.Content.Segregator modifiers="dashboard-left-column">
                    <NotebookList Notebooks={array} />
                </Dashboard.Content.Segregator>
                <Dashboard.Content.Segregator modifiers="dashboard-center-column">
                    <Card />
                    <Card />
                    <Card />
                    <Card />
                </Dashboard.Content.Segregator>
                <Dashboard.Content.Segregator modifiers="dashboard-right-column">
                    <FriendsOnline friends={"asd", "dsa"} />
                    <Statistics />
                </Dashboard.Content.Segregator>
            </Dashboard.Content>
        </Dashboard>
    )

}
