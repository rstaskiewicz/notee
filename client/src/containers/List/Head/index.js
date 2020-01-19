import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
    faSort as sort
} from '@fortawesome/free-solid-svg-icons'

import { Head } from '@notee/layout/containers/List'

export default () => {
    return (
        <Head>

            <Head.Row>

                <Head.Item />

                <Head.Item>
                    Name
                    <FontAwesomeIcon icon={sort} fixedWidth />
                </Head.Item>

                <Head.Item>
                    Title
                    <FontAwesomeIcon icon={sort} fixedWidth />
                </Head.Item>

                <Head.Item>
                    Notebook
                    <FontAwesomeIcon icon={sort} fixedWidth />
                </Head.Item>

                <Head.Item>
                    Date
                    <FontAwesomeIcon icon={sort} fixedWidth />
                </Head.Item>

                <Head.Item />

            </Head.Row>

        </Head>
    )
}
