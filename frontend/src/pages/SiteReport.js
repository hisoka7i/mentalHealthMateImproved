import React, {useState, useEffect} from 'react';
import {PieChart, Pie, Cell} from 'recharts';
import 'bootstrap/dist/css/bootstrap.min.css';
import './styles/Tables.css';
import {URL} from '../config';
import axios from 'axios';
import NavBar from '../components/NavBar';

const SiteReport = () => {
    const [patient,
        setPatient] = useState([]);
    const [chartData,
        setCharData] = useState([]); // State for chart data
    //   const [medHis,setMedHis] = useState([]);

    function PatientData() {
        var id = sessionStorage['id']
        return axios
            .get(`${URL}/question/sitereport/${id}`)
            .then((response) => response.data);
    }
    var COLORS;

    function calculateChartData(patientData) {
        const anxity = patientData.anxity * 10;
        const autism = patientData.autism * 12;
        const hyperActivity = patientData.hyperActivity * 11;
        const ocd = patientData.ocd * 13;
        const parenoia = patientData.parenoia * 19;
        const ptsd = patientData.ptsd * 5;

        const data = [
            {
                name: 'anxity',
                students: anxity,
            }, {
                name: 'autism',
                students: autism,
            }, {
                name: 'ocd',
                students: ocd
            }, {
                name: 'hyperActivity',
                students: hyperActivity
            }, {
                name: 'parenoia',
                students: parenoia
            }, {
                name: 'ptsd',
                students: ptsd
            }
        ];
        COLORS = ['#FF5733', '#5EAD2A', '#FFC300', '#0074D9', '#D67BFF']
        setCharData(data);
    }

    function printSiteHistory() {
        return chartData.map((answer, i) => {
            return (
                <p key={i}>Based on your answers we believe that your {answer.name}
                    is {((answer.students) % 11) * 10}%</p>
            )
        })
    }

    useEffect(() => {
        PatientData().then((responseData) => {
            setPatient(responseData);
            calculateChartData(responseData);
        });
    }, []);

    return ( <> <NavBar/> < div className = "wrapper" >
       <div className="container main">
        <div className="rowd">
            <div className="col col-md-auto col-sm-auto">
                <h3>Your Report based on your answers</h3>
                <PieChart width={600} height={450}>
                    <Pie data={chartData} dataKey="students" nameKey="name" label outerRadius={175}/>
                </PieChart>
                <div
                    style={{
                    display: "inline"
                }}
                    class="input-field">
                    <p
                        style={{
                        display: "inline"
                    }}
                        type="text"
                        id="text"
                        class="input">Patient last answers to the questions were:</p>
                    {printSiteHistory()}
                </div>
            </div>
        </div>
    </div> 
    </div>
    </>);
}

export default SiteReport;