const closeAlert = () => {
    let elementsToClose = document.getElementsByClassName("alert");
    /* let elementIdToClose = document.getElementById("generic"); */
    for (let index = 0; index < elementsToClose.length; index++) {
        elementsToClose[index].style.display = "none";
        console.log(elementsToClose[index]);
        
    }
    
};