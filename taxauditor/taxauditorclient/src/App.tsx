import React, { useState } from "react";
import axios from "axios";

const ChatComponent: React.FC = () => {
  const [message, setMessage] = useState<string>("");
  const [response, setResponse] = useState<string | null>(null);

  const handleSendMessage = async () => {
    try {
      const endpointWithQueryParam = `http://localhost:8080/taxauditor/chat?prompt=${encodeURIComponent(
        message
      )}`;
      const axiosResponse = await axios.post<string>(
        endpointWithQueryParam,
        {}
      );
      setResponse(axiosResponse.data);
    } catch (error) {
      console.error("Error sending message:", error);
      setResponse("Error sending message. Please try again.");
    }
  };

  const handleCancel = () => {
    setMessage("");
    setResponse(null);
  };

  return (
    <div className="container mt-5 border-radius">
      <h1>Deloitte Tax Auditor</h1>
      <input
        type="text"
        className="form-control"
        aria-label="Message Content"
        aria-describedby="button-addon2 button-addon3"
        id="chatPrompt"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        placeholder="Tax Prompt"
      />
      <button
        className="btn btn-outline-secondary"
        type="button"
        id="button-addon2"
        onClick={handleSendMessage}
      >
        Send
      </button>
      <button
        className="btn btn-outline-secondary"
        type="button"
        id="button-addon3"
        onClick={handleCancel}
      >
        Cancel
      </button>
      {response && <div className="border p-2 rounded">{response}</div>}
    </div>
  );
};

export default ChatComponent;
