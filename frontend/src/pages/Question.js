import Card from 'react-bootstrap/Card';
import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import {useNavigate} from 'react-router';
import './styles/Home.css';
import NavBar from '../components/NavBar';
import axios from 'axios';
import { URL } from '../config';
import Choice from './Choice';
const Question = () => {
    const navigate = useNavigate();
    var anxity = "";
    var stress = "";
    var ocd = "";
    var schizophrenia = "";
    var autism = "";
    var ptsd = "";
    var hyperActivity = "";

// private int siteGenRepId;,private int anxity;, private int hyperActivity;, private int schizophrenia;
// private int ocd;,private int autism;, private int stress;, private int ptsd;, private int parenoia;
    const generateReport =() =>{
        const body = {
            "siteGenRepId": 0,
            "anxity": anxity,
            "hyperActivity": hyperActivity,
            "schizophrenia": schizophrenia,
            "ocd": ocd,
            "autism": autism,
            "stress": stress,
            "ptsd": ptsd,
            "parenoia": 3
        }
        var id = sessionStorage['id']
        axios.post(`${URL}/question/${id}`,body).then((response)=>{
            const status = response.status
            if(status==200){
                navigate("/userSiteReport")
            }
        }).catch((error)=>{
            console.error(error);
        })
    }
    const question1 = (e) => {
        anxity = e.target.value;
    }
    const question2 = (e) => {
        stress = e.target.value;
    }
    const question3 = (e) => {
        ocd = e.target.value;
    }
    const question4 = (e) => {
        schizophrenia = e.target.value;
    }
    const question5 = (e) => {
        autism = e.target.value;
    }
    const question6 = (e) => {
        ptsd = e.target.value;
    }
    const question7 = (e) => {
        hyperActivity = e.target.value;
    }
    return ( <> <NavBar/> < div class = 'Wrapper' > <div class='container maind'>
        <div class='rowd'>
            <div class='col-md-8 col-sm-auto'>

                <header>
                    <h3>Please answer these questions</h3>
                </header>

                <div class="input-field">
                    <h6>Do you feel stressed?</h6>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question1"
                            id="inlineRadio1"
                            onClick={question2}
                            value="4"/>
                        <label class="form-check-label" for="inlineRadio1">Extremely</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question1"
                            id="inlineRadio2"
                            onClick={question2}
                            value="1"/>
                        <label class="form-check-label" for="inlineRadio2">Mildly</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question1"
                            id="inlineRadio3"
                            onClick={question2}
                            value="0"/>
                        <label class="form-check-label" for="inlineRadio3">Not at all</label>
                    </div>
                </div>
                <div class="input-field">
                    <h6>Do you feel anxious?</h6>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question2"
                            id="inlineRadio1"
                            onClick={question1}
                            value="4"/>
                        <label class="form-check-label" for="inlineRadio1">Extremely</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question2"
                            id="inlineRadio2"
                            onClick={question1}
                            value="1"/>
                        <label class="form-check-label" for="inlineRadio2">Mildly</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question2"
                            id="inlineRadio3"
                            onClick={question1}
                            value="0"/>
                        <label class="form-check-label" for="inlineRadio3">Not at all</label>
                    </div>
                </div>

                <div class="input-field">
                    <h6>Were you ever diagnosed with OCD?</h6>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question3"
                            id="inlineRadio2"
                            onClick={question3}
                            value="1"/>
                        <label class="form-check-label" for="inlineRadio2">yes</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question3"
                            id="inlineRadio3"
                            onClick={question3}
                            value="0"/>
                        <label class="form-check-label" for="inlineRadio3">No</label>
                    </div>
                </div>

                <div class="input-field">
                    <h6>How frequently do you face schizophrenic episode?</h6>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question4"
                            onClick={question4}
                            id="inlineRadio1"
                            value="4"/>
                        <label class="form-check-label" for="inlineRadio1">Frequently</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question4"
                            onClick={question4}
                            id="inlineRadio2"
                            value="1"/>
                        <label class="form-check-label" for="inlineRadio2">Sometimes</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question4"
                            onClick={question4}
                            id="inlineRadio3"
                            value="0"/>
                        <label class="form-check-label" for="inlineRadio3">None at all</label>
                    </div>
                </div>
                <div class="input-field">
                    <h6>Were you diagnosed with post traumatic stress disorder?</h6>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question5"
                            id="inlineRadio2"
                            onClick={question5}
                            value="1"/>
                        <label class="form-check-label" for="inlineRadio2">yes</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question5"
                            id="inlineRadio3"
                            onClick={question5}
                            value="0"/>
                        <label class="form-check-label" for="inlineRadio3">No</label>
                    </div>
                </div>
                <div class="input-field">
                    <h6>Are you autistic?</h6>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question6"
                            onClick={question6}
                            id="inlineRadio2"
                            value="1"/>
                        <label class="form-check-label" for="inlineRadio2">yes</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question6"
                            onClick={question6}
                            id="inlineRadio3"
                            value="0"/>
                        <label class="form-check-label" for="inlineRadio3">No</label>
                    </div>
                </div>
                <div class="input-field">
                    <h6>Rate your hiperactivity disorder.</h6>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question7"
                            onClick={question7}
                            id="inlineRadio1"
                            value="4"/>
                        <label class="form-check-label" for="inlineRadio1">Extremely</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question7"
                            onClick={question7}
                            id="inlineRadio2"
                            value="1"/>
                        <label class="form-check-label" for="inlineRadio2">Mildly</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="question7"
                            onClick={question7}
                            id="inlineRadio3"
                            value="0"/>
                        <label class="form-check-label" for="inlineRadio3">Not at all</label>
                    </div>
                    
                </div>
            </div>
            <div class="input-field">
                        <input type="submit" onClick={generateReport} class="login" value="Generate Report?" />
                   </div> 
        </div>
    </div> 
</div>
</>)

}
export default Question;