import React from "react";
import {Dialog, DialogTitle, DialogContent } from '@mui/material';

// v0.7 Tutorial Popup for basic application information

export default function LoadTutorialPopup(props)
{
    const {openTutorial, setOpenTutorial} = props;

    function DialogButton()
    {
        setOpenTutorial(false);
    }

    return (
    <Dialog open={openTutorial}>
        <DialogTitle>
            <div><b>Welcome to Air Traffic Puzzle Simulator v0.7</b></div>
        </DialogTitle>
        <DialogContent>
            <p>This is a project created as part of Kingston University Final Year Project</p>
            <p>The project is hosted at : <a href="https://github.com/Obragius/FinalYearProject2024">GitHub</a></p>
            <ul>
                <li>To initilise the map click on it and then click Add Map (You should see a mapID given)</li>
                <li>Edit mode<ul>
                    <li>To add aircraft click on the Edit Mode and click on the map to add an aircraft.</li>
                    <li>Click on the aircraft to change it's parameters.</li>
                    <li>To remove an aircraft, click on Remve Mode and then aircraft to be deleted and then turn the Remove Mode off.</li>
                    <li>Once you've added all the aircraft click on the Edit Mode to finish editing.</li>
                    </ul>
                </li>
                <li>Use the Simulation Running button to turn the simulation on/off</li>
            </ul>
            <button onClick={DialogButton}>Close</button>
        </DialogContent>
    </Dialog>
    )
}