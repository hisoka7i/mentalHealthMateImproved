import React, { useState } from "react";
import { PiChatDotsLight } from "react-icons/pi";
import { RxCrossCircled } from "react-icons/rx";
import "../styles/ChatBox.css";
import axios from "axios";
import { URL } from "../../config";

const ChatBox = ({
  userName,
  medicalHistory,
  themeColor = "#4477CE",
  sendButtonText = "Send",
}) => {
  const [isOpen, setIsOpen] = useState(false); // Check visibility
  const [messages, setMessages] = useState([]); // Temporary chat data
  const [input, setInput] = useState(""); // Input value for the chatbox

  // Toggle chatbox visibility
  function toggleChat() {
    setIsOpen(!isOpen);
  }

  // Handle sending a message
  const sendMessage = () => {
    if (input.trim() === "") return;

    const token = sessionStorage["token"];

    // Add user's message to the chat
    setMessages([...messages, { sender: "user", text: input }]);
    setInput(""); // Clear input field

    const body = {
      "question": input
    };
    //here we need to make the axios call.
    const result = axios
      .post(`${URL}/chatbot/ask`, body, {
        headers: {
          Authorization: `Bearer ${token}`, // Replace 'jwtToken' with the key where you stored the token
        },
      })
      .then((response) => {
        if (response.status == 200) {
          setMessages((prevMessages) => [...prevMessages, { sender: "ai", text: response.data }]);
          // alert(response.data); we are getting the response
        }
      });

    // Simulate AI response (use medical history here)
    // setTimeout(() => {
    //   const aiResponse =
    //     input.toLowerCase().includes("history") && medicalHistory.length
    //       ? `Your medical history: ${medicalHistory.join(", ")}`
    //       : `Hello ${userName}, how can I assist you?`;

    //   setMessages((prevMessages) => [...prevMessages, { sender: "ai", text: aiResponse }]);
    // }, 1000);
  };

  return (
    <>
      {/* Floating Chat Icon */}
      <div className="chat-icon" onClick={toggleChat}>
        <PiChatDotsLight />
      </div>

      {/* Chatbox UI */}
      {isOpen && (
        <div className="chatBox">
          {/* Header */}
          <div
            className="chatBox-header"
            style={{ backgroundColor: themeColor }}
          >
            <h3>Any questions? {userName}</h3>
            <button onClick={toggleChat}>
              <RxCrossCircled />
            </button>
          </div>

          {/* Message Section */}
          <div className="chatBox-messages">
            {messages.map((msg, index) => (
              <div
                key={index}
                className={`chat-message ${
                  msg.sender === "user" ? "user-message" : "ai-message"
                }`}
              >
                {msg.text}
              </div>
            ))}
          </div>

          {/* Input Section */}
          <div className="chatBox-input">
            <input
              type="text"
              value={input}
              placeholder="Type your query here..."
              onChange={(event) => setInput(event.target.value)}
              onKeyDown={(event) => event.key === "Enter" && sendMessage()}
            />
            <button
              onClick={sendMessage}
              style={{ backgroundColor: themeColor }}
            >
              {sendButtonText}
            </button>
          </div>
        </div>
      )}
    </>
  );
};

export default ChatBox;
