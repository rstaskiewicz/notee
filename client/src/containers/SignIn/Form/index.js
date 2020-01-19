import React from 'react'
import { useFormik } from 'formik'
import * as Yup from 'yup'

import { SignIn } from '@notee/layout/containers/SignIn'

import TextInput from '@notee/components/TextInput'
import Checkbox from '@notee/components/Checkbox'
import { A, P, Button } from '@notee/elements'

export default ({
    onLogin,
    onSignUp
}) => {

    const schema = Yup.object().shape({
        mail: Yup.string()
            .email('E-mail must be valid')
            .trim()
            .required('E-mail is required'),
        password: Yup.string()
            .min(8, 'Password must have length of at least 8 characters')
            .required('Password is required'),
        remberMe: Yup.bool()
    })

    const formik = useFormik({
        initialValues: {
            mail: '',
            password: '',
            remberMe: false
        },
        validationSchema: schema,
        onSubmit: onLogin
    })

    return (
        <SignIn.Form
            onSubmit={formik.handleSubmit}
        >

            <SignIn.Input>
                <TextInput
                    name="mail"
                    type="mail"
                    placeholder="E-mail"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.mail}
                    error={formik.touched.mail && formik.errors.mail}
                />
            </SignIn.Input>

            <SignIn.Input>
                <TextInput
                    name="password"
                    type="password"
                    placeholder="Password"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.password}
                    error={formik.touched.password && formik.errors.password}
                />
            </SignIn.Input>

            <SignIn.Supplementary>

                <Checkbox
                    name="remember-me"
                >
                    {({ isActive, isMouseOver }) => (
                        <P modifiers={[ (isActive || isMouseOver) && 'primary' ]}>
                            Remember me
                        </P>
                    )}
                </Checkbox>

                <A href="#">Forgot password</A>

            </SignIn.Supplementary>

            <SignIn.ButtonGroup>

                <Button
                    type="submit"
                    modifiers="primary"
                >
                    Login
                </Button>
                <Button
                    onClick={onSignUp}
                >
                    Sign Up
                </Button>

            </SignIn.ButtonGroup>
    </SignIn.Form>

    )

}
