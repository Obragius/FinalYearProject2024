import LoadMapPopup from './LoadMapPopup';
import LoadTutorialPopup from './LoadTutorialPopup';
import api from '../api/axiosConfig';
import PuzzlePopup from './PuzzlePopup';
import { useState, useEffect } from 'react';
import userEvent from '@testing-library/user-event';

function ButtonTray ({mapID,EditMode,CallInitMap,Pause,RemoveMode,openPopup,setOpenPopup,openTutorial,setOpenTutorial, setMapTo ,LockMap,hasRecognitionSupport,startListening})
{

    // from v0.8 added function to call the create puzzle api to add puzzle to the current map
    const CallInitPuzzle = async (e) =>
        {
            const response = await api.post("api/addPuzzle",{"mapID":mapID});
            setPuzzle(response.data.puzzleID);
            setPuzzleObject(response.data);
            document.getElementById("puzzleID").innerHTML = "Local puzzleID = " + response.data.puzzleID;
            console.log(response.data);
        }

    // from v0.8 added function to handle adding behavior to puzzle
    const [openPuzzle, setPuzzlePopup] = useState(false);

    const [puzzleID, setPuzzle] = useState(-1);

    const [puzzleObject, setPuzzleObject] = useState(null);

    function openPuzzleFunc()
    {
        if (puzzleID != -1)
            {
                setPuzzlePopup(true);
            }
    }



    // from v0.6 added spans for tooltips to help users understand what the buttons do  


    return (
    <div className='ButtonTray'>
        <button title="To add new aircraft to the map" id={"EditModeButton"} onClick={EditMode}>Edit Mode</button>
        <button title="To initilise a new map with a unique id" onClick={CallInitMap}>Add Map</button>
        <button title="Green if simulation is runing, click to pause" className={"myClass"} id={"Sim"} onClick={Pause}>Simulation Running</button>
        <button title="Used to remove aircraft in the edit mode" id={"Remove"} onClick={RemoveMode}>Remove Mode</button>
        <button title="Click to load a map using a Map ID" onClick={() => setOpenPopup(true)}>Load Map</button>
        <button title="Click to create a new puzzle object for this map" onClick={CallInitPuzzle}>Create Puzzle</button>
        <button title="Click to open puzzle options for this map" onClick={openPuzzleFunc}>Open Puzzle</button>
        <LoadMapPopup openPopup={openPopup} setOpenPopup={setOpenPopup} setMapTo={setMapTo} ></LoadMapPopup>
        <LoadTutorialPopup openTutorial={openTutorial} setOpenTutorial={setOpenTutorial} ></LoadTutorialPopup>
        <PuzzlePopup openPuzzle={openPuzzle} setPuzzlePopup={setPuzzlePopup} puzzleID={puzzleID} puzzleObject={puzzleObject} setPuzzleObject={setPuzzleObject} mapID={mapID} ></PuzzlePopup>
        <button title="Click to save the map in a current state to load it using the Map ID" onClick={LockMap}>Lock Map</button>
        {
        hasRecognitionSupport 
            ? <button title="Voice support currently not working" onClick={startListening}>Start listening</button>
            : <h1>No Voice Support</h1>
        }
        <div id={"mapID"}>

        </div>
        <div id={"puzzleID"}>

        </div>
    </div>)
}
export default ButtonTray;