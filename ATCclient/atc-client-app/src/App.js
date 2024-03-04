import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';

import { MapContainer, Marker,TileLayer, Popup, useMapEvents } from 'react-leaflet';
import { Icon, LatLng, latLng } from "leaflet";
import "leaflet/dist/leaflet.css";
import "leaflet-rotatedmarker";
import { useMap } from 'react-leaflet';
import ChatWindow from './components/ChatWindow';
import useSpeechRecognition from './hooks/useSpeechRecognitionHook';
import * as Papa from "papaparse";
import airport from './airports.csv';
import navaid from './navaids.csv';


var edit = false;

var removeMode = false;

var pause = false;

var elementID = 0;

var popupID = 0;

var defaultAircraftNum = 0;

const elementsToAdd = [];

var angleBefore = '';

var speedBefore = '';

var signBefore = '';

var altBefore = '';

const markers = [];

const empty = [];

var init = true;

var mapMarkers = L.layerGroup();

var mapID = null;

const chatMessages = [];

function buildAircraft(aircraft)
{
  var speed = (aircraft.speed*1.94384).toPrecision(5);
  var result = "";
  result += "<table><tr><th>Callsign</th><th>Heading</th><th>Speed</th><th>Altitude</th></tr><tr><td>" + aircraft.callsign + "</td><td>" + aircraft.angle.value + "°</td><td>" + speed + "kts</td><td>FL" + (aircraft.height/100) + "</td></tr></table>"
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
  var toolTipOptions = {content:aircraft.callsign.toString(),permanent:true,opacity:1,className:'myTooltip',direction:"bottom"}
  var toolTip = new L.Tooltip(toolTipOptions);
  popup.a = aircraft.id;
  popup.id = aircraft.callsign;
  newMarker.setRotationOrigin('center center');
  newMarker.bindPopup(popup);
  newMarker.bindTooltip(toolTip);
  markers.push(newMarker);
} 

const SendElements = async (e) =>
{
  var elementsNum = elementsToAdd.length;
  for (let index = (elementsNum-1); index > -1; index--) {
    var element = elementsToAdd.pop()
    element.openPopup();
    var angle = parseInt(document.getElementById(("angle"+(index))).value);
    if (isNaN(angle))
    {
      angle = 0;
    } 
    var speed = parseInt(document.getElementById(("speed"+(index))).value);
    if (isNaN(speed))
    {
      speed = 200;
    } 
    var altitude = parseInt(document.getElementById(("alt"+(index))).value);
    if (isNaN(altitude))
    {
      altitude = 10000;
    } 
    console.log(angle,speed,altitude)
    // Convert speed from knots to m/s
    speed = speed/1.94384.toPrecision(5);
    var sign = document.getElementById(("sign"+(index))).value
    if (sign == "")
    {
      sign = "Default Aircraft" + defaultAircraftNum
      defaultAircraftNum += 1;
    }
    element.closePopup();
    // var angle = parseInt(element.getPopup().getContent().slice(6,9));
    // var speed = parseInt(element.getPopup().getContent().slice(19,22));
    // var sign = parseInt(element.getPopup().getContent().slice(19,22));
    const response = await api.post("api/addAircraft",{"xPos":element.getLatLng().lat,"yPos":element.getLatLng().lng,"angle":angle,"speed":speed,"sign":sign,"altitude":altitude,"mapID":mapID});
    if (response.status !== 406)
    {
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
        var thisMarker = markers.pop();
        if (index == 0)
        {
          mapMarkers.addLayer(thisMarker);
        }
      }
    }
  }
}

const Elements = async (e) =>
{
    const response = await api.post("api/addMap",{map:"newMap"});
    mapID = response.data.mapID;
    document.getElementById("mapID").innerHTML = "MapID = " + mapID;
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
    popupID = 0;
    document.getElementById("EditModeButton").classList.remove("myClass");
  }
  else
  {

    edit = true;
    document.getElementById("EditModeButton").classList.add("myClass");
  }
} 

function RemoveMode()
{
  if (removeMode)
  {
    document.getElementById("Remove").classList.remove("myClass");
    removeMode = false;
  }
  else
  {
    document.getElementById("Remove").classList.add("myClass");
    removeMode = true;
  }
} 

function Pause()
{
  if (pause)
  {
    document.getElementById("Sim").classList.add("myClass");
    pause = false;
  }
  else
  {
    document.getElementById("Sim").classList.remove("myClass");
    pause = true;
  }
} 

function App() {

  useEffect (() => {const interval = setInterval(() => {if(mapID !== null && edit == false && pause == false){Tick();handleInput();
    }},1000); return () => clearInterval(interval);}, []);

  const [formValue, setFormValue] = useState("");

  const [chatValue, setChatValue] = useState([]);

  const {text,setText,startListening,stopListening,isListening,hasRecognitionSupport} = useSpeechRecognition();

  const [handleCounter,setCounter] = useState(0);

  const ReadFile = async(e) => 
  {
    Papa.parse(airport, {
      download: true,
      complete: LoadAllAirports
    });

    Papa.parse(navaid, {
      download: true,
      complete: LoadAllNavaids
    });



  }

  function LoadAllAirports(data){

    var result = data.data
    const TriangleIcon = new Icon({
      iconUrl: require("./images/Airport.png"),
      iconSize: [10,10]
    })
    var num = result.length;
    for (var index = 1; index < num; index++)
    {
      var x = parseFloat(result[index][4]);
      var y = parseFloat(result[index][5]);
      var markerOptions = {icon:TriangleIcon,draggable:false};
      if (((49 < x )&&(x < 52)) && ((-2 < y)&&(y < 2)))
      {
        if (result[index][2] == "large_airport")
        {
          var toolTipOptions = {content:result[index][1],permanent:true,opacity:1,className:'myTooltip',direction:"bottom"}
          var toolTip = new L.Tooltip(toolTipOptions);
          var newMarker = new L.Marker([x,y],markerOptions);
          newMarker.bindTooltip(toolTip)
          mapMarkers.addLayer(newMarker)
        }
      }
    }
  }

  function LoadAllNavaids(data){

    var result = data.data
    const TriangleIcon = new Icon({
      iconUrl: require("./images/BlueTriangle.png"),
      iconSize: [10,10]
    })
    var num = result.length;
    for (var index = 1; index < num; index++)
    {
      var x = parseFloat(result[index][6]);
      var y = parseFloat(result[index][7]);
      var markerOptions = {icon:TriangleIcon,draggable:false};
      if (((49 < x )&&(x < 52)) && ((-2 < y)&&(y < 2)))
      {
        var toolTipOptions = {content:result[index][2],permanent:true,opacity:1,className:'myTooltip',direction:"bottom"}
        var toolTip = new L.Tooltip(toolTipOptions);
        var newMarker = new L.Marker([x,y],markerOptions);
        newMarker.bindTooltip(toolTip)
        mapMarkers.addLayer(newMarker)
      }
    }
  }


  const sendCommand = async(e) => {
    e.preventDefault();
    const response = await api.post("api/addCommand",{"mapID":mapID,"text":formValue});
    console.log(response.data);
    var Message = [formValue.toUpperCase(),"Command"];
    chatValue.push(Message);
    if (response.data !== "Aircraft not found")
    { 
      Message = [response.data.toUpperCase(),"Response"];
      chatValue.push(Message);
    }
    setFormValue('')
  }

  const LockMap = async(e) => {
    const response = await api.post("api/lockmap",{"mapID":mapID});
    console.log(response.data);
  }

  const handleInput = async(e) =>
  {
    if(document.getElementById("input").innerHTML !== "")
    {
      var newText = document.getElementById("input").innerHTML;
      document.getElementById("input").innerHTML = "";
      const response = await api.post("api/addCommand",{"mapID":mapID,"text":newText});
      console.log(response.data);
      var Message = [newText.toUpperCase(),"Command"];
      chatValue.push(Message);
      if (response.data !== "Aircraft not found")
      { 
        Message = [response.data.toUpperCase(),"Response"];
        chatValue.push(Message);
      }
      setFormValue('')
    }
  }

  const LoadMap = async(e) =>
  {
    var loadingMap = parseInt(formValue);
    const response = await api.post("api/getgeomap",{"mapID":loadingMap});
    const result = response.data;
    mapID = result.mapID;
    document.getElementById("mapID").innerHTML = "MapID = " + mapID;
    for(let index = 0; index <= markers.length; index++)
    {
      markers.pop();
    }
    result.allObjects.forEach(ReloadAllElements);

    var markNum = markers.length;
    for( let index = 0; index < markNum; index ++)
    {
      var thisMarker = markers.pop();
      mapMarkers.addLayer(thisMarker);
    }
  }
  

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
        if (myMarkers[index].getPopup() !== undefined)
        {
          if (myMarkers[index].getPopup().a == allAircraft[i].id)
          {
            myMarkers[index].setLatLng([allAircraft[i].xPos,allAircraft[i].yPos])
            myMarkers[index].setRotationAngle(allAircraft[i].angle.value);
            var aircraftInfo = buildAircraft(allAircraft[i])
            myMarkers[index].getPopup().setContent(aircraftInfo);
          }
          else if (myMarkers[index].getPopup().a == 0)
          {
            mapMarkers.removeLayer(myMarkers[index]);
          }
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
          var elementSelected;
          var markNum = mapMarkers.getLayers().length;
          for( let index = 0; index < markNum; index ++)
          {
            if (mapMarkers.getLayers()[index].getPopup() !== undefined)
            {
              if(mapMarkers.getLayers()[index].getPopup().isOpen())
              {
                elementSelected = mapMarkers.getLayers()[index].getPopup().id;
              }
            }
          }
          var change = 0;
          if ((document.getElementById(("angle"+(elementSelected))).value) != angleBefore)
          {
            change = 0;
          }
          else if ((document.getElementById(("speed"+(elementSelected))).value) != speedBefore)
          {
            change = 1;
          }
          else if ((document.getElementById(("sign"+(elementSelected))).value) != signBefore)
          {
            change = 2;
          }
          else if ((document.getElementById(("alt"+(elementSelected))).value) != altBefore)
          {
            change = 3;
          }
          var content = 'Angle:<input id = angle'+(elementSelected)+' value='+(document.getElementById(("angle"+(elementSelected))).value)+'></input><br>Speed:<input id = speed'+(elementSelected)+' value='+(document.getElementById(("speed"+(elementSelected))).value)+'></input><br>Callsign:<input id = sign'+(elementSelected)+' value='+(document.getElementById(("sign"+(elementSelected))).value)+'></input><br>Altitude:<input id = alt'+(elementSelected)+' value='+(document.getElementById(("alt"+(elementSelected))).value)+'></input>';
          var focusElement;
          switch (change)
          {
            case 0: focusElement = "angle";break;
            case 1: focusElement = "speed";break;
            case 2: focusElement = "sign";break;
            case 3: focusElement = "alt";break;
          }
          elementsToAdd[elementSelected].getPopup().setContent(content);
          angleBefore = document.getElementById(("angle"+(elementSelected))).value;
          speedBefore = document.getElementById(("speed"+(elementSelected))).value;
          signBefore = document.getElementById(("sign"+(elementSelected))).value;
          altBefore = document.getElementById(("alt"+(elementSelected))).value;
          document.getElementById((focusElement+(elementSelected))).focus();
          document.getElementById((focusElement+(elementSelected))).setSelectionRange(1000, 1000);
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
          signBefore = '';
          angleBefore = '';
          speedBefore = '';
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

  function SelectPlane(){
    const map = useMapEvents({
      popupopen(e)
      {
        if (e.popup.a != 0 && e.popup.a != 1)
        {
          setFormValue(e.popup.id);
        }
      },
      popupclose(e)
      {
        if (e.popup.a != 0 && e.popup.a != 1)
        {
          setFormValue("");
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
          var popupOptions = {content:'Angle:<input id = angle'+elementID+' maxlength=3></input><br>Speed:<input id = speed'+elementID+'></input><br>Callsign:<input id = sign'+elementID+'></input><br>Altitude:<input id = alt'+elementID+'></input>',interactive:true};
          var popup = new L.Popup(popupOptions);
          popup.a = 1;
          popup.id = popupID;
          popupID += 1;
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

      <div className='ButtonTray'>
        <button id={"EditModeButton"} onClick={EditMode}>Edit Mode</button>
        <button onClick={Elements}>Add Map</button>
        <button className={"myClass"} id={"Sim"} onClick={Pause}>Simulation Running</button>
        <button id={"Remove"} onClick={RemoveMode}>Remove Mode</button>
        <button onClick={LoadMap}>Load Map</button>
        <button onClick={ReadFile}>Load Points</button>
        <button onClick={LockMap}>Lock Map</button>
        {
          hasRecognitionSupport 
            ? <button onClick={startListening}>Start listening</button>
            : <h1>No Voice Support</h1>
        }
        <div id={"mapID"}></div>
      </div>

      <div className='Container'>
        <div>
          <MapContainer center={[51.8864,0.2413]} zoom={13}> 
          <TileLayer  attribution='&copy; <a href="https://carto.com/attributions">CARTO</a>'
          url="https://{s}.basemaps.cartocdn.com/dark_nolabels/{z}/{x}/{y}{r}.png"/>
          <AddObject />
          <UpdatePopup />
          <FinishPopup />
          <SelectPlane />
          <RemoveObject />
          </MapContainer>
        </div>
        <div>
          <ChatWindow text={chatValue}></ChatWindow>
          <form name="myform" onSubmit={sendCommand}>
            <input className='Input' value={formValue} onChange={(e) => setFormValue(e.target.value)}></input>
          </form>
          <p hidden id="input"></p>
        </div>
      </div>
      
    </div>
      
  )
}

export default App;
