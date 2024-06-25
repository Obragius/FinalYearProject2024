import React from "react";
import {Dialog, DialogTitle, DialogContent } from '@mui/material';
import api from '../api/axiosConfig';

export default function PuzzlePopup(props)
{
    const {openPuzzle, setPuzzlePopup, puzzleID, puzzleObject, setPuzzleObject, mapID} = props;

    const CallAddBehaviour = async (e) =>
        {
            const response = await api.post("api/addBehaviour",{"mapID":mapID});
            setPuzzleObject(response.data);
            console.log(response.data);
        }

    // Building the behavior objects
    const behaviours = []

    if (puzzleObject != null)
    {
        for (var i = 0; i < puzzleObject.behaviours.length; i++)
        {
            behaviours.push(<li>Behaviour Name - Complete?</li>);
        }
    }
    

    return (
    <Dialog open={openPuzzle}>
        <DialogTitle>
            <div>Puzzle "{puzzleID}"</div>
        </DialogTitle>
        <DialogContent>
            <ul>{behaviours}</ul>
            <button onClick={() => setPuzzlePopup(false)}>Close</button>
            <button onClick={CallAddBehaviour}>Add Behaviuor</button>
        </DialogContent>
    </Dialog>
    )
}