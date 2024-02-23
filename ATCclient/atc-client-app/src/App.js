import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';

import { MapContainer, Marker,TileLayer, Popup, useMapEvents } from 'react-leaflet';
import { Icon, LatLng, latLng } from "leaflet";
import "leaflet/dist/leaflet.css";
import "leaflet-rotatedmarker";
import { useMap } from 'react-leaflet';

var edit = false;

var removeMode = false;

var pause = false;

var elementID = 0;

const elementsToAdd = [];

const markers = [];

const empty = [];

var init = true;

var mapMarkers = L.layerGroup();

var mapID = null;

function buildAircraft(aircraft)
{
  var speed = aircraft.speed*1.94384.toPrecision(5);
  var result = "";
  result += "<table><tr><th>ID</th><th>Heading</th><th>Speed</th><th>Altitude</th></tr><tr><td>" + aircraft.id + "</td><td>" + aircraft.angle.value + "°</td><td>" + speed + "kts</td><td>FL" + (aircraft.height/100) + "</td></tr></table>"
  return result;
}

function ReloadAllElements(aircraft)
{
  const airplaneIcon = new Icon({
    iconUrl: require("./images/GreenPlane.png"),
    iconSize: [38,38]
  })

  var markerOptions = {icon:airplaneIcon,rotationAngle:aircraft.angle.value,draggable:false};
  var newMarker = new L.Marker([aircraft.xPos,aircraft.yPos],markerOptions);
  var aircraftInfo = buildAircraft(aircraft);
  var popupOptions = {content:aircraftInfo,interactive:true};
  var popup = new L.Popup(popupOptions);
  var toolTipOptions = {content:aircraft.id.toString(),permanent:true,opacity:1,className:'myTooltip',direction:"bottom"}
  var toolTip = new L.Tooltip(toolTipOptions);
  popup.a = aircraft.id;
  newMarker.setRotationOrigin('center center');
  newMarker.bindPopup(popup);
  newMarker.bindTooltip(toolTip);
  markers.push(newMarker);
} 

const SendElements = async (e) =>
{
  var elementsNum = elementsToAdd.length;
  for (let index = 0; index < elementsNum; index++) {
    var element = elementsToAdd.pop()
    var angle = parseInt(element.getPopup().getContent().slice(6,9));
    const response = await api.post("api/addAircraft",{"xPos":element.getLatLng().lat,"yPos":element.getLatLng().lng,"angle":angle,"mapID":mapID});
    const result = response.data[0];
    mapID = result.mapID;
    console.log("Aircraft sent, Map returned")
    for(let index = 0; index <= markers.length; index++)
    {
      markers.pop();
    }
    
    result.allObjects.forEach(ReloadAllElements);

    var markNum = markers.length;
    for( let index = 0; index < markNum; index ++)
    {
      mapMarkers.addLayer(markers.pop());
    }
  }
}

const Elements = async (e) =>
{
    const response = await api.post("api/addMap",{map:"newMap"});
    mapID = response.data.mapID;
    console.log(mapID);
    response.data.allObjects.forEach(ReloadAllElements);
}

function EditMode()
{
  if (edit)
  {
    edit = false;
    if (elementsToAdd.length > 0)
    {
      SendElements();
    }
    elementID = 0;
  }
  else
  {
    edit = true;
  }
} 

function RemoveMode()
{
  console.log(removeMode)
  if (removeMode)
  {
    removeMode = false;
  }
  else
  {
    removeMode = true;
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

  useEffect (() => {const interval = setInterval(() => {if(mapID !== null && edit == false && pause == false){Tick();
    }},1000); return () => clearInterval(interval);}, []);

  const airplaneIcon = new Icon({
    iconUrl: require("./images/GreenPlane.png"),
    iconSize: [38,38]
  })

  const [map, setMap] = useState();


  const Tick = async (e) =>
{
    const response = await api.post("api/tick",{"mapID":mapID});
    mapID = response.data.mapID;
    markers.length = 0;
    response.data.allObjects.forEach(ReloadAllElements);

    var markerNum = mapMarkers.getLayers().length;
    var allAircraft = response.data.allObjects;
    var myMarkers = mapMarkers.getLayers();
    for(let index = 0; index < markerNum; index++) 
    {
      var aircraftNum = allAircraft.length;
      for (let i = 0; i < aircraftNum; i++)
      {
        if (myMarkers[index].getPopup().a == allAircraft[i].id)
        {
          myMarkers[index].setLatLng([allAircraft[i].xPos,allAircraft[i].yPos])
          myMarkers[index].setRotationAngle(allAircraft[i].angle.value);
        }
        else if (myMarkers[index].getPopup().a == 0)
        {
          mapMarkers.removeLayer(myMarkers[index]);
        }
      }
    }
}

  function UpdatePopup() {

    const map = useMapEvents({
      keyup(e)
      {
        if (elementID > 0)
        {
          var content = 'Angle:<textarea id = '+(elementID-1)+'>'+(document.getElementById(elementID-1).value)+'</textarea>';
          elementsToAdd[elementID-1].getPopup().setContent(content)
        }
      }
    })
  }

  function FinishPopup(){
    const map = useMapEvents({
      popupclose(e)
      {
        if (e.popup.a == 1)
        {
          e.popup.setContent("Angle:"+e.popup.getContent().slice(23,26));
          e.popup.a = 0
        }
      }
    })
  }

  function RemoveObject(){
    const map = useMapEvents({
      popupopen(e)
      {
        if (removeMode)
        {
          if (elementsToAdd.indexOf(e.popup._source) != -1)
          {
            elementsToAdd.splice(elementsToAdd.indexOf(e.popup._source),1)
            mapMarkers.removeLayer(e.popup._source)
            elementID -= 1;
          }
        }
      }
    })
  }


  function AddObject() {


    const map = useMapEvents({
      click(e)
      {

        if (edit)
        {
          var markerOptions = {icon:airplaneIcon,rotationAngle:0,draggable:true}
          var newMarker = new L.Marker(e.latlng,markerOptions)
          var popupOptions = {content:'Angle:<textarea id = '+elementID+'></textarea>',interactive:true};
          var popup = new L.Popup(popupOptions);
          popup.a = 1;
          newMarker.bindPopup(popup);
          elementsToAdd.push(newMarker);
          newMarker.addTo(mapMarkers);
          elementID += 1;
        }
        else
        {
          if (init)
          {
            mapMarkers.addTo(map);
            init = false;
          }
        }
      }
    })
   
     return null
     
   }

  return (
    <div>
        <MapContainer center={[51.509865,-0.118092]} zoom={13}> 
        <TileLayer  attribution='&copy; <a href="https://carto.com/attributions">CARTO</a>'
        url="https://{s}.basemaps.cartocdn.com/dark_nolabels/{z}/{x}/{y}{r}.png"/>
        <AddObject />
        <UpdatePopup />
        <FinishPopup />
        <RemoveObject />
        </MapContainer>;
        
      <button onClick={EditMode}>Edit Mode</button>
      <button onClick={Elements}>Add Map</button>
      <button onClick={Pause}>Pause Tick</button>
      <button onClick={RemoveMode}>Remove Mode</button>
    </div>
      
  )
}

export default App;
