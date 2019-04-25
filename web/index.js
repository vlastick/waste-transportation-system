import Map from 'ol/Map.js';
import View from 'ol/View.js';
import {Draw} from 'ol/interaction.js';
import {Tile as TileLayer, Vector as VectorLayer, Layer} from 'ol/layer.js';
import {OSM, Vector as VectorSource} from 'ol/source.js';
import {fromLonLat, toLonLat} from 'ol/proj.js';
import Feature from 'ol/Feature.js';
import geom from 'ol/geom.js';
import style from 'ol/style.js';

var raster = new TileLayer({
    source: new OSM()
});

var source = new VectorSource({wrapX: false});

var vector = new VectorLayer({
    source: source
});


var map = new Map({
    layers: [raster, vector],
    target: 'map',
        view: new View({
        center: fromLonLat([30.0495, 61.2302]),
        zoom: 13
    })
});


var draw; // global so we can remove it later
function addInteraction() {
    draw = new Draw({
        source: source,
        type: "Point"
    });
    map.addInteraction(draw);
}

/**
* Handle change event.
*/

addInteraction();


/**
* Click handler to create point
*/
var pointSelect = document.getElementById('pointType');
var longField   = document.getElementById('long');
var latField    = document.getElementById('lat');
var descriptionField  = document.getElementById('pointDescription');
map.on('click', function(evt) {
    var type = pointSelect.value;
    if (type !== 'None') {
        var coordinate = toLonLat(evt.coordinate);
        longField.value = coordinate[0];
        latField.value  = coordinate[1];
    }
});


var size = new OpenLayers.Size(21, 25);
var offset = new OpenLayers.Pixel(-(size.w / 2), -size.h);
var icon = new OpenLayers.Icon('http://www.ifmo.ru/images/logo.png', size, offset);

var lonLat = new OpenLayers.LonLat( -0.1279688 ,51.5077286 )
          .transform(
            new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
            map.getProjectionObject() // to Spherical Mercator Projection
          );



var iconFeatures=[];

var iconFeature = new Feature({
  geometry: new geom.Point(fromLonLat([-72.0704, 46.678])),
  name: 'Null Island',
  population: 4000,
  rainfall: 500
});

var iconFeature1 = new ol.Feature({
  geometry: new geom.Point(fromLonLat([-72.0704, 46.678])),
  name: 'Null Island Two',
  population: 4001,
  rainfall: 501
});

iconFeatures.push(iconFeature);
iconFeatures.push(iconFeature1);

var vectorSource = new Vector({
  features: iconFeatures //add an array of features
});


var vectorLayer = new VectorLayer({
  source: vectorSource,
});