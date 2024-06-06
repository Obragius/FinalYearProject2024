import LoadMapPopup from './LoadMapPopup';
import LoadTutorialPopup from './LoadTutorialPopup';

function ButtonTray ({FetchAir,EditMode,Elements,Pause,RemoveMode,openPopup,setOpenPopup,openTutorial,setOpenTutorial, setMapTo ,LockMap,hasRecognitionSupport,startListening})
{

    // from 0.6 added spans for tooltips to help users understand what the buttons do


    return (
    <div className='ButtonTray'>
        <button title="To add new aircraft to the map" id={"EditModeButton"} onClick={EditMode}>Edit Mode</button>
        <button title="To initilise a new map with a unique id" onClick={Elements}>Add Map</button>
        <button title="Green if simulation is runing, click to pause" className={"myClass"} id={"Sim"} onClick={Pause}>Simulation Running</button>
        <button title="Used to remove aircraft in the edit mode" id={"Remove"} onClick={RemoveMode}>Remove Mode</button>
        <button title="Click to load a map using a Map ID" onClick={() => setOpenPopup(true)}>Load Map</button>
        <LoadMapPopup openPopup={openPopup} setOpenPopup={setOpenPopup} setMapTo={setMapTo} ></LoadMapPopup>
        <LoadTutorialPopup openTutorial={openTutorial} setOpenTutorial={setOpenTutorial} ></LoadTutorialPopup>
        <button title="Click to save the map in a current state to load it using the Map ID" onClick={LockMap}>Lock Map</button>
        {
        hasRecognitionSupport 
            ? <button title="Voice support currently not working" onClick={startListening}>Start listening</button>
            : <h1>No Voice Support</h1>
        }
        <div id={"mapID"}>

        </div>
    </div>)
}
export default ButtonTray;