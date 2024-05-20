import React from "react";
import {Dialog, DialogTitle, DialogContent } from '@mui/material';

export default function LoadMapPopup(props)
{
    const {openPopup, setOpenPopup, setMapTo} = props;

    function DialogButton()
    {
        setMapTo(parseInt(document.getElementById("mapToLoadInputField").value));
        setOpenPopup(false);
    }

    return (
    <Dialog open={openPopup}>
        <DialogTitle>
            <div>Please Insert Map ID</div>
        </DialogTitle>
        <DialogContent>
            <input id="mapToLoadInputField" type="number" ></input>
            <button onClick={DialogButton}>Submit</button>
        </DialogContent>
    </Dialog>
    )
}