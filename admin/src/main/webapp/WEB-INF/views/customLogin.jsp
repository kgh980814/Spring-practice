<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Infinity - Bootstrap Admin Template</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
	<meta name="description" content="Admin, Dashboard, Bootstrap" />
	<link rel="shortcut icon" sizes="196x196" href="/admin/resources/assets/images/logo.png">
	
	<link rel="stylesheet" href="/admin/resources/libs/bower/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="/admin/resources/libs/bower/material-design-iconic-font/dist/css/material-design-iconic-font.min.css">
	<link rel="stylesheet" href="/admin/resources/libs/bower/animate.css/animate.min.css">
	<link rel="stylesheet" href="/admin/resources/assets/css/bootstrap.css">
	<link rel="stylesheet" href="/admin/resources/assets/css/core.css">
	<link rel="stylesheet" href="/admin/resources/assets/css/misc-pages.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway:400,500,600,700,800,900,300">
</head>
<body class="simple-page">
	<div id="back-to-home">
		<a href="index.html" class="btn btn-outline btn-default"><i class="fa fa-home animated zoomIn"></i></a>
	</div>
	<div class="simple-page-wrap">
		<div class="simple-page-logo animated swing">
			<a href="index.html">
				<span><i class="fa fa-gg"></i></span>
				<span>ADMIN</span>
			</a>
		</div><!-- logo -->
		<div class="simple-page-form animated flipInY" id="login-form">
	<h4 class="form-title m-b-xl text-center">Sign In With Your Infinity Account</h4>
	<form method="post" action="login">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}"><!-- post로 값을 넘길때마다 csrf값을 넘겨줘야함 -->
		<div class="form-group">
			<input id="sign-in-email" type="text" name="username" class="form-control" placeholder="UserName" value="admin">
		</div>

		<div class="form-group">
			<input id="sign-in-password" type="password" name="password" class="form-control" placeholder="Password" value="admin">
		</div>

		<div class="form-group m-b-xl">
			<div class="checkbox checkbox-primary">
				<input type="checkbox" id="keep_me_logged_in"/>
				<label for="keep_me_logged_in">Keep me signed in</label>
			</div>
		</div>
		
		<input type="submit" class="btn btn-warning" value="SING IN">
	</form>
</div><!-- #login-form -->

<div class="simple-page-footer">
	<p><a href="password-forget.html">FORGOT YOUR PASSWORD ?</a></p>
	<p>
		<small>Don't have an account ?</small>
		<a href="signup.html">CREATE AN ACCOUNT</a>
	</p>
</div><!-- .simple-page-footer -->


	</div><!-- .simple-page-wrap -->
</body>
</html>