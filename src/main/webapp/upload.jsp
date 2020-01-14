
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
        <link rel="stylesheet" href="css/style-update.css" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Nunito&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="container">

            <div class="mainlogo"> ANALIZA KONKURENCJI</div>
            <div class="triangle"> </div>
            <div><img src="css/cropp.jpg" id="cropplogo1" alt="cropp logo" ></div>

            <form action="LoginController" method="POST" >
                <input type="hidden" name="action" value="LOG_OUT" />
                <input class="button" type="submit" value="Wyloguj" />
            </form>

            </div>
            <div class="containerUpload">
                <div class="formUpload" style="line-height:1.5;">
                    Instrukcja:<br/>
                    1. Dodaj plik w formacie .txt z adresami URL do subclass.<br/>
                    2. Ważne: jeden plik -> jeden brand (nie dotyczy krajów).<br/>
                    3. Nazwa pliku musi się zgadzać z nazwą brandu np house.txt<br/>
                    4. Dostępne brandy: reserved, befree, bershka, h&m, house, pull&bear, terranova<br/>
                    5. Zwróć uwagę aby na końcu pliku nie było żadnych spacji ani dodatkowych enterów - zostaną potraktowane jako kolejny adres URL.<br/>
                    6. W przypadku HM należy wkleić link po ówczesnym zaczytaniu wszystkich mdk na stronie. <br/>
                    7. W przypadku Terranovy mogą się zdarzyć mdki bez ceny - spowoduje to błąd. <br/>
                </div>
                <div class="formUpload">
                    <form method="POST" action="FileController" enctype="multipart/form-data">
                        <div style="text-align: center;  margin-top:70px; ">Wybierz plik:<br/>
                            <input style="margin-left: 50px;" type="file" name="uploadFile" />
                            <br/>
                        </div>
                        <input id="submitLogin" style=" margin-left: 150px;"type="submit" value="Upload" />
                    </form>
                    <div class="information" > ${information} </div>
                </div>
            </div>
        </div>
    </body>
</html>
