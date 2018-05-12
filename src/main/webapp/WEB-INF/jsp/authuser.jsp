<%@ include file="includes/header.jsp" %>

<script>
function blinker() {
    $('.blink_me').fadeOut(500);
    $('.blink_me').fadeIn(500);
    $('.blink_me').hide;
}

setInterval(blinker, 1500);

$(function() {
    var action;
    $(".number-spinner button").mousedown(function () {
        btn = $(this);
        input = btn.closest('.number-spinner').find('input');
        btn.closest('.number-spinner').find('button').prop("disabled", false);

    	if (btn.attr('data-dir') == 'up') {
            action = setInterval(function(){
                if ( input.attr('max') == undefined || parseInt(input.val()) < parseInt(input.attr('max')) ) {
                    input.val(parseInt(input.val())+1);
                }else{
                    btn.prop("disabled", true);
                    clearInterval(action);
                }
            }, 50);
    	} else {
            action = setInterval(function(){
                if ( input.attr('min') == undefined || parseInt(input.val()) > parseInt(input.attr('min')) ) {
                    input.val(parseInt(input.val())-1);
                }else{
                    btn.prop("disabled", true);
                    clearInterval(action);
                }
            }, 50);
    	}
    }).mouseup(function(){
        clearInterval(action);
    });
});
</script>

<style>
input{
font-size: 11px;

}
.navbar-brand {
  padding: 0px;
}
.navbar-brand>img {
  height: 110%;
  padding: 0px;

}
</style>

<%@ include file="includes/innermenu.jsp" %>
<div class="container">

 <div class="row">
<table id="mytable" class="table table-bordred table-striped">
           <thead>
        </thead>
	 <tbody>
	  <br>		
			<div class="row">
			<h4 align="center" class="blink_me" style="color:green;">  ${authuser} </h4>
			<br>
		<form role="form" action="saveAuthuser" method ="post" modelAttribute="authuser">
                <div class="form-group col-sm-4">
		    	<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
                    <label for="userName"><span class="text-danger" style="margin-right:5px;">*</span>AuthUser-Login:</label>
                 
                    <div class="text-center">
                         <input class="form-control" id="userName"   type="text" name="userName" placeholder="AuthUser-login-detail" required/>
                       </div>
                    </div>
                     <div class="form-group col-sm-4">
		
                    <label for="password"><span class="text-danger" style="margin-right:5px;">*</span>AuthUser-Password:</label>
                  
                    <div class="text-center">
                       
                         <input class="form-control" id="password"    type="text" name="password" placeholder="authuserpass" required/>
                       </div>
                    </div> 
                    
        </div>
				
		 <!-- Username Field -->
				<div class="row">
                 <div class="form-group col-sm-4">
                    <label for="userfname"><span class="text-danger" style="margin-right:5px;">*</span>AuthUser First Name:</label>
                         <div class="input-center">
                            <input class="form-control" id="userfname" type="text" name="userfname"  placeholder="authuserfirstname" required/>
                          </div>
                    </div>
				 <div class="form-group col-sm-4">
					 <label for="userlname"><span class="text-danger" style="margin-right:5px;">*</span>AuthUser Last Name:</label>
                         <div class="input-center">
                            <input class="form-control" id="userlname" type="text" name="userlname"  placeholder="authuserlastname" required/>
                          </div>
		         </div>
			</div>
		
			<div class="row">
			
              <div class="form-group col-sm-4">
			    <div class="input-center">
                    <label for="role"><span class="text-danger" style="margin-right:5px;">*</span>AuthUser Role:</label>
                         <div class="input-center">
         <select required class="form-control" name="role" id="role">
	 		<option value="">select role</option>
	 		<option value="1">ADMIN</option>
			<option value="2">USER</option>
			
    </select>
	</div>
      </div>
					
			</div>
	</div>
	<br>
		<div class="form-group col-sm-3">
        <button class="btn btn-primary btn-sx"  type="submit" style=" margin-left: 200px;padding: 6px 50px;">Submit</button>
				</div>
				</div>
  </form>
	
</tbody>

	</table>
					  
	</div>					  
 </div>
 <%@ include file="includes/footer.jsp" %>