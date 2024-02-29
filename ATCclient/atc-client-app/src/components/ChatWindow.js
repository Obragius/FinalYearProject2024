function ChatWindow({text})
{

    return (<div className='ChatWindow'>
            {text.map((message) => <div className="Message">{message}</div>)}
            </div>)
} 

export default ChatWindow;