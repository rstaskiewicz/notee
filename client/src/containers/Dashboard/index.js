import React from 'react'

import Dashboard from '../../layout/Dashboard'

import Header from '../../components/Header'
import Card from '../../components/Card'

import NotebookList from '../../components/NotebookList'
import FriendsOnline from '../../components/FriendsOnline'
import Statistics from '../../components/Statistics'

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

    return (
        <Dashboard>

            <Header />
            <Dashboard.Content>
                <Dashboard.Content.Segregator modifiers="dashboard-left-column">
                    <NotebookList notebooks={array} />
                </Dashboard.Content.Segregator>
                <Dashboard.Content.Segregator modifiers="dashboard-center-column">
                    <Card />
                    <Card />
                    <Card />
                    <Card />
                </Dashboard.Content.Segregator>
                <Dashboard.Content.Segregator modifiers="dashboard-right-column">
                    <FriendsOnline friends={"asd", "dsa"} />
                    <Statistics createdNoteCount={2} savedNoteCount={4} userRegisteredCount={5} />
                </Dashboard.Content.Segregator>
            </Dashboard.Content>
        </Dashboard>
    )

}
