function AircraftList({aircraftList,selectPopup})
{

    return (<><div><div className="AircraftListTitle">Active Aircraft List</div><div className="AircraftList">{aircraftList.map((aircraft) => <div className="AircraftListItem" key={aircraft.id} id={aircraft.id} onClick={selectPopup}>{aircraft.callsign}</div>)}</div></div></>)
}

export default AircraftList; 