<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

if(session.getAttribute("username")!=null)
    response.sendRedirect("user.jsp");
%>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <title>Login page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
       
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resources/fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resources/vendor/animate/animate.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resources/vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resources/vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resources/vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resources/vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="resources/css/util.css">
        <link rel="stylesheet" type="text/css" href="resources/css/main.css">
        <!--===============================================================================================-->
    </head>

    <body>

        <div class="limiter">
            <div class="container-login100" style="background-image: url('resources/images/bg-01.jpg');">
                <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
                    <form class="login100-form validate-form" method="post" action="login">
                        <span class="login100-form-title p-b-49">
						Login
					</span>

                        <div class="wrap-input100 validate-input m-b-23" data-validate="Username is reauired">
                            <span class="label-input100">Username</span>
                            <input class="input100" type="email" name="email" placeholder="Type your Email">
                            <span class="focus-input100" data-symbol="&#xf206;"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Password is required">
                            <span class="label-input100">Password</span>
                            <input class="input100" type="password" name="password" placeholder="Type your password">
                            <span class="focus-input100" data-symbol="&#xf190;"></span>
                        </div>

                        <div class="text-right p-t-8 p-b-31">
                            <a href="forgetpassword.jsp">
							Forgot password?
						</a>
                        </div>

                        <div class="container-login100-form-btn">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="login100-form-btn">
								Login
							</button>
                            </div>
                        </div>



                        <div class="flex-col-c p-t-50">
                            <span class="txt1 p-b-17">
							Or Sign Up Using
						</span>

                            <a href="Register.jsp" class="txt2">
							Sign Up
						</a>
                        </div>


                        <div class="flex-c-m p-t-30">
                            <a href="#" class="login100-social-item bg1">
                                <i class="fa fa-facebook"></i>
                            </a>

                            <a href="#" class="login100-social-item bg2">
                                <i class="fa fa-twitter"></i>
                            </a>

                            <a href="#" class="login100-social-item bg3">
                                <i class="fa fa-google"></i>
                            </a>
                        </div>

                    </form>
                </div>
            </div>
        </div>


        <div id="dropDownSelect1"></div>

        <!--===============================================================================================-->
        <script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="resources/vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="resources/vendor/bootstrap/js/popper.js"></script>
        <script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="resources/vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="resources/vendor/daterangepicker/moment.min.js"></script>
        <script src="resources/vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="resources/vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->
        <script src="resources/js/main.js"></script>
		<script src="resources/js/forgotpassword.js"></script>
    </body>

    </html>