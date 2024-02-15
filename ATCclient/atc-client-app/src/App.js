import logo from './logo.svg';
import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';

import { MapContainer, Marker,TileLayer, Popup } from 'react-leaflet';
import { Icon } from "leaflet";
import "leaflet/dist/leaflet.css";
import "leaflet-rotatedmarker";

function App() {
  const markers = [{geocode:[51.509865,-0.118292],popUp:"Airplane 1",angle:0},{geocode:[51.509765,-0.118052],popUp:"Airplane 2",angle:180}]

  const airplaneIcon = new Icon({
    iconUrl: require("./images/GreenPlane.png"),
    iconSize: [38,38]
  })

  const [map, setMap] = useState();

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
    <MapContainer center={[51.509865,-0.118092]} zoom={13}>
      <TileLayer attribution='&copy; <a href="https://carto.com/attributions">CARTO</a>'
      url="https://{s}.basemaps.cartocdn.com/dark_nolabels/{z}/{x}/{y}{r}.png"/>
      {markers.map(marker => (<Marker position={marker.geocode} icon={airplaneIcon} rotationAngle={marker.angle} draggable={true} popUp={marker.popUp} >
        <Popup>{marker.popUp}</Popup>
      </Marker>))}
      </MapContainer>
      
  )
}

export default App;
