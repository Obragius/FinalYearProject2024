function AircraftList({aircraftList,selectPopup})
{

    return (<><div className="AircraftList">{aircraftList.map((aircraft) => <div key={aircraft.id} id={aircraft.id} onClick={selectPopup}>{aircraft.callsign}</div>)}</div></>)
}

export default AircraftList; 