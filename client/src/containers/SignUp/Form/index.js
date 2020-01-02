import React, { forwardRef } from 'react'
import { useFormik } from 'formik'
import * as Yup from 'yup'

import SignUp from '../../../layout/SignUp'
import { P, A } from '../../../elements'

import Input from '../../../components/Input'
import Checkbox from '../../../components/Checkbox'
import Headshot from '../../../components/Headshot'

export default forwardRef(({ onSubmit }, ref) => {

    const schema = Yup.object().shape({
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
            .oneOf([ Yup.ref('password'), null ], 'Passwords must match' )
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
            terms: ''
        },
        validationSchema: schema
    })

    return (
        <SignUp.Form
            ref={ref}
            onSubmit={formik.handleSubmit}
        >

            <SignUp.Headshot>
                <Headshot />
            </SignUp.Headshot>

            <SignUp.Input>
                <Input
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
                <Input
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
                <Input
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
                <Input
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
                    onChange={formik.onChange}
                    value={formik.values.terms}
                    // checked={formik.values.terms}
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
                <button type="submit">Submit</button>
            </SignUp.Supplementary>
        </SignUp.Form>
    )

})
