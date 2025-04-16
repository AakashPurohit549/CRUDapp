<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Management</title>
                  
</head>
<body>
  <body>

           <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a class="navbar-brand"> User Management App </a>
                    </div>
                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
                    </ul>
                </nav>
            </header>
            <br>          
                  
                   <div class="conatiner col-md5"> 
                   
                       <div class="card">
                         
                          <div class="card-body">
                     
                     
                        <c:if test="${user != null}">
                            <form action="update" method="post">
                        </c:if>
                   
                        <c:if test="${user == null}">
                            <form action="insert" method="post">
                        </c:if>

                        
                            <h2>
                                <c:if test="${user != null}">
                                    Edit User
                                </c:if>
                                <c:if test="${user == null}">
                                    Add New User
                                </c:if>
                            </h2>
                  
                        
                        
                        <c:if test="${user != null}">
                            <input  type="hidden" name="id" value="<c:out value='${user.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>User Name</label> <input type="text" value="<c:out value='${user.name}' />" class="form-control" name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>User Email</label> <input type="text" value="<c:out value='${user.email}' />" class="form-control" name="email">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>User Country</label> <input type="text" value="<c:out value='${user.country}' />" class="form-control" name="country">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                      </form>
                    </div>
                   </div>
                  </div>
              
</body>
</html>