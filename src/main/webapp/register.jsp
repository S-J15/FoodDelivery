<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration - Food App</title>
    <!-- Adding Bootstrap CSS for styling -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            background-color: #1e1e2f; /* Dark background */
            color: #f8f9fa; /* Light text color */
            font-family: 'Arial', sans-serif;
            height: 100vh; /* Full height */
            display: flex; /* Flexbox for centering */
            justify-content: center; /* Center horizontally */
            align-items: center; /* Center vertically */
            margin: 0; /* Remove default margin */
        }
        .container {
            max-width: 500px; /* Max width for container */
            padding: 30px;
            border-radius: 10px;
            background: linear-gradient(135deg, #2a2a45, #1e1e2f); /* Gradient background */
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5); /* Box shadow for depth */
        }
        h1 {
            text-align: center;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-bottom: 20px;
            text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.7); /* Text shadow for depth */
        }
        label {
            font-weight: bold; /* Bold labels */
        }
        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%; /* Full width */
            padding: 10px;
            margin-bottom: 20px; /* Space between inputs */
            border-radius: 5px; /* Rounded corners */
            border: 1px solid #ccc; /* Light border */
            background-color: #2a2a45; /* Input background */
            color: #f8f9fa; /* Input text color */
            transition: border-color 0.3s ease; /* Transition for border color */
        }
        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="password"]:focus {
            border-color: #ff4081; /* Change border color on focus */
            outline: none; /* Remove default outline */
        }
        input[type="submit"], .btn-secondary {
            border-radius: 25px; /* Rounded corners */
            padding: 10px 20px; /* Padding for button */
            font-size: 1.2rem; /* Increase font size */
            background-color: #ff4081; /* Bright pink for register button */
            border: none;
            color: white; /* White text */
            transition: all 0.3s ease; /* Smooth transition */
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2); /* Button shadow */
            display: block; /* Block display for symmetry */
            margin: 10px auto; /* Center buttons */
            width: 80%; /* Set button width */
        }
        input[type="submit"]:hover,
        .btn-secondary:hover {
            transform: translateY(-3px); /* Lift effect */
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.3); /* Enhanced shadow on hover */
            background-color: #e91e63; /* Darker pink on hover */
        }
        .footer {
            text-align: center;
            margin-top: 30px;
            color: #cccccc; /* Light gray */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>User Registration</h1>
        <form action="register" method="post">
            <label for="email">Email:</label>
            <input type="email" name="email" required>

            <label for="password">Password:</label>
            <input type="password" name="password" required>

            <label for="name">Name:</label>
            <input type="text" name="name" required>

            <label for="contact_number">Contact Number:</label>
            <input type="text" name="contact_number" required>

            <input type="submit" value="Register">
        </form>
        <a href="index.jsp" class="btn btn-secondary">Go back to home</a>
    </div>

    <!-- Adding Bootstrap JS for interactivity -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
