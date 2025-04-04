<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@page import="java.util.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"
            href=
"https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
            integrity=
"sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I"
            crossorigin="anonymous" />
        <script src=
"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity=
"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                crossorigin="anonymous">
          </script>
        <script src=
"https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
                integrity=
"sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
                crossorigin="anonymous">
          </script>
          <script type="text/javascript" src="index.js"></script>

<meta charset="UTF-8">
<title>User CRUD</title>
</head>
<body>

      <header>
      <nav class="navbar navbar-expand -md navbar-dark" style="background-color : tomato">
         <div>
         <h1 class="navbar-brand">User Management App</h1>
         </div>
         
         <ul class="navbar-nav">
             <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
         </ul>
           </nav>
      </header>
         <div class="row">      
                    
            <div class="container">
           
                
                <h3 class="text-center">List of Users</h3>
                
             <!--   <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User </a>
              </div> -->
              
              
            <div>
            
            <button
            type="button"
                    class="btn btn-primary"
                    data-toggle="modal"
                    data-target="#exampleModal"
                    data-whatever="@anything">
            Add New User
              </button> 
             
             </div>
            

            <!-- Modal -->
            <div class="modal fade" id="exampleModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">
                            Enter  user details
                              </h5>
                            <button type="button"
                                    class="close"
                                    data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">
                                    ×
                                  </span>
                            </button>
                        </div>
                        <div class="modal-body">
                        
                            <form id="addform" method="post" action="insert">
                                <input  type="hidden" name="id" value="<c:out value='${user.id}' />" />
                                <div class="mb-3">
                                    <label for="recipient-name"
                                        class="col-form-label">
                                        Name:
                                      </label>
                                    <input type="text"
                                    required="required"
                                        class="form-control"
                                        <c:out value="${user.name}"/>
                                        name="name"
                                        id="name"
                                         />
                                </div>
                                <div class="mb-3">
                                    <label for="message-text"
                                        class="col-form-label">
                                          Email:
                                      </label>
                                       <input type="email"
                                        class="form-control"
                                        <c:out value="${user.email}"/>
                                        name="email"
                                        required="required" />
                                      
                                 <div class="mb-3">
                                    <label for="recipient-name"
                                        class="col-form-label">
                                        Country:
                                      </label>
                                    <input type="text"
                                        class="form-control"
                                        required="required"
                                        <c:out value="${user.country}" />
                                        name="country" />
                                        
                                         <div class="mb-3">
                                    <label for="message-text"
                                        class="col-form-label">
                                          Number:
                                      </label>
                                       <input type="text"
                                       maxlength="10"
                                       minlength="10"
                                       id="number"
                                        class="form-control"
                                        <c:out value="${user.number}"/>
                                        name="number"
                                        required="required" />
                                </div>
                                      
                                  
                                     <!--   <textarea class="form-control"
                                            id="message-text">
                                    </textarea> -->
                                    
                                </div>
                            
                        </div>
                        <div class="modal-footer">
                            <button type="button"
                                    class="btn btn-secondary"
                                    data-dismiss="modal">
                                Close
                              </button>
                            <button type="submit"
                                    class="btn btn-primary"
                                    onClick = "return validate()"
                                    >
                                Add
                              </button>
                            
                        </div>
                    </div>
                </div>
               </form>
            </div>
            </div>
            
            
            
            <!-- Modal -->
            <div class="modal fade" id="UpdateModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">
                            Update User 
                              </h5>
                            <button type="button"
                                    class="close"
                                    data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">
                                    ×
                                  </span>
                            </button>
                        </div>
                        <div class="modal-body">
                       
                            <form method="post" id="upp" action="update">
                                <input  type="hidden" name="id" id="id" value="${user.id}" />
                                <div class="mb-3">
                                    <label for="recipient-name"
                                        class="col-form-label">
                                        Name:
                                      </label>
                                    <input type="text"
                                        class="form-control"
                                       value="${user.name }"
                                        name="name"
                                        id="n"
                                        required="required"
                                         />
                                </div>
                                <div class="mb-3">
                                    <label for="message-text"
                                        class="col-form-label">
                                          Email:
                                      </label>
                                       <input type="email"
                                        class="form-control"
                                        value="${user.email }"
                                        name="email"
                                        id="email"
                                        required="required" />
                                      
                                 <div class="mb-3">
                                    <label for="recipient-name"
                                        class="col-form-label">
                                        Country:
                                      </label>
                                    <input type="text"
                                        class="form-control"
                                        value="${user.country }"
                                        name="country"
                                        id="country"
                                        required="required" />
                                        
                                         <div class="mb-3">
                                    <label for="message-text"
                                        class="col-form-label">
                                          Number:
                                      </label>
                                       <input type="number"
                                        class="form-control"
                                        value="${user.number }"
                                        name="number"
                                        id="m"
                                        required="required" />
                                </div>
                           
                                    
                                </div>
                            
                        </div>
                        <div class="modal-footer">
                            <button type="button"
                                    class="btn btn-secondary"
                                    data-dismiss="modal">
                                Close
                              </button>
                            <button type="submit"
                                    class="btn btn-primary"
                                    name="id"
                                    id="submit-btn"
                                 >
                                Save Changes
                              </button>
                            
                        </div>
                    </div>
                </div>
               </form>
            </div>
            </div>
            
     
           <script>
           document.getElementById('UpdateModal').addEventListener('show.bs.modal',function(event){
        	   var button = event.relatedTarget;
        	   
        	   var userId = button.getAttribute('data-id');
        	   var userName = button.getAttribute('data-name');
        	   var userEmail = button.getAttribute('data-email');
        	   var userCountry = button.getAttribute('data-country');
        	   var userNumber = button.getAttribute('data-number');
        	   
        	   console.log(userId);
        	   console.log(userName);
        	   console.log(userEmail);
        	   console.log(userCountry);
        	   console.log(userNumber);
        	   

        	   document.getElementById('id').value = userId;
        	   document.getElementById('name').value = userName;
        	   document.getElementById('email').value = userEmail;
        	   document.getElementById('country').value = userCountry;
        	   document.getElementById('number').value = userNumber;
        	   
        	   validate();
        	   

           });
           
           
           
           function validate(){
        	   var num =  document.getElementById('number').value.trim();
         	  console.log(num);
         	  
         	   let isValid = true;
             	 var mobilePattern = /^\d{10}$/;
             	 if (!mobilePattern.test(num)) {
             	 alert("Please enter a valid 10-digit mobile number.");
             	 isValid = false;
             	 }
             	 
          	   var name =  document.getElementById('name').value.trim();
          	   console.log(name);
          	   
          	   var nameRegx = /^[A-Za-z\s]+$/;
          	   if(!nameRegx.test(name)){
          		   alert("No digits allowed, please enter the name");
          		   isValid = false;
          	   }
             	 
             	 return isValid;
           }
           
        
           //for now data showing in the console , this is part of the extra task
          /* async function Restcountries(){
        	   const url = 'https://restcountries.com/v3.1/all';
        	   const options = {
        	   	method: 'GET',
        	   };

        	   try {
        	   	const response = await fetch(url, options);
        	   	const result = await response.text();
        	   	console.log(result);
        	   } catch (error) {
        	   	console.error(error);
        	   }
           }
           
           Restcountries();  */
           </script>    
                   
         <br>
                   
       <table class="table table-bordered">
          <thead >
                <tr>
                  <td width="120" ><b>ID</b></td>
                  <td width="120" ><b>Name</b></td>
                  <td width="120" ><b>Email</b></td>
                  <td width="120" ><b>Country</b></td>
                  <td width="120" ><b>Number</b></td>
                  <td width="120" ><b>Actions<b></td>
                </tr>
              </thead>
                        <tbody>
                           <c:forEach var="user" items="${listUser}">

                                <tr>
                                    <td>
                                        <c:out value="${user.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.email}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.country}" />
                                    </td>
                                    <td>
                                        <c:out value="${user.number}" />
                                    </td>
                                    <td><button type="button"
                    class="btn btn-primary editbtn"
                    data-toggle="modal"
                    data-target="#UpdateModal"
                    data-id="${user.id}"
                    data-name="${user.name}"
                    data-email="${user.email}"
                    data-country="${user.country}"
                    data-number="${user.number}"
                    > Edit</button> </a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                          </tbody>
                   </table>
                 </div>
               </div>
               
          <!--  <td><a href data-toggle="modal" data-target="#UpdateModal" data-id="${user.id }"> -->

</body>

       
</html>