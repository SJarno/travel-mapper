const url = contextRoot;

/* Load fragments  */
async function loadFragment(path) {
    let response = await fetch(url+path, {
        headers: {
            "Accept": "text/html"
        }
    });
    let content = await response.text();
    const div = document.getElementById("main-content-fragment");
    div.innerHTML = content; //toimii
    if (path === "map-page") {
        loadMap();
    }
};