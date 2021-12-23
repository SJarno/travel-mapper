/* Loads map when content is loaded */
/* const loadMap = async () => {

    navigator.geolocation.getCurrentPosition(function (location) {
        var latlng = new L.LatLng(location.coords.latitude, location.coords.longitude);
        var circle = null;
        var mymap = L.map('map').setView(latlng, 13)
        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; OpenStreetMap',
            maxZoom: 18,
        }).addTo(mymap);

        var marker = L.marker(latlng).addTo(mymap);
        marker.bindPopup("Nykyinen sijainti");

        var popup = L.popup();

        function onMapClick(e) {
            if (circle !== null) {
                mymap.removeLayer(circle);
            }
            popup
                .setLatLng(e.latlng)
                .setContent('Latitude: ' + e.latlng.lat.toString() + '\nLongitude: ' + e.latlng.lng.toString())
                .openOn(mymap);

            document.getElementById("locationLatitude").value = e.latlng.lat;
            document.getElementById("locationLongitude").value = e.latlng.lng;

            circle = L.circle(e.latlng, {
                color: 'red',
                fillColor: '#f03',
                fillOpacity: 0.5,
                radius: 10
            }).addTo(mymap);
            
        }

        mymap.on('click', onMapClick);

    });

}; */

const loadMap = async () => {
    var map = L.map('map').fitWorld();
    var circle = null;
    var popup = L.popup();

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        maxZoom: 18
    }).addTo(map);
    /* locate current position: */
    map.locate({ setView: true, maxZoom: 16 });
    
    function onLocationFound(e) {
        var radius = e.accuracy;
        L.marker(e.latlng).addTo(map)
            .bindPopup("You are within " + radius + " meters from this point").openPopup();
    
        L.circle(e.latlng, radius).addTo(map);

        /* var marker = L.marker(e.latlng).addTo(map);
        marker.bindPopup("Nykyinen sijainti"); */
    }
    
    function onLocationError(e) {
        alert(e.message);
    }
    function onMapClick(e) {
        if (circle !== null) {
            map.removeLayer(circle);
        }
        popup
            .setLatLng(e.latlng)
            .setContent('Latitude: ' + e.latlng.lat.toString() + '\nLongitude: ' + e.latlng.lng.toString())
            .openOn(map);

        document.getElementById("locationLatitude").value = e.latlng.lat;
        document.getElementById("locationLongitude").value = e.latlng.lng;

        circle = L.circle(e.latlng, {
            color: 'red',
            fillColor: '#f03',
            fillOpacity: 0.5,
            radius: 10
        }).addTo(map);
        
    }
    map.on('locationfound', onLocationFound);
    map.on('locationerror', onLocationError);
    map.on('click', onMapClick);
};

const getCurrentPosition = async (map) => {
    map.locate({ setView: true, maxZoom: 16 });
};


