<%-- 
    IT15086730
    M.M.S.U.Mahagedara
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="bootstrap.min.css">
        <title>Home</title>

        <style type="text/css">
            .profile-form {
                width: 600px;
                margin: 50px auto;
            }
            .profile-form form {
                margin-bottom: 20px;
                background: #006666;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 40px;
                border-radius: 20px;
                background-image: url("/w3images/photographer.jpg");
            }
            .profile-form h1 {
                margin: 0 0 35px;
            }
            .form-control, .btn {
                min-height: 40px;
                border-radius: 4px;
            }
            .btn {        
                font-size: 15px;
                font-weight: bold;
                border-radius: 15px;
                background-color:#0066cc;
                align-self: center;
            }
        </style>

    </head>
    <body>        
        <div class="profile-form">
            <form action="Validator" method="POST">
                <h1 class="text-center">Enter Your Details</h2>       
                <div class="form-group">
                    <input type="text" name="id" placeholder="Enter ID" class="form-control">
                </div>
                <div class="form-group">
                    <input type="password" name="key" placeholder="Enter Password" class="form-control">
                </div>
                <div class="form-group">
                    <input type="submit"  class="btn btn-primary btn-block" value="Submit"/>
                </div>
                <input type="hidden" name="tokentext" id="tokentext"/>
            </form>
          
        </div>

        <script src="jquery.min.js"></script>
        <script src="bootstrap.min.js"></script>
        <script>
            $.ajax({
                type: 'GET',
                dataType: 'json',
                url: '/SynchronizerTokenPattern/Validator',
                async: false,
                contentType: "application/json",
                success: function (data) {
                    var response = JSON.stringify(data);
                    console.log("data.csrfToken: " + data.csrfToken);
                    
                    console.log("response   " + response[0].csrfToken);
                    alert("Displaying Token Data Here: " + response);
                    $("#tokentext").val(data.csrfToken);
                },
                error: function (xhr, status, error) {
                    alert(status);
                }
            });
        </script>
    </body>
</html>