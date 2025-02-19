<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UFO Challenge</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

    <div class="info-box">
        <h4>User Information</h4>
        <p>Username: <span id="username">${traveler.getName()}</span></p>
        <p>Games Won: <span id="games-won">${win}</span></p>
        <p>Games Lost: <span id="games-lost">${defeat}</span></p>
        <p>Total Games: <span id="total-games">${full}</span></p>
    </div>
    <div class="container mt-5 text-center">

        <h1 class="mb-4">${head}<br>${question}</h1>

        <form action="continue" method="post" class="mt-3" onsubmit="return validateForm()">
            <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
              <input type="radio" class="btn-check" name="btnradio" id="btnradio1"  value="${accept}" autocomplete="off" >
              <label class="btn btn-outline-primary" for="btnradio1">${accept}</label>

              <input type="radio" class="btn-check" name="btnradio" id="btnradio2"  value="${reject}" autocomplete="off">
              <label class="btn btn-outline-primary" for="btnradio2">${reject}</label>
            </div>
            <div>
                <button type="submit" class="btn btn-dark mt-3">Submit</button>
            </div>
        </form>
    </div>
    <script>
        function validateForm() {
            var radios = document.getElementsByName('btnradio');
            var formValid = false;

            var i = 0;
            while (!formValid && i < radios.length) {
                if (radios[i].checked) formValid = true;
                i++;
            }

            if (!formValid) alert("Please select an option.");
            return formValid;
        }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
