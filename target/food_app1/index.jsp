<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Food App</title>
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
        h1, h2 {
            text-align: center;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-bottom: 20px;
            text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.7); /* Text shadow for depth */
        }
        .container {
            max-width: 600px;
            padding: 30px;
            border-radius: 10px;
            background: linear-gradient(135deg, #2a2a45, #1e1e2f); /* Gradient background */
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5); /* Box shadow for depth */
        }
        .btn {
            border-radius: 25px; /* Rounded corners */
            padding: 10px 20px; /* Adjust padding */
            font-size: 1.2rem; /* Increase font size */
            transition: all 0.3s ease; /* Smooth transition */
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2); /* Button shadow */
        }
        .btn-primary {
            background-color: #ff4081; /* Bright pink */
            border: none;
        }
        .btn-secondary {
            background-color: #29b6f6; /* Bright blue */
            border: none;
        }
        .btn:hover {
            transform: translateY(-3px); /* Lift effect */
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.3); /* Enhanced shadow on hover */
        }
        .footer {
            text-align: center;
            margin-top: 30px;
            color: #cccccc; /* Light gray */
        }
    </style>
</head>
<body>
    <div class="container text-center">
        <h1>Welcome to the Food App</h1>
        
        <div class="mt-4">
            <h2>User Registration</h2>
            <!-- Register Button -->
            <a href="register.jsp" class="btn btn-primary btn-lg">Register Here</a>
        </div>

        <div class="mt-4">
            <h2>Already Registered?</h2>
            <!-- Login Button -->
            <a href="login.jsp" class="btn btn-secondary btn-lg">Login</a>
        </div>
        
        <div class="footer mt-5">
            <p>&copy; 2024 Food App. All rights reserved.</p>
        </div>
    </div>

    <!-- Adding Bootstrap JS for interactivity -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
