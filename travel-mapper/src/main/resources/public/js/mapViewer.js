/* Loads map when content is loaded */
//const url = contextRoot;

const loadMap = async () => {
    var map = L.map('map').fitWorld();
    var userLocation = null;
    var popup = L.popup();

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        maxZoom: 18
    }).addTo(map);
    /* locate current position: */
    map.locate({ setView: true, maxZoom: 16 });

    loadLocations(map);
    
    /* Function for adding marker on users location */
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
        if (userLocation !== null) {
            map.removeLayer(userLocation);
        }
        popup
            .setLatLng(e.latlng)
            .setContent('Latitude: ' + e.latlng.lat.toString() + '\nLongitude: ' + e.latlng.lng.toString())
            .openOn(map);

        document.getElementById("locationLatitude").value = e.latlng.lat;
        document.getElementById("locationLongitude").value = e.latlng.lng;

        userLocation = L.circle(e.latlng, {
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

/* Write a function for fetching locations here */
async function loadLocations(map) {
    let response = await fetch(url+"locations/all", {
        headers: {
            "Accept": "application/json"
        }
    });
    let locations = await response.json();
    locations.forEach(location => {
        let marker = L.marker([location.latitude, location.longitude]);
        marker.bindPopup(location.name);
        marker.addTo(map);
        L.DomUtil.addClass(marker._icon, 'testi');
    });
}


