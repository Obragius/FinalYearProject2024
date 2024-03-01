function ChatWindow({text})
{

    return (<div className='ChatWindow'>
            {text.map((message) => <div className={message[1]}>{"-"+message[0]}</div>)}
            </div>)
} 

export default ChatWindow;