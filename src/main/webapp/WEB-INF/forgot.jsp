<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Find Password</title>
    </head>
    <body>
        <h1>Please enter the email address to retrieve the password.</h1>
        <form action="forgot" method="post">
            <label>Email Address: </label>
            <input type="email" id="email" name="email">
            <br>
            <input type="submit" value="Submit">
        </form>
        <p>${message}</p>
        <a href="login">Back to Login</a>
    </body>
</html>
