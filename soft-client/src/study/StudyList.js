import React, {useEffect, useState} from "react";
import {NavLink, Link} from "react-router-dom";
import {Button, Table} from "react-bootstrap";
import moment from "moment";
import Layout from "../layout/Layout";
import * as StudyApi from "../api/StudyApi";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faCheckCircle, faCoffee, faPen, faTimesCircle} from '@fortawesome/free-solid-svg-icons'


const StudyActions =({study}) =>{
    return (
        <React.Fragment>
            <NavLink to={"/study/" + study.id}><FontAwesomeIcon icon={faPen} color="green" className={"edit"}/></NavLink>
            <FontAwesomeIcon icon={faTimesCircle} className={"ml-1 cross"}/>
        </React.Fragment>
    );
};


function StudyList(props) {
    const [studyPatientDtos, setStudyPatientDtos] = useState([]);

    useEffect(() => {
        StudyApi.findAll().then((response) => {
            console.log(JSON.stringify(response.content));
            setStudyPatientDtos(response.content);

        });

    }, []);

    function convertDate(date) {
        return moment(date).format("YYYY-MM-DD h:mm");
    }

    return(
        <Layout title={"Study List"} classname={"study-list"}>
            <PageControls/>
            <section>
                <Table striped className={""}>
                    <thead>
                        <td className={"d-none d-md-block"}>sl.</td>
                        <td>Person Code</td>
                        <td className={"w-1"}>Patient name</td>
                        <td>DOB</td>
                        <td>Study name</td>
                        <td className={"d-none d-md-block"}>Update date</td>
                        <td>Action</td>
                    </thead>
                    <tbody>
                    {studyPatientDtos && studyPatientDtos.map((item, index) =>
                        <tr>
                            <td className={"d-none d-md-block"}>{index + 1}</td>
                            <td>{item.patientCode}</td>
                            <td>{item.fullName}</td>
                            <td>{moment(item.dateOfBirth).format("YYYY-MM-DD")}</td>
                            <td>{item.name}</td>
                            <td className={"d-none d-md-block"}>{convertDate(item.updateDate)}</td>
                            <td>
                                <StudyActions study={item}/>
                            </td>
                        </tr>
                    )}
                    </tbody>
                </Table>
            </section>
        </Layout>
    );
}

const PageControls = () =>
    <Link to={"/study/create"} className={"btn btn-success"}>Create</Link>
;

export default StudyList;