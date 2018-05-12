<%@ include file="includes/loginheader.jsp" %>

	
<div class="logininnermainDiv"> 	

         <div class="inner-left-corner">
            
             <div id="image-content" style="margin-top: 20px;float: left;margin-left: 23px;">
             
             <img id="loader" src="<c:url value="images/authbridge-ls2.png" />" style="width: 736px;"/>
             </div>
             
             <div id="content" style="float: left;text-align: center;">
             
                <p style="color: #212121;font-size: 15px;font-weight: 300;line-height: 1.5;margin-top: 16px;width: 648px;text-align: justify;margin-left: 27px;">ESign facility which can be used to digitally sign your uploaded documents. This is similar to the process of self-attestation.  </p>
              
                <h1 style="float: left;margin-top: 34px;margin-bottom: 3px;margin-left: 26px;    font-weight: bold;font-size: 22px;">What is self-attestation? </h1>
                
                <p style="color: #212121;font-size: 15px;font-weight: 300;line-height: 1.5;margin-top: 16px;width: 648px;float: left;text-align: justify;margin-left: 27px;">Self Attestation is a method of verification of a document by the applicant himself/herself. It is done by taking a photocopy of a document and then self-certifying by affixing signature on it and writing true copy or self attested if required. Self Attestation empowers the owner of a document to vouch for its authenticity by affirmation. It saves valuable time and resources taken in finding officials who provide attestation and then actually getting them to attest it.  </p>             
             </div>

    </div>
	

    
     
       <div class="inner-right-corner">
       
       		 <span class="loginspan" ></span>
       		 <c:if test="${exerror ==1}">
                <script>
                   alert('Your Password Is Expired');
                   window.location = '/authpasschange';
                </script>
                
             </c:if>
                <%
           
                if (request.getParameter("succmessage") != null) {%>
                	 <p style="color: #68a26d;font-weight: bold;"><%= request.getParameter("succmessage") %></p>
                <% } %>
       
       
       <p  style="color: #f73030;font-weight: bold;">${error}</p>
       
       <div class="panel-body">
       
          <h1 class="login-heading">Sign In to your Esign account</h1>
         <br><br>
          <form action="./login" method="post" class="form-signin">
                <input type="text" class="form-control" name="username" placeholder="UserName" required autofocus>
                <input type="password" class="form-control" name="password" placeholder="Password" required>
                <input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    Sign in</button>
                
               <span class="clearfix"></span>
          </form>
                <c:if test="${param.error ne null}">
				<div class="alert-danger">Invalid Username or password.</div>
				</c:if>
				<c:if test="${param.logout ne null}">
					<div class="alert-normal">You have been logged out.</div>
				</c:if>
			
           </div>  
       </div>
  
       
     
</div> 
	
 <%-- <div class="container" style="margin-top: 3%;">
    <div class="row">
       <div class="col-sm-6 col-md-4 col-md-offset-4">
         <div class="panel panel-primary">
           <div class="panel-heading">Welcome to ESIGNING</div>
            <div class="panel-body">
             <div align="center"><img  height="48" width="200" src="images/logo.jpg"></img></div>
               <br><br>
               <form action="./login" method="post" class="form-signin">
                <input type="text" class="form-control" name="username" placeholder="UserName" required autofocus>
                <input type="password" class="form-control" name="password" placeholder="Password" required>
                <input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    Sign in</button>
                
               <span class="clearfix"></span>
                </form>
                <c:if test="${param.error ne null}">
				<div class="alert-danger">Invalid Username or password.</div>
				</c:if>
				<c:if test="${param.logout ne null}">
					<div class="alert-normal">You have been logged out.</div>
				</c:if>
            </div>
            </div>
           </div>
        </div>
    </div> 

</div>--%>

<%@ include file="includes/loginfooter.jsp" %>
