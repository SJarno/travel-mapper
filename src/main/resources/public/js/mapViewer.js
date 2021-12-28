/* Loads map when content is loaded */
let map = null;
/* console.log(map); */

const loadMap = async () => {
    map = L.map('map').fitWorld();
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
    let response = await fetch(url + "locations/all", {
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

/*  */
async function addLocation(event, form) {
    let response = fetch(form.action, {
        method: 'post',
        body: new FormData(form)
    });
    event.preventDefault();
    let data = (await response);
    console.log("Vastaus: "+data.status);
    /* console.log((await response).json()); */
    if ((await response).status === 201) {
        loadLocations(map);
        console.log(data);
        displaySuccess("Sijainti lisätty!")
    } else {
        console.log(data.headers);
        displayError(data.headers.get("error"));
    }
   

}

/* Write a function on success */
async function displaySuccess(successMessage) {
    let alertDiv = document.querySelector("#generic-success")
    
    /* alertDiv.style.marginTop = margin+"rem"; */
    alertDiv.style.display = "flex";
    let errorContainer = alertDiv.querySelector(".messageContainer");
    errorContainer.innerHTML = successMessage;
}

/* Write a function on error */
async function displayError(errorMessage) {
    let alertDiv = document.querySelector("#generic-error")
    
    /* alertDiv.style.marginTop = margin+"rem"; */
    alertDiv.style.display = "flex";
    let errorContainer = alertDiv.querySelector(".messageContainer");
    errorContainer.innerHTML = errorMessage;
    
}

