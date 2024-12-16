import React, {useState} from "react";
import { PiChatDotsLight } from "react-icons/pi";
import { RxCrossCircled } from "react-icons/rx";

const chatBox = ({userName, medicalHistory}) =>{
    const [isOpen, setIsOpen] = useState(false); //this will maintain check of visibility
    const [message, setMessage] = useState([]); //This will maintain temporary data of chat, during a session
    const [input, setInput] = useState(""); //This will maintain the input of chatbox

    //toggle checkbox visibility
    function toggleChat(){
        setIsOpen(!isOpen);
    };

    return (<>
        //floating chat icon
        <div className="chat-icon" onClick={toggleChat}>
            <PiChatDotsLight />
        </div>

        //now we need to check weather chatBox icon was clicked or not
        //if open then we need to implement the ui
        {isOpen && (
            <div className = "chatBox">
                //header
                <div className="chatBox-header">
                    <h3>Any questions? {userName}</h3> //here we are using props
                    <button onClick={toggleChat}><RxCrossCircled /></button> //using tbis we are toggling the chat box
                </div>

                //message section
                <div className="chatBox-messages">
                    {message.map((message, index) => (
                        <div key={index} className={`chat-message ${message.sender == "user"?"user-message":"ai-message"}`}>
                            {message.text}
                        </div>
                    ))};
                </div>

                //input section
                <div className="chatBox-input">
                    
                    <input type="text" placeholder="Type your query here..." onChange={event=>setInput(event.target.value)} 
                    onKeyDown={(event=>event.key == "Enter" && sendMessage())}></input>
                </div>
            </div>
        )};

    </>);
};

export default chatBox;

//difference between export and export default
//export will allow multiple functions,variables or classes from a module(we have import using the exact name and {} )
//A module can only have single export default(we can import with any name)(useful for exporting single value)