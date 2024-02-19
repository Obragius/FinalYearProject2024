import logo from './logo.svg';
import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';

import { MapContainer, Marker,TileLayer, Popup, useMapEvents } from 'react-leaflet';
import { Icon, LatLng, latLng } from "leaflet";
import "leaflet/dist/leaflet.css";
import "leaflet-rotatedmarker";
import { click } from '@testing-library/user-event/dist/click';

var edit = false;

function EditMode()
{
  if (edit)
  {
    edit = false;
  }
  else
  {
    edit = true;
  }
}  

function App() {
  const markers = [{geocode:[51.509865,-0.118292],popUp:"Airplane 1",angle:0},{geocode:[51.509765,-0.118052],popUp:"Airplane 2",angle:180}]

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
          new L.Marker(e.latlng,markerOptions).addTo(map);
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
        {markers.map(marker => (<Marker position={marker.geocode} icon={airplaneIcon} rotationAngle={marker.angle} draggable={true} popUp={marker.popUp} >
        <Popup>{marker.popUp}</Popup>
        </Marker>))}
        <AddObject />
        </MapContainer>;
      <button onClick={EditMode}>Edit Mode</button>
    </div>
      
  )
}

export default App;
