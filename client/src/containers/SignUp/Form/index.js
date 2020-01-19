import React from 'react'
import { useFormik } from 'formik'
import * as Yup from 'yup'

import { SignUp } from '@notee/layout/containers/SignUp'
import { P, A, Button } from '@notee/elements'

import TextInput from '@notee/components/TextInput'
import Checkbox from '@notee/components/Checkbox'
import Headshot from '@notee/components/Headshot'

export default ({
    onRegister
}) => {

    const schema = Yup.object().shape({
        avatar: Yup.string(),
        name: Yup.string()
            .matches(/\w{3,}\s\w{3,}/, 'Full name is required')
            .trim()
            .required('Full name is required'),
        mail: Yup.string()
            .email('E-mail must be valid')
            .trim()
            .required('E-mail is required'),
        password: Yup.string()
            .min(8, 'Password must have length of at least 8 characters')
            .required('Password is required'),
        confirmPassword: Yup.string()
            .oneOf([ Yup.ref('password'), null ], 'Passwords must match')
            .required('Password confirm is required'),
        terms: Yup.bool()
            .required('You must accept terms of service')
    })

    const formik = useFormik({
        initialValues: {
            avatar: '',
            name: '',
            mail: '',
            password: '',
            confirmPassword: '',
            terms: false
        },
        validationSchema: schema,
        onSubmit: onRegister
    })

    return (
        <SignUp.Form
            onSubmit={formik.handleSubmit}
        >

            <SignUp.Headshot>
                <Headshot
                    name="avatar"
                    value={formik.values.avatar}
                    onFile={formik.handleChange}
                />
            </SignUp.Headshot>

            <SignUp.Input>
                <TextInput
                    name="name"
                    type="text"
                    placeholder="Full name"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.name}
                    error={formik.touched.name && formik.errors.name}
                />
            </SignUp.Input>

            <SignUp.Input>
                <TextInput
                    name="mail"
                    type="e-mail"
                    placeholder="E-mail"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.mail}
                    error={formik.touched.mail && formik.errors.mail}
                />
            </SignUp.Input>

            <SignUp.Input>
                <TextInput
                    name="password"
                    type="password"
                    placeholder="Password"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.password}
                    error={formik.touched.password && formik.errors.password}
                />
            </SignUp.Input>

            <SignUp.Input>
                <TextInput
                    name="confirmPassword"
                    type="password"
                    placeholder="Confirm password"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.confirmPassword}
                    error={formik.touched.confirmPassword && formik.errors.confirmPassword}
                />
            </SignUp.Input>

            <SignUp.Supplementary>
                <Checkbox
                    name="terms"
                    onCheck={formik.handleChange}
                    value={formik.values.terms}
                    checked={formik.values.terms}
                    error={formik.errors.terms}
                >
                    {() => (
                        <P>I accept the&nbsp;
                            <A modifiers="active" href="#">
                                Terms of Service and Privacy Policy
                            </A>
                        </P>
                    )}
                </Checkbox>
            </SignUp.Supplementary>

            <SignUp.ButtonGroup>

                <Button
                    type="submit"
                    modifiers={[ 'full-width', 'primary' ]}
                >
                    Register
                </Button>

            </SignUp.ButtonGroup>
        </SignUp.Form>
    )

}
