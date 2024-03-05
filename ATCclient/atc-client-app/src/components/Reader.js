import * as Papa from "papaparse";
import airport from '.././airports.csv';
import navaid from '.././navaids.csv';
import { Icon, LatLng, latLng } from "leaflet";
import { MapContainer, Marker,TileLayer, Popup, useMapEvents } from 'react-leaflet';
import "leaflet/dist/leaflet.css";
import "leaflet-rotatedmarker";

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
          var polygon = [[x,y],[52.13240603,0.51309916],[52.09697357,0.5810654]]
          const color = {color: "red", opacity:0.1}
          if (result[index][1] == "EGSS")
          {
            var myPoly = new L.Polygon(polygon,color);
            mapMarkers.addLayer(myPoly)
          }
        }
      }
    }
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

}
export {Reader};