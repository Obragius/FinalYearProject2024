import LoadMapPopup from './LoadMapPopup';

function ButtonTray ({FetchAir,EditMode,Elements,Pause,RemoveMode,openPopup,setOpenPopup, setMapTo ,LockMap,hasRecognitionSupport,startListening})
{




    return (
    <div className='ButtonTray'>
        <button id={"EditModeButton"} onClick={EditMode}>Edit Mode</button>
        <button onClick={Elements}>Add Map</button>
        <button className={"myClass"} id={"Sim"} onClick={Pause}>Simulation Running</button>
        <button id={"Remove"} onClick={RemoveMode}>Remove Mode</button>
        <button onClick={() => setOpenPopup(true)}>Load Map</button>
        <LoadMapPopup openPopup={openPopup} setOpenPopup={setOpenPopup} setMapTo={setMapTo} ></LoadMapPopup>
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