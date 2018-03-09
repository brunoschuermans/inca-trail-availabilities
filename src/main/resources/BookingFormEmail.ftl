<html>
<body>
<h1>Booking Form Request</h1>
<h2>Info Booking</h2>
<p>
    <b>Point of contact: </b>${email}
</p>
<p>
    <b>Tour start date: </b>${tourStartDate}
</p>
<p>
    <b>Private/Group: </b>${tourType?capitalize}
</p>
<p>
    <b>Tour: </b>${tour.description}
</p>
<p>
    <b>Hotel: </b>${hotel}
</p>
<p>
    <b>Food restriction: </b>${foodRestriction!""}
</p>
<p>
    <b>Other information: </b>${anyInformation!""}
</p>
<p>
    <b>Know us from: </b>${knowUsFrom?capitalize}
</p>
<h2>Info Traveller(s) [${travellerForms?size}]</h2>
<#list travellerForms as t>
<p>
    <b>First name: </b>${t.firstName}
</p>
<p>
    <b>Last name: </b>${t.lastName}
</p>
<p>
    <b>Passport: </b>${t.passport}
</p>
<p>
    <b>Nationality: </b>${t.nationality}
</p>
<p>
    <b>Birth date: </b>${t.birthDate}
</p>
<hr/>
</#list>
</body>
</html>