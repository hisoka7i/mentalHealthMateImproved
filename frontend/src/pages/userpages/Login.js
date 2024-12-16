import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import '../styles/Login.css';
import {useNavigate} from 'react-router';
import {AiOutlineUser} from 'react-icons/ai';
import NavBar from '../../components/NavBar';
import {URL} from '../../config'
import axios from 'axios';
import { Alert } from 'react-bootstrap';

// import loginpic from "../images/login.svg"

const Login = () =>{
    const navigate = useNavigate();
    var username="";
    var password="";
    function handler1(e){
        username=e.target.value;
    }
    function handler2(e){
        password=e.target.value;
    }
    function loginFunction(){
        const body={
           "uname":username,
           "password":password
        }
        //console.log(body);
        axios.post(`${URL}/user/login`, body)
        .then((response) => {
            console.log("Successful login");
            const result = response.data; // Backend response body
            const status = response.status;
    
            if (status === 200) {
                // alert(result + " Login successful ")
                console.log(result)
                // Store data in sessionStorage
                sessionStorage['id'] = result.id;
                sessionStorage['uname'] = result.userName;
                sessionStorage['name'] = result.name; // Fixed
                sessionStorage['email'] = result.email;
                sessionStorage['loginStatus'] = 2;
                sessionStorage['token'] = result.jwtToken; // Assuming token is sent
    
                // alert('Login successful!');
                navigate('/') // Uncomment if navigation is required
            }
        })
        .catch((error) => {
            console.error(error);
            alert('Login failed!');
        });
    
    }
    return (
        <>
        <NavBar />
        <div class="wrapper">
    <div class="container main">
        <div class="row">
            <div class="col-md-6 side-image">        
            </div>
            <div class="col-md-6 right">  
                <div class="input-box">
                   <header><h2>Login <AiOutlineUser /></h2></header>
                   <div class="input-field">
                        <input type="text" class="input" onBlur={handler1} id="email" required="" autocomplete="off" />
                        <label for="email">Username</label> 
                    </div> 
                   <div class="input-field">
                        <input type="password" class="input" onBlur={handler2} id="pass" required="" />
                        <label for="pass">Password</label>
                    </div> 
                   <div class="input-field">
                        <input type="submit" onClick={loginFunction} class="login" value="Login" />
                   </div> 
                   <div class="signin">
                    <span>For Registeration: <a onClick={()=>{navigate("/userRegisteration")}} href="">Register here</a></span>
                   </div>
                </div>  
            </div>
        </div>
    </div>
</div>
        </>
    )
}
export default Login;