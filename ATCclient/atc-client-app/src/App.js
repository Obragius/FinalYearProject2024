import logo from './logo.svg';
import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';

import { MapContainer, TileLayer } from 'react-leaflet';
import "leaflet/dist/leaflet.css";

function App() {
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
    <MapContainer center={[35.5140,5.1312]} zoom={13}>
      <TileLayer attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors' 
      url="https://tile.openstreetmap.org/{z}/{x}/{y}.png"/>

      </MapContainer>
  )
}

export default App;
