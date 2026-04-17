function initMap() {
    let map = new google.maps.Map(document.getElementById("map"), {
        zoom: 12,
        center: { lat: userLat, lng: userLng }
    });

    new google.maps.Marker({
        position: { lat: userLat, lng: userLng },
        map: map,
        title: "You"
    });
}
<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY&callback=initMap" async defer></script>
