/* Loads map when content is loaded */
const loadMap = async () => {
    navigator.geolocation.getCurrentPosition(function (location) {
        var latlng = new L.LatLng(location.coords.latitude, location.coords.longitude);

        var mymap = L.map('map').setView(latlng, 13)
        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; OpenStreetMap',
            maxZoom: 18,
        }).addTo(mymap);

        var marker = L.marker(latlng).addTo(mymap);
        marker.bindPopup("Nykyinen sijainti");

        var popup = L.popup();
            

        function onMapClick(e) {
            popup
                .setLatLng(e.latlng)
                //display lat and long:
                .setContent('Latitude: ' + e.latlng.lat.toString()+'\nLongitude: '+e.latlng.lng.toString())
                .openOn(mymap);
        }

        mymap.on('click', onMapClick);

    });

};


