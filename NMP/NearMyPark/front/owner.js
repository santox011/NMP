function addParking() {

    let data = {
        locationName: document.getElementById("name").value,
        latitude: parseFloat(document.getElementById("lat").value),
        longitude: parseFloat(document.getElementById("lng").value),
        address: document.getElementById("address").value,
        ownerId: 1
    };

    fetch(BASE_URL + "/parking/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(() => alert("Parking Added Successfully"));
}
