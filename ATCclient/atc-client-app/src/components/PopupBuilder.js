import { MapContainer, Marker,TileLayer, Popup, useMapEvents } from 'react-leaflet';
import { Icon, LatLng, latLng } from "leaflet";
import "leaflet/dist/leaflet.css";
import "leaflet-rotatedmarker";

function PopupBuilder(type,args)
{
    if (type == "Aircraft")
    {
        return AirPopup(args)
    }
    else if (type == "Navaid")
    {
        return NavPopup(args)
    }

}

function buildAircraft(aircraft)
{
  var speed = (aircraft.speed*1.94384).toPrecision(5);
  var result = "";
  result += "<table><tr><th>Callsign</th><th>Heading</th><th>Speed</th><th>Altitude</th></tr><tr><td>" + aircraft.callsign + "</td><td>" + aircraft.angle.value + "°</td><td>" + speed + "kts</td><td>FL" + (aircraft.height/100) + "</td></tr></table>"
  return result;
}

function AirPopup(args)
{
    // Args[0] = aircraft
    // returns markers
    const airplaneIcon = new Icon({
        iconUrl: require(".././images/GreenPlane.png"),
        iconSize: [38,38]
      })

      var markerOptions = {icon:airplaneIcon,rotationAngle:args[0].angle.value,draggable:false};
      var newMarker = new L.Marker([args[0].xPos,args[0].yPos],markerOptions);
      var aircraftInfo = buildAircraft(args[0]);
      var popupOptions = {content:aircraftInfo,interactive:true};
      var popup = new L.Popup(popupOptions);
      var toolTipOptions = {content:args[0].callsign.toString(),permanent:true,opacity:1,className:'myTooltip',direction:"bottom"}
      var toolTip = new L.Tooltip(toolTipOptions);
      popup.a = args[0].id;
      popup.id = args[0].callsign;
      newMarker.setRotationOrigin('center center');
      newMarker.bindPopup(popup);
      newMarker.bindTooltip(toolTip);
      return newMarker;
}

function NavPopup(args)
{

}

export {PopupBuilder}