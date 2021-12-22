/* Loads map when content is loaded */
const loadMap = async () => {
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
            /* Remove circle if exists */
            if (circle !== null) {
                mymap.removeLayer(circle);
            }
            /* Add pop up on click */
            popup
                .setLatLng(e.latlng)
                //display lat and long:
                .setContent('Latitude: ' + e.latlng.lat.toString() + '\nLongitude: ' + e.latlng.lng.toString())
                .openOn(mymap);
            /* Set location info on iput on click: */
            document.getElementById("locationLatitude").value = e.latlng.lat;
            document.getElementById("locationLongitude").value = e.latlng.lng;

            /* Add circle on click */
            circle = L.circle(e.latlng, {
                color: 'red',
                fillColor: '#f03',
                fillOpacity: 0.5,
                radius: 10
            }).addTo(mymap);
            
        }

        mymap.on('click', onMapClick);

    });

};


