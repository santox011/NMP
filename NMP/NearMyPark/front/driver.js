let userLat = 0;
let userLng = 0;

function getLocation() {
    navigator.geolocation.getCurrentPosition(position => {
        userLat = position.coords.latitude;
        userLng = position.coords.longitude;

        loadParking();
    });
}

function loadParking() {
    fetch(BASE_URL + "/parking/all")
        .then(res => res.json())
        .then(data => displayParking(data));
}

function displayParking(list) {
    let container = document.getElementById("parkingList");
    container.innerHTML = "";

    list.forEach(p => {
        let distance = calculateDistance(
            userLat,
            userLng,
            p.latitude,
            p.longitude
        );

        let div = document.createElement("div");
        div.className = "parking-card";

        div.innerHTML = `
            <h3>${p.locationName}</h3>
            <p>Distance: ${distance.toFixed(2)} km</p>
            <button onclick="contactOwner(${p.parkingId})">
                Contact Owner
            </button>
        `;

        container.appendChild(div);
    });
}
function calculateDistance(lat1, lon1, lat2, lon2) {
    const R = 6371;

    let dLat = (lat2 - lat1) * Math.PI / 180;
    let dLon = (lon2 - lon1) * Math.PI / 180;

    let a =
        Math.sin(dLat/2) * Math.sin(dLat/2) +
        Math.cos(lat1 * Math.PI/180) *
        Math.cos(lat2 * Math.PI/180) *
        Math.sin(dLon/2) * Math.sin(dLon/2);

    let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

    return R * c;
}
function contactOwner(parkingId) {
    fetch(BASE_URL + "/contact/request/" + parkingId, {
        method: "POST"
    })
    .then(res => res.json())
    .then(data => {
        alert("Call this number: " + data.maskedNumber);
    });
}