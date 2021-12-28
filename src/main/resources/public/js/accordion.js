/* Toggle accordion */
const toggleAccordion = () => {
  let mapFooter = document.getElementsByClassName("add-location-footer")[0];
  let panel = document.getElementById("panelForm");
  if (panel.style.display === "block") {
    mapFooter.style.borderBottom = "0.1rem";
    mapFooter.style.borderRadius = "0 0 0.25rem 0.25rem";
    panel.style.display = "none";
  } else {
    panel.style.display = "block";
    mapFooter.style.borderRadius = "0";
    mapFooter.style.borderBottom = "none";

  }

};