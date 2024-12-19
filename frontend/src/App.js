import React from "react";
import {BrowserRouter, Route, Routes, Link} from 'react-router-dom'
import './App.css';
import ErrorPage from './pages/ErrorPage';

import Login from './pages/userpages/Login';
import Register from "./pages//userpages/Register";
import MedicalHistory from "./pages/MedicalHistory";
import { store, persistor } from './pages/store/ReduxStore';  // Import the store and persistor
import { PersistGate } from 'redux-persist/integration/react';
import Choice from "./pages/userpages/Choice";
import DoctorHome from "./pages/doctorpages/DoctorHome";
import DoctorLogin from "./pages/doctorpages/DoctorLogin"
import 'bootstrap/dist/css/bootstrap.min.css';
import DoctorRegistration from './pages/doctorpages/DoctorRegistration'
import PaymentHistory from "./pages/PaymentHistory";
import FreeSlot from "./pages/FreeSlot";
import DoctorAppointment from "./pages/doctorpages/DoctorAppointment";
import PatientAppointment from "./pages/userpages/PatientAppointment";
import MedicalHistoryCard from "./pages/MedicalHistoryCard"
import BookAppointment from "./pages/doctorpages/BookAppointment";
import UserHome from "./pages/userpages/UserHome"
import Question from "./pages/userpages/Question";
import SiteReport from "./pages/SiteReport";
import { Provider } from "react-redux";

const AuthorizeDoctor=()=>{
  const loginStatus = sessionStorage['loginStatus'];
  return loginStatus == '1'?<DoctorRegistration />:<DoctorLogin />
}
const AuthorizePayments=()=>{
  const loginStatus = sessionStorage['loginStatus'];
  if(loginStatus=='1'|| loginStatus=='2'){
    return <PaymentHistory />
   }
   else{
     return <Login />
   }
}
const AuthorizeAppointments=()=>{
  const loginStatus = sessionStorage['loginStatus'];
  if(loginStatus=='1'){
    return <DoctorAppointment />
  }else if(loginStatus=='2'){

  }else{
    return <Login />
  }
}

const AuthorizeQuestion =()=>{
  const loginStatus = sessionStorage['loginStatus'];
  console.log(loginStatus);
  if(loginStatus=='2'){
    return <Question />
  }
  else{
    return <Login />
  }
}

const AuthorizeFreeSlot=()=>{
  const loginStatus = sessionStorage['loginStatus'];
  if(loginStatus=='1'){
   return <FreeSlot />
  }
  else{
    return <Login />
  } 
}
const AuthorizeSiteReport=()=>{
  const loginStatus = sessionStorage['loginStatus'];
  if(loginStatus=='2'){
    return <SiteReport />
  }else{
    return <Login />
  }
}
const AuthorizePatient=()=>{
  const loginStatus = sessionStorage['loginStatus']
  if(loginStatus=='1'){
    return <PatientAppointment />
  }
  else{
    <Login />
  }
}
const AuthorizeBookAppointment=()=>{
  const loginStatus = sessionStorage['loginStatus']
  if(loginStatus=='2'){
    return <BookAppointment />
  }
}
const AuthorizeMedicalHistory=()=>{
  const loginStatus = sessionStorage['loginStatus']
  if(loginStatus=='2'){
    return <MedicalHistory />
  }
}
const Authorize=()=>{
  const loginStatus = sessionStorage['loginStatus'];
  if(loginStatus=='1'){
   return <DoctorHome />
  }
  else if(loginStatus=='2'){
    return <UserHome />
  }
  else{
    return <Login />
  }
}
const App = () => {
 
  return (
    <>
    <Provider store={store} >
      <PersistGate loading={<div>Loading...</div>} persistor={persistor}>
    <BrowserRouter>
    <Routes>
      {/* <Route path="/" element={} /> */}
      {/* <Route path="/userRegister" element={<Register /> } /> */}
      <Route path="/userLogin" element={<Login></Login>} />
      <Route path="/doctorRegisteration" element={<DoctorRegistration />} />
      <Route path="/" element={<Authorize />} />
      <Route path="/bookAppointment" element={<AuthorizeBookAppointment />} />
      <Route path="/appointments" element={<AuthorizeAppointments />} />
      <Route path="/addMedicalHistory" element={<AuthorizeMedicalHistory />} />
      <Route path="/doctorLogin" element={<DoctorLogin />} />
      <Route path="/userRegisteration" element={<Register />} />
      <Route path="/payments" element={<AuthorizePayments />} />
      <Route path="/freeSlot" element={<AuthorizeFreeSlot />} />
      <Route path="/patient" element={<AuthorizePatient />}/>  
      <Route path="/error" element={<ErrorPage/>} /> 
      <Route path="/userSiteReport" element={<AuthorizeSiteReport />} />
      <Route path="/userQuestions" element={<AuthorizeQuestion />} />
      {/* <Route path="/medicalhistory" element={<MedicalHistory/>} />  */}

    </Routes>
    </BrowserRouter>
    </PersistGate>
    </Provider>
    </>
  );
};

export default App;
