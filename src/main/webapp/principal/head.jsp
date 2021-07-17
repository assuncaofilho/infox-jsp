<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    
<head>
    <title>Curso JSP - JDev Treinamento</title>
    <!-- HTML5 Shim and Respond.js IE10 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 10]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
      <!-- Meta -->
      <meta charset="<ISO-8859-1">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="description" content="Projeto de estudos feito em JSP - Info-X." />
      <meta name="author" content="Marco Assuncao" />
      <!-- Favicon icon -->
      <link rel="icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico" type="image/x-icon">
    <!-- Google font-->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,500" rel="stylesheet">
    <!-- waves.css -->
    <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/pages/waves/css/waves.min.css" type="text/css" media="all">
      <!-- Required Fremwork -->
      <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/assets/css/bootstrap/css/bootstrap.min.css">
      <!-- waves.css -->
      <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/pages/waves/css/waves.min.css" type="text/css" media="all">
      <!-- themify icon -->
      <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/assets/icon/themify-icons/themify-icons.css">
      <!-- Font Awesome -->
      <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/assets/icon/font-awesome/css/font-awesome.min.css">
      <!-- scrollbar.css -->
      <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/assets/css/jquery.mCustomScrollbar.css">
        <!-- am chart export.css -->
        <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
      <!-- Style.css -->
      <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/assets/css/style.css">
      
      <style type="text/css">
      
      table#tbusuResult th#ver {
      color: blue;
      }
      
      div#exampleModalUsuario span#msgResult {
      color:red;
      font-size: 8pt;
      margin-bottom: 1px;
      margin-left: 4px;
      
      }
      
      form#formUser label {
      color: #0d1260;
      font-style: italic;
      }
      
      span#alertaAuth {
      color: red;
      font-size: 10pt;
      
      }
      
      
      </style>
  </head>
    