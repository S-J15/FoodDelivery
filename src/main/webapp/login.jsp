<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Food App</title>
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
            max-width: 400px; /* Max width for container */
            padding: 30px;
            border-radius: 10px;
            background: linear-gradient(135deg, #2a2a45, #1e1e2f); /* Gradient background */
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5); /* Box shadow for depth */
        }
        h2 {
            text-align: center;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-bottom: 20px;
            text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.7); /* Text shadow for depth */
        }
        label {
            font-weight: bold; /* Bold labels */
        }
        .form-control {
            background-color: #2a2a45; /* Input background */
            color: #f8f9fa; /* Input text color */
            border: 1px solid #ccc; /* Light border */
        }
        .form-control::placeholder {
            color: #cccccc; /* Placeholder text color */
        }
        button {
            width: 100%; /* Full width button */
            border-radius: 25px; /* Rounded corners */
            padding: 10px; /* Padding for button */
            font-size: 1.2rem; /* Increase font size */
            background-color: #ff4081; /* Bright pink */
            border: none;
            color: white; /* White text */
            transition: all 0.3s ease; /* Smooth transition */
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2); /* Button shadow */
        }
        button:hover {
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
    <div class="container">
        <h2>Login</h2>
        <form action="login" method="post">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
        <br>
        <div class="footer">
            <a href="register.jsp" class="text-light">Don't have an account? Register</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
