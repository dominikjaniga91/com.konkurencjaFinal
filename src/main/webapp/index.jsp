

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Analiza konkurencji CROPP</title>
        <meta   charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Analiza konkurencji CROPP " />
        <meta name="author" content="Dominik Janiga" />
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Nunito&display=swap" rel="stylesheet">

    </head>
    <body style=" background-size: cover;">
        <div class="container">

            <div class="mainlogo"> ANALIZA KONKURENCJI</div>
            <div class="triangle"> </div>
            <div><img src="css/cropp.jpg" alt="cropp logo" id="cropplogo"  ><div>
            </div>
                <div class="formIndex" style="opacity:70%; background-color: #262626;">
                </div>
                <div class="formIndex">
                    <form action="LoginController" method="POST" >
                        <input type="hidden" name="action" value="LOGIN" />
                        <table style="margin-top:40px; margin-left:10px;">
                            <tr>
                                <td><label>Login</label></td>
                                <td><label><input type="text" name="login" ></label></td>
                            </tr>
                            <tr>
                                <td><label>Hasło</label></td>
                                <td><label><input type="password" name="password" ></label></td>
                            </tr>
                        </table>
                        <input id="submitLogin" type="submit" value="Zaloguj" />
                        <div class="information" style="clear: both; text-align: center; color: white; "> ${information} </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
