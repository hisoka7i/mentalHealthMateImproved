import Card from 'react-bootstrap/Card';
import 'bootstrap/dist/css/bootstrap.min.css';
import React, { useEffect } from 'react';
import { useNavigate } from 'react-router';
import '../styles/Home.css';
import { BsBookmarkHeart } from 'react-icons/bs';
import { AiOutlineMedicineBox } from 'react-icons/ai';
import { TbUserQuestion } from 'react-icons/tb'
import { BiHomeHeart } from 'react-icons/bi'
import NavBar from '../../components/NavBar';
import { URL } from '../../config';
import axios from 'axios';
import ChatBox from '../AiAssistance/chatBox';
import { useSelector } from 'react-redux';
import { Alert } from 'react-bootstrap';

const UserHome = () => {
    const navigate = useNavigate();
    var allergy = ""
    const id = sessionStorage['id']
    // const token = sessionStorage['token']
    const user = useSelector(state => state.user);
    const token = user.token;
    function updateAllergy() {
        alert(token);
        axios.patch(`${URL}/user/updateAllergies/${id}/${allergy}`,
            {}, // Empty request body, since PATCH requests usually need one
            {
                headers: {
                    Authorization: `Bearer ${token}`, // Replace 'jwtToken' with the key where you stored the token
                },
            }
        ).then((response) => {
            console.log("Home page rendered")
            const status = response.status
            if (status == 200) {
                navigate('/')
                // alert("Done")
            }
            // alert(status);
        }).catch((error) => {
            alert(error)
        })
    }
    function handler(e) {
        allergy = e.target.value;
    }

    const name = sessionStorage['uname']

    return (
        <>
            <NavBar />
            <div className="wrapper">
                <div className="container main">
                    <center><h1>Welcome {name}<BiHomeHeart /></h1></center>

                    <div className="row">
                        <div className='col col-md-auto'>
                            <div class="card" style={{ width: "18rem;" }}>
                                <div class="card-body">
                                    <h5 class="card-title">Book Appointment</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">Make direct Appointment</h6>
                                    <p class="card-text">In case if you do not want to answer questions and directly do the doctor appointment. We advise you to answer the questions.</p>
                                    <div class="card-title">
                                        <a onClick={() => navigate("/bookAppointment")} href="">Book <BsBookmarkHeart /></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className='col col-md-auto'>
                            <div class="card" style={{ width: "18rem;" }}>
                                <div class="card-body">
                                    <h5 class="card-title">Self Diagnosis</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">This is a must visit</h6>
                                    <p class="card-text">Just in case you developed a new allergy or you forgot to add any allergy</p>
                                    <div class="card-title">
                                        <a onClick={() => navigate("/userQuestions")} href="">Attempt <TbUserQuestion /></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className='row justify-content-center'>
                        <div className='col col-md-auto'>
                            <div class="card" style={{ width: "18rem;" }}>
                                <div class="card-body">
                                    <h5 class="card-title">Update Allergies</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">Change allergy</h6>
                                    <div class="input-field">
                                        <input type="text" onBlur={handler} placeholder='Put here' class="input" id="diagnoses" required="" autocomplete="off" />
                                    </div>
                                    <div class="card-title">
                                        <a onClick={() => updateAllergy()} href="">Update <AiOutlineMedicineBox /></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className='col col-md-auto'>
                            <div class="card" style={{ width: "18rem;" }}>
                                <div class="card-body">
                                    <h5 class="card-title">Medical History</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">Visit to Add Medical History</h6>
                                    <p class="card-text">Fill this form to make a medical History card, you can keep track of your diagnosis and medications. This card will also have your allergies and family disease history. This can be used globally, and this will help doctor to make best decision for you.</p>
                                    <div class="card-title">
                                        <a onClick={() => navigate("/addMedicalHistory")} href="">Add <AiOutlineMedicineBox /></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <ChatBox userName={name} medicalHistory={"Nothing"}/>
        </>
    )
}
export default UserHome;