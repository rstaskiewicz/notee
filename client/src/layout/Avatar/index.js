import styled, { css } from 'styled-components'
import { applyStyleModifiers } from 'styled-components-modifiers'

import Image from './Image'
import Status from './Status'

const AVATAR_MODFIERS = {

    'card-avatar': () => css`
        max-width: 48px;
        max-height: 48px;
    `,

    'dashboard-statistics': () => css`
        margin-left: 24px;
        min-width: 48px;
        min-height: 48px;
    `,

}

const Avatar = styled.div`
    position: relative;
    max-width: 48px;
    max-height: 48px;
    display: flex;

    ${applyStyleModifiers(AVATAR_MODFIERS)}
`

Avatar.Image = Image
Avatar.Status = Status

export default Avatar
