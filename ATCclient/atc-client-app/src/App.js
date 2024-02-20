import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';

import { MapContainer, Marker,TileLayer, Popup, useMapEvents } from 'react-leaflet';
import { Icon, LatLng, latLng } from "leaflet";
import "leaflet/dist/leaflet.css";
import "leaflet-rotatedmarker";

var edit = false;

const elementsToAdd = [];

const oldMarkers = [];

const markers = [];

var mapMarkers = L.layerGroup();

var mapID = null;

function reloadAllElements(aircraft)
{
  const airplaneIcon = new Icon({
    iconUrl: require("./images/GreenPlane.png"),
    iconSize: [38,38]
  })

  var markerOptions = {icon:airplaneIcon,rotationAngle:aircraft.angle.value,draggable:true};
  var newMarker = new L.Marker([aircraft.xPos,aircraft.yPos],markerOptions);
  markers.push(newMarker);
} 

const SendElements = async (e) =>
{
  for (let index = 0; index < elementsToAdd.length; index++) {
    const response = await api.post("api/addAircraft",{"xPos":elementsToAdd[index].getLatLng().lat,"yPos":elementsToAdd[index].getLatLng().lng,"mapID":mapID});
    const result = response.data[0];
    mapID = result.mapID;
    console.log(mapID);
    result.allObjects.forEach(reloadAllElements);
  }
}

const Elements = async (e) =>
{
    const response = await api.post("api/addMap",{map:"hello"});
    mapID = response.data.mapID;
    console.log(mapID);
    response.data.allObjects.forEach(reloadAllElements);
}

function EditMode()
{
  if (edit)
  {
    SendElements();
    edit = false;
  }
  else
  {
    edit = true;
  }
}  

function App() {

  const airplaneIcon = new Icon({
    iconUrl: require("./images/GreenPlane.png"),
    iconSize: [38,38]
  })

  const [map, setMap] = useState();


  function AddObject() {


    const map = useMapEvents({
      click(e)
      {
        if (edit)
        {
          var markerOptions = {icon:airplaneIcon,rotationAngle:0,draggable:true}
          var newMarker = new L.Marker(e.latlng,markerOptions)
          elementsToAdd.push(newMarker);
          newMarker.addTo(map);
        }
      }
    })
   
     return null
     
   }

   function UpdateMarkers()
   {
    const map = useMapEvents({
      click(e)
      {
        if (JSON.stringify(markers) !== JSON.stringify(oldMarkers))
        {
          mapMarkers.clearLayers();
          for(let index = 0; index < markers.length; index++) 
          {
            markers.pop(index).addTo(mapMarkers);
            
          }
          mapMarkers.addTo(map);
          oldMarkers.join;
        }
      }
    })
   
     return null
   }

  const getMap = async () => {
    try
    {
      const response = await api.get("/api/getMap");

      console.log(response.data);

      setMap(response.data);
    }
    catch (err)
    {
      console.log(err);
    }
  }

  useEffect(() =>
  {
    getMap();


  },[])

  return (
    <div>
        <MapContainer onClick={AddObject} center={[51.509865,-0.118092]} zoom={13}>
        <TileLayer  attribution='&copy; <a href="https://carto.com/attributions">CARTO</a>'
        url="https://{s}.basemaps.cartocdn.com/dark_nolabels/{z}/{x}/{y}{r}.png"/>
        <AddObject />
        <UpdateMarkers />
        <reloadAllElements />
        </MapContainer>;
      <button onClick={EditMode}>Edit Mode</button>
      <button onClick={Elements}>Add Map</button>
    </div>
      
  )
}

export default App;
