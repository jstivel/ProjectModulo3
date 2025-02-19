<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome Traveler</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <!-- <link rel="stylesheet" href="css/styles.css"> -->
</head>
<body>
    <div class="container mt-5 text-center">
        <h1 class="mb-4">Introduction</h1>

        <p>The game is set in the distant future – the year 3018, a time when humans share the Earth with robots and space travel is routine.</p>

        <h2>Meet the Crew</h2>
        <p>My name is John Squirrels, captain of the Galactic Rush.</p>
        <p>My name is Eleanor Carrey. You can call me Ellie. I'm the navigator here on the Galactic Rush.</p>

        <h2>Say, traveler, what is your name?</h2>
        <form name="travelerForm" action="start" method="post" class="mt-3" onsubmit="return validateForm()">
            <div class="mx-auto p-2" style="width: 200px;">
                <input type="text" name="traveler_name" class="form-control" placeholder="Enter your name" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
    <script>
            function validateForm() {
                var name = document.forms["travelerForm"]["traveler_name"].value;
                if (name == null || name.trim() === "") {
                    alert("Please enter your name.");
                    return false;
                }
                return true;
            }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
