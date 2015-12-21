<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>TicketMaster</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="bootstrap/css/jumbotron-narrow.css" rel="stylesheet">
    
    <!-- Custom styles for this project -->
    <link href="bootstrap/css/ticketCSS.css" rel="stylesheet">

  
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
	  
    <div class="container">
        <div class="header clearfix">
            <a href="index.html"><h3 class="text-muted">Ticket Master</h3></a>
        </div>
      <div class="jumbotron">
          <div class="row">
              <div class="col-md-6">
                  <img src="assets/img/logo.png"  class="img-responsive"/>
              </div>
              <div class="col-md-6">
                  <form action="FrontController" method="post">
                     <div class="form-group">
                        <input type="text" name="login" class="form-control" id="exampleInputEmail3" placeholder="Usuario">
                        <input type="hidden" name="servlet" value="login">
                      </div>
                      <button type="submit" class="btn btn-default" value="Autenticar"> Logar</button>
                  </form>
              </div>
          </div>
        
      </div>

      <footer class="footer">
        <p>&copy; TicketMaster 2015</p>
      </footer>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>