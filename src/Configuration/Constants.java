/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuration;

/**
 *
 * @author ASUS
 */
public class Constants {
    
    public static String EVENT_PICTURES_PATH="C:\\Users\\hp\\OneDrive\\Bureau\\GestionUser\\src\\Pictures\\Events\\";
    public static String EVENT_ICONS_PATH="C:\\Users\\hp\\OneDrive\\Bureau\\GestionUser\\src\\Icons\\";
    
    public static String setMapData(double Longitude,double Latitude,String Title){
        return "<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <meta charset=\"utf-8\">\n" +
"        <!-- Nous chargeons les fichiers CDN de Leaflet. Le CSS AVANT le JS -->\n" +
"        <link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet@1.3.1/dist/leaflet.css\" integrity=\"sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ==\" crossorigin=\"\" />\n" +
"        <style type=\"text/css\">\n" +
"            #map{ /* la carte DOIT avoir une hauteur sinon elle n'apparaît pas */\n" +
"                height:400px;\n" +
"            }\n" +
"        </style>\n" +
"        <title>Carte</title>\n" +
"    </head>\n" +
"    <body>\n" +
"        <div id=\"map\">\n" +
"	    <!-- Ici s'affichera la carte -->\n" +
"	</div>\n" +
"\n" +
"        <!-- Fichiers Javascript -->\n" +
"        <script src=\"https://unpkg.com/leaflet@1.3.1/dist/leaflet.js\" integrity=\"sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw==\" crossorigin=\"\"></script>\n" +
"	<script type=\"text/javascript\">\n" +
"            // On initialise la latitude et la longitude de Paris (centre de la carte)\n" +
"            var lat ="+String.valueOf(Latitude)+";" +
"            var lon = "+String.valueOf(Longitude)+";" +
"            var macarte = null;\n" +
"            // Fonction d'initialisation de la carte\n" +
"            function initMap() {\n" +
"                // Créer l'objet \"macarte\" et l'insèrer dans l'élément HTML qui a l'ID \"map\"\n" +
"                macarte = L.map('map').setView([lat, lon], 11);\n" +
"                // Leaflet ne récupère pas les cartes (tiles) sur un serveur par défaut. Nous devons lui préciser où nous souhaitons les récupérer. Ici, openstreetmap.fr\n" +
"                L.tileLayer('https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png', {\n" +

"                    attribution: '',\n" +
"                    minZoom: 1,\n" +
"                    maxZoom: 20\n" +
"                }).addTo(macarte);\n" +
                  "var marker = L.marker(["+Latitude+","+Longitude+" ]).addTo(macarte); \n"+
                "marker.bindPopup('"+Title+"'); \n"+
"            }\n" +
              
"            window.onload = function(){\n" +
"		// Fonction d'initialisation qui s'exécute lorsque le DOM est chargé\n" +
"		initMap(); \n" +
"            };\n" +
"        </script>\n" +
"    </body>\n" +
"</html>";
        
        
        
        
    }
    
      public static String setMapEmpty(){
        return "<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <meta charset=\"utf-8\">\n" +
"        <!-- Nous chargeons les fichiers CDN de Leaflet. Le CSS AVANT le JS -->\n" +
"        <link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet@1.3.1/dist/leaflet.css\" integrity=\"sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ==\" crossorigin=\"\" />\n" +
"        <style type=\"text/css\">\n" +
"            #map{ /* la carte DOIT avoir une hauteur sinon elle n'apparaît pas */\n" +
"                height:400px;\n" +
"            }\n" +
"        </style>\n" +
"        <title>Carte</title>\n" +
"    </head>\n" +
"    <body>\n" +
"	<input style=\"display:none\" type=\"text\" id=\"javagetlatlng\" value=\"Mickey\">\n" +
"        <div id=\"map\">\n" +
"	    <!-- Ici s'affichera la carte -->\n" +
"	</div>\n" +
"\n" +
"        <!-- Fichiers Javascript -->\n" +
"        <script src=\"https://unpkg.com/leaflet@1.3.1/dist/leaflet.js\" integrity=\"sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw==\" crossorigin=\"\"></script>\n" +
"	<script type=\"text/javascript\">\n" +
"            // On initialise la latitude et la longitude de Paris (centre de la carte)\n" +
"            var lat = 48.852969;\n" +
"            var lon = 2.349903;\n" +
"            var macarte = null;\n" +
"			var oldMarker=null;\n" +
"			var marker=null;\n" +
"			var markers=[];\n" +
"            // Fonction d'initialisation de la carte\n" +
"            function initMap() {\n" +
"                // Créer l'objet \"macarte\" et l'insèrer dans l'élément HTML qui a l'ID \"map\"\n" +
"                macarte = L.map('map').setView([lat, lon], 11);\n" +
"                // Leaflet ne récupère pas les cartes (tiles) sur un serveur par défaut. Nous devons lui préciser où nous souhaitons les récupérer. Ici, openstreetmap.fr\n" +
"                L.tileLayer('https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png', {\n" +
"                    // Il est toujours bien de laisser le lien vers la source des données\n" +
"                    attribution: 'données © <a href=\"//osm.org/copyright\">OpenStreetMap</a>/ODbL - rendu <a href=\"//openstreetmap.fr\">OSM France</a>',\n" +
"                    minZoom: 1,\n" +
"                    maxZoom: 20\n" +
"                }).addTo(macarte);\n" +
"\n" +
"             macarte.on('click', onMapClick);\n" +
"  }\n" +
"\n" +
"/*\n" +
"  function onMapClick(e) {\n" +
"    alert(\"You clicked the map at \" + e.latlng);\n" +
"}\n" +
"  \n" +
"*/\n" +
" function onMapClick(e) {\n" +
"               // alert('clicked'+ e.latlng.toString()); \n" +
"      //  popup.setLatLng(e.latlng).setContent('You clicked the map at ' + e.latlng.toString()).openOn(macarte);\n" +
"\n" +
"markers.forea\n" +
"if(markers.length>0){\n" +
"markers.forEach(element => element.remove()	);\n" +
"}\n" +
"\n" +
"        marker = L.marker(e.latlng).addTo(macarte);\n" +
"		\n" +
"		markers.push(marker);\n" +
"		  document.getElementById('javagetlatlng').value =e.latlng.toString() ;\n" +
"		  var position=		  document.getElementById('javagetlatlng').value;\n" +
"		  position.replace('(', position);\n" +
"		  position.replace(')', position);\n" +
"		  oldMarker=marker;\n" +
                "alert(e.latlng); \n"+
"\n" +
"\n" +
"    }\n" +
"\n" +
"            window.onload = function(){\n" +
"		// Fonction d'initialisation qui s'exécute lorsque le DOM est chargé\n" +
"		initMap(); \n" +
"            };\n" +
"        </script>\n" +
"    </body>\n" +
"</html>";
        
        
        
        
    }
    
}
