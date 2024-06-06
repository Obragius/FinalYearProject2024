import { useState, useEffect } from 'react';

var key= 0;

var messageList = [];
var currentMessageIndex = -1;

function ChatWindow({api,mapID,text,chatValue,formValue,setFormValue})
{
    useEffect (() => {const interval = setInterval(() => {HandleInput();
    },1000); return () => clearInterval(interval);}, []);

    function SelectPlane(e)
    {
        setFormValue(e.target.id)
        document.getElementsByClassName("Input").item(0).focus();
    }

    const HandleInput = async(e) =>
    {
      if(document.getElementById("input").innerHTML !== "")
      {
        // v0.7 There will be a list of commands that have been inputed and they can be cycled through
        messageList.unshift(formValue);
        currentMessageIndex = -1;
        // -------------------------------------------------------------------------------------------
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
    
    const sendCommand = async(e) => 
    {
      e.preventDefault();
      // v0.7 There will be a list of commands that have been inputed and they can be cycled through
      messageList.unshift(formValue);
      currentMessageIndex = -1;
      // -------------------------------------------------------------------------------------------
      const response = await api.post("api/addCommand",{"mapID":mapID,"text":formValue});
      console.log(response.data);
      var target = formValue.split(" ")[0]
      if (response.status < 300 )
      { 
        var Message = [formValue.toUpperCase(),"Command",key,target];
        chatValue.push(Message);
        key += 1;
        Message = [response.data.toUpperCase(),"Response",key,target];
        chatValue.push(Message);
      }
      setFormValue('')
      key += 1;
    }

    // v0.7 Cycle through all inputs
    const loadPreviosMessage = (event) =>
    {
      if (event.key == "ArrowUp")
        {
          if (currentMessageIndex < messageList.length-1)
          {
            currentMessageIndex += 1;
          }
          setFormValue(messageList[currentMessageIndex]);
        }
      if (event.key == "ArrowDown")
        {
          if (currentMessageIndex > 0)
          {
            currentMessageIndex -= 1;
          }
          setFormValue(messageList[currentMessageIndex]);
        }
    }

    // From v0.7 input field to detect key presses to enable up arrow to load previous messages

    return (<><div className='ChatWindow'>
            {text.map((message) => <div id={message[3]} onClick={(e) => SelectPlane(e)} key={message[2]} className={message[1]}><b>{"-"+message[0]}</b></div>)}
            </div>
            <form name="myform" onSubmit={sendCommand}>
            <input id="CommandInput" onKeyDown={loadPreviosMessage} className='Input' value={formValue} onChange={(e) => setFormValue(e.target.value)}></input>
          </form>
          <p hidden id="input"></p>
          </>)
} 

export default ChatWindow;