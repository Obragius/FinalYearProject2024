function ButtonTray ({FetchAir,EditMode,Elements,Pause,RemoveMode,LoadMap,LockMap,hasRecognitionSupport,startListening})
{


    return (
    <div className='ButtonTray'>
        <button id={"EditModeButton"} onClick={EditMode}>Edit Mode</button>
        <button onClick={Elements}>Add Map</button>
        <button className={"myClass"} id={"Sim"} onClick={Pause}>Simulation Running</button>
        <button id={"Remove"} onClick={RemoveMode}>Remove Mode</button>
        <button onClick={LoadMap}>Load Map</button>
        <button onClick={LockMap}>Lock Map</button>
        {
        hasRecognitionSupport 
            ? <button onClick={startListening}>Start listening</button>
            : <h1>No Voice Support</h1>
        }
        <div id={"mapID"}>

        </div>
    </div>)
}
export default ButtonTray;