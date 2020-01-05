import React from 'react'

import Card from '@notee/layout/Card'

import Header from './Header'
import Content from './Content'
import Footer from './Footer'

const user = {
    fullname: 'Joanna Kowalska'
}

export default () => {

    return (
        <Card>
            <Header user={user} date="4 min ago" />
            <Content>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque ut vehicula arcu, nec dictum felis. Etiam a est dapibus, mollis est convallis, laoreet orci. Praesent mollis, augue quis molestie pulvinar, ante tortor facilisis metus, et placerat justo quam a nulla. Suspendisse feugiat tortor a quam imperdiet vestibulum. Maecenas commodo consectetur sapien, vel hendrerit sem gravida et. Sed tincidunt aliquam ex, id mollis dolor ullamcorper at. Vivamus dapibus enim iaculis urna eleifend, eget blandit arcu scelerisque. Nunc id ipsum non odio gravida fermentum…
            </Content>
            <Footer />
        </Card>
    )
}
