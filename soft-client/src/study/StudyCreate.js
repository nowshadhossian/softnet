import React, {useEffect, useState} from "react";
import {Link, NavLink, useHistory} from "react-router-dom";
import {ErrorMessage, Field, Form, Formik, setIn} from "formik";
import * as Yup from "yup";
import * as StudyApi from "../api/StudyApi";
import Layout from "../layout/Layout";


const PageControls = () =>
    <NavLink to={"/study/list"} className={"btn btn-primary"}>List</NavLink>
;

function StudyCreate(props) {
    let history = useHistory();
    const [initialValue, setInitialValue] = useState({});
    useEffect(() => {
        StudyApi.findById(props.match.params.id).then((response) => {
            setInitialValue(response);
        });

    }, {});

    const saveStudy = (study) => {
        StudyApi.save(study).then((response) => {
            history.push("/study/list");
        });
    };

    return(
        <Layout title={"Study Create"} classname={"study-create shadow"}>
            <PageControls/>

            <section>
                    <Formik
                        enableReinitialize
                        initialValues={initialValue}
                        validationSchema={Yup.object({
                            name: Yup.string()
                                .required("Please complete this field"),
                        })}
                        onSubmit={(values, {setSubmitting, setFieldError}) => {
                            setTimeout(() => {
                                saveStudy(values);
                                setSubmitting(false);
                            }, 400);
                        }}
                    >
                        <Form>
                            <div>
                                <div className={"form-group"}>
                                    <Field
                                        name="id"
                                        type="hidden"
                                        id="id"
                                        className="form-control"
                                    />
                                    <label className="" htmlFor="name">Name</label>
                                    <div className="input-group">
                                        <Field
                                            name="name"
                                            type="text"
                                            id="name"
                                            className="form-control" placeholder="Enter Study Name"
                                        />
                                        <ErrorMessage className={"alert alert-danger"} name="name" component="div"/>
                                    </div>
                                </div>
                                <div className={"form-group"}>
                                    <label className="" htmlFor="name">Description</label>
                                    <div className="input-group">
                                        <Field
                                            name="description"
                                            type="text"
                                            id="ip"
                                            className="form-control" placeholder="Enter description"
                                        />
                                    </div>
                                </div>
                                <div className={"form-group"}>
                                    <label className="" htmlFor="serialNo">Patient First name</label>
                                    <div className="input-group">
                                        <Field
                                            name="firstName"
                                            type="text"
                                            id="ip"
                                            className="form-control" placeholder="Enter first name"
                                        />
                                    </div>
                                </div>
                                <div className={"form-group"}>
                                    <label className="" htmlFor="serialNo">Patient Last name</label>
                                    <div className="input-group">
                                        <Field
                                            name="lastName"
                                            type="text"
                                            id="ip"
                                            className="form-control" placeholder="Enter last name"
                                        />
                                    </div>
                                </div>
                                <div className={"form-group"}>
                                    <label className="" htmlFor="serialNo">Patient DOB</label>
                                    <div className="input-group">
                                        <Field
                                            name="dateOfBirth"
                                            type="date"
                                            id="ip"
                                            className="form-control" placeholder="Enter date of birth"
                                        />
                                    </div>
                                </div>
                            </div>


                            <div className="text-right mt-2">
                                <button type="submit" className="btn btn-success">
                                    Save
                                </button>
                            </div>

                        </Form>
                    </Formik>

            </section>
        </Layout>
    );
}



export default StudyCreate;