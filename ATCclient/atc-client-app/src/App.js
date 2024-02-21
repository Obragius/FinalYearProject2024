import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';

import { MapContainer, Marker,TileLayer, Popup, useMapEvents } from 'react-leaflet';
import { Icon, LatLng, latLng } from "leaflet";
import "leaflet/dist/leaflet.css";
import "leaflet-rotatedmarker";

var edit = false;

var pause = false;

const elementsToAdd = [];

const markers = [];

const empty = [];

var mapMarkers = L.layerGroup();

var mapID = null;

function ReloadAllElements(aircraft)
{
  const airplaneIcon = new Icon({
    iconUrl: require("./images/GreenPlane.png"),
    iconSize: [38,38]
  })

  var markerOptions = {icon:airplaneIcon,rotationAngle:aircraft.angle.value,draggable:false};
  var newMarker = new L.Marker([aircraft.xPos,aircraft.yPos],markerOptions);
  markers.push(newMarker);
} 

const SendElements = async (e) =>
{
  for (let index = 0; index < elementsToAdd.length; index++) {
    const response = await api.post("api/addAircraft",{"xPos":elementsToAdd[index].getLatLng().lat,"yPos":elementsToAdd[index].getLatLng().lng,"mapID":mapID});
    const result = response.data[0];
    mapID = result.mapID;
    console.log("Aircraft sent, Map returned")
    for(let index = 0; index <= markers.length; index++)
    {
      markers.pop();
    }
    result.allObjects.forEach(ReloadAllElements);
  }
}

const Tick = async (e) =>
{
    const response = await api.post("api/tick",{"mapID":mapID});
    mapID = response.data.mapID;
    console.log(mapID);
    markers.length = 0;
    response.data.allObjects.forEach(ReloadAllElements);
}

const Elements = async (e) =>
{
    const response = await api.post("api/addMap",{map:"hello"});
    mapID = response.data.mapID;
    console.log(mapID);
    response.data.allObjects.forEach(ReloadAllElements);
}

function EditMode()
{
  if (edit)
  {
    edit = false;
    SendElements();
  }
  else
  {
    edit = true;
  }
}  

function Pause()
{
  if (pause)
  {
    pause = false;
  }
  else
  {
    pause = true;
  }
} 

function App() {

  useEffect (() => {const interval = setInterval(() => {if(mapID !== null && edit == false && pause == false){Tick();}},1000); return () => clearInterval(interval);}, []);

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
        if (JSON.stringify(markers) !== JSON.stringify(empty))
        {
          var markerNum = elementsToAdd.length;
          for(let index = 0; index < markerNum; index++) 
          {
            if (elementsToAdd.length != 0)
            {
              map.removeLayer(elementsToAdd.pop());
            }
          }
          mapMarkers.clearLayers();
          map.removeLayer(mapMarkers)
          var markerNum = markers.length;
          for(let index = 0; index < markerNum; index++) 
          {
            markers.pop().addTo(mapMarkers);
          }
          mapMarkers.addTo(map);
        }
      }
    })
   
     return null
   }

  return (
    <div>
        <MapContainer onClick={AddObject} center={[51.509865,-0.118092]} zoom={13}>
        <TileLayer  attribution='&copy; <a href="https://carto.com/attributions">CARTO</a>'
        url="https://{s}.basemaps.cartocdn.com/dark_nolabels/{z}/{x}/{y}{r}.png"/>
        <AddObject />
        <UpdateMarkers />
        </MapContainer>;
      <button onClick={EditMode}>Edit Mode</button>
      <button onClick={Elements}>Add Map</button>
      <button onClick={Pause}>Pause Tick</button>
    </div>
      
  )
}

export default App;
