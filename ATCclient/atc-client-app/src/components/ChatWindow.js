import { useState, useEffect } from 'react';

var key= 0;

function ChatWindow({api,mapID,text,chatValue,formValue,setFormValue})
{
    useEffect (() => {const interval = setInterval(() => {HandleInput();
    },1000); return () => clearInterval(interval);}, []);

    const HandleInput = async(e) =>
  {
    if(document.getElementById("input").innerHTML !== "")
    {
      var newText = document.getElementById("input").innerHTML;
      document.getElementById("input").innerHTML = "";
      const response = await api.post("api/addCommand",{"mapID":mapID,"text":newText});
      console.log(response.data);
      var Message = [newText.toUpperCase(),"Command"];
      chatValue.push(Message);
      if (response.data !== "Aircraft not found")
      { 
        Message = [response.data.toUpperCase(),"Response"];
        chatValue.push(Message);
      }
      setFormValue('')
    }
  }
    
    const sendCommand = async(e) => {
        e.preventDefault();
        const response = await api.post("api/addCommand",{"mapID":mapID,"text":formValue});
        console.log(response.data);
        var Message = [formValue.toUpperCase(),"Command",key];
        chatValue.push(Message);
        key += 1;
        if (response.data !== "Aircraft not found")
        { 
          Message = [response.data.toUpperCase(),"Response",key];
          chatValue.push(Message);
        }
        setFormValue('')
        key += 1;
      }

    return (<><div className='ChatWindow'>
            {text.map((message) => <div key={message[2]} className={message[1]}>{"-"+message[0]}</div>)}
            </div>
            <form name="myform" onSubmit={sendCommand}>
            <input className='Input' value={formValue} onChange={(e) => setFormValue(e.target.value)}></input>
          </form>
          <p hidden id="input"></p></>)
} 

export default ChatWindow;