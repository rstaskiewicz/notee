import React from 'react'

import Header from '../../../layout/Card/Header'
import HeaderLeft from '../../../layout/Card/HeaderLeft'
import HeaderRight from '../../../layout/Card/HeaderRight'
import Avatar from '../../Avatar'
import Label from '../../../layout/Card/Label'
import { A } from '../../../elements'
export default ({
    user,
    title = '',
}) => {

    return (
        <Header>
            <HeaderLeft>
                <Avatar modifiers="card-avatar" />
            </HeaderLeft>
            <HeaderRight>
                <A modifiers="card-header">USER_NAME</A>
                <Label modifiers="card-header">created new note titled</Label>
                <A modifiers="card-header">NOTE_TITLE</A>
            </HeaderRight>
        </Header>
    )

}
