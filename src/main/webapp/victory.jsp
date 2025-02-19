<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Defeat page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body >
<div class="info-box">
    <h4>User Information</h4>
    <p>Username: <span id="username">${traveler.getName()}</span></p>
    <p>Games Won: <span id="games-won">${traveler.game.getCountVictory()}</span></p>
    <p>Games Lost: <span id="games-lost">${traveler.game.getCountDefeat()}</span></p>
    <p>Total Games: <span id="total-games">${traveler.game.getTotalGames()}</span></p>
</div>
<div class="container mt-5 text-center">
    <h1>YOU WIN </h1>
    <h2>Congratulations ${username}</h2>
    <div class="d-grid gap-2 col-6 mx-auto">
      <button class="btn btn-primary" type="button" onclick="redirectToHome()">Restart</button>
    </div>
</div>
<script>
   function redirectToHome() {
       window.location.href = "continue";
   }
</script>
</body>
</html>
