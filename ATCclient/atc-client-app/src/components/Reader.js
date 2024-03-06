import * as Papa from "papaparse";
import airport from '.././airports.csv';
import navaid from '.././navaids.csv';
import { Icon, LatLng, latLng } from "leaflet";
import { MapContainer, Marker,TileLayer, Popup, useMapEvents } from 'react-leaflet';
import "leaflet/dist/leaflet.css";
import "leaflet-rotatedmarker";
import api from '.././api/axiosConfig';

var lat = "";
var lng = "";
var airportCode = "";
var runway = 0;

function Reader(mapMarkers)
{
    
    Papa.parse(airport, {
      download: true,
      complete: LoadAllAirports
    });

    Papa.parse(navaid, {
      download: true,
      complete: LoadAllNavaids
    });



  function LoadAllAirports(data){

    var result = data.data
    const TriangleIcon = new Icon({
      iconUrl: require(".././images/Airport.png"),
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

          // create a vector
          var directionOne = result[index][8];
          lat = x.toString();
          lng = y.toString();
          airportCode = result[index][1];
          for (var index2 = 0; index2 < 2; index2++)
          {
            runway = index2.toString();
            getRunway();
          }
        }
      }
    }
  }

  function LoadILS(polygon)
  {
    console.log(polygon.data)
    const color = {fillColor:"rgba(255, 0, 0, 0.3)", opacity:0.1}
    var myPoly = new L.Polygon(polygon.data,color);
    mapMarkers.addLayer(myPoly)
  }

  const getRunway = async(e) =>
  {
    const response = await api.post("api/runway",{"airport": airportCode, "lat":lat, "lng":lng, "runway":runway}).then(LoadILS);
  }

  function LoadAllNavaids(data){

    var result = data.data
    const TriangleIcon = new Icon({
      iconUrl: require(".././images/BlueTriangle.png"),
      iconSize: [10,10]
    })
    var num = result.length;
    for (var index = 1; index < num; index++)
    {
      var x = parseFloat(result[index][6]);
      var y = parseFloat(result[index][7]);
      var markerOptions = {icon:TriangleIcon,draggable:false};
      if (((49 < x )&&(x < 54)) && ((-2 < y)&&(y < 2)))
      {
        var toolTipOptions = {content:result[index][2],permanent:true,opacity:1,className:'myTooltip',direction:"bottom"}
        var toolTip = new L.Tooltip(toolTipOptions);
        var newMarker = new L.Marker([x,y],markerOptions);
        newMarker.bindTooltip(toolTip)
        mapMarkers.addLayer(newMarker)
      }
    }
  }

}
export {Reader};