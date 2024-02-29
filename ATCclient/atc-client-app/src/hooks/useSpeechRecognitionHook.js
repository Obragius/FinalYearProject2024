import { useEffect, useState } from "react";

let recognition = null;
if ("webkitSpeechRecognition" in window)
{
    recognition = new webkitSpeechRecognition();
    recognition.continuous = true;
    recognition.lang = "en-US";
}

const useSpeechRecognition = () => {
    const [text, setText] = useState("");
    const [isListening, setIsListening] = useState(false);

    function CommonWords(text)
    {
        var newtext = text.replace("Ryanair ","RYR");
        return newtext;
    }

    useEffect(() => {
        if (!recognition) return;
        recognition.onresult = (event) => {
            console.log("onresult event: ",event)
            setText(CommonWords(event.results[0][0].transcript));
            document.getElementById("input").value = CommonWords(event.results[0][0].transcript);
            recognition.stop()
            setIsListening(false);
        }
    }, [])
    const startListening = () => {
        setText('');
        setIsListening(true);
        recognition.start();
    }

    const stopListening = () =>
    {
        setIsListening(false);
        recognition.stop();
    }

    return {text,setText,isListening,startListening,stopListening,hasRecognitionSupport: !!recognition,}
}


export default useSpeechRecognition;