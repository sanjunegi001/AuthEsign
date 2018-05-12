<%@ include file="includes/header.jsp" %>

<script>
$(function(){
    $(".makeEditable").click(function(){
     //   $('input:text').removeAttr("readonly");    
        $(this).parent().parent().find('input:text').removeAttr("readonly");    
        $(this).parent().parent().find('#saveTd').show();
        $(this).parent().hide();
    });
    $(".makeNonEditable").click(function(){
    	$('input:text').attr("readonly", "readonly");   
    	$(this).parent().parent().find('#saveTd').hide();
    	$(this).parent().parent().find('#editTd').show();
    	//location.reload();
       
    });               
});


function show2(){document.getElementById("s").style.display="none";
document.getElementById("e").style.display="block";

}


</script>
 
<script type="text/javascript">
  function ischkESIGNOtp()
   {
	 
	 return $('#chkESIGNOtp').is(':checked');	
	 
   }
 function ischkESIGNbio()
  {
	 
	 return $('#chkESIGNbio').is(':checked');	
	 
}
function ischkESIGNiris()
{
	 
	 return $('#chkESIGNiris').is(':checked');	
	 
}
function EnableDisableEsignProceedButton()
{
	
	 document.getElementById('chkESIGNbio').disabled = false;
	 document.getElementById('chkESIGNOtp').disabled = false;
	
	 
	 var txtAadhaarValue = $('#txtAadhaarNo').val();
	
	 
	
	 
	 
	 if($.trim(txtAadhaarValue).length < 12) {
	    	alert("Invalid Aadhaar Number");
	    	$('input:checkbox').each(function() { this.checked = false; });
	        document.getElementById('btnProceed').disabled = true;
	        return;
	    }
	 
	 if($.trim(txtAadhaarValue).length > 12) {
	    	alert("Invalid Aadhaar Number");
	    	 $('input:checkbox').each(function() { this.checked = false; });
	        document.getElementById('btnProceed').disabled = true;
	        return;
	    }
	 
	 var isAadhaarNumberValid = checkverhoff($('#txtAadhaarNo').val());
	 if (isAadhaarNumberValid === false) {
	        document.getElementById('btnProceed').disabled = true;
	        document.getElementById('chkESIGNbio').disabled = false;
	        document.getElementById('chkESIGNOtp').disabled=false;
	       // document.getElementById('chkESIGNiris').disabled=false;
	        
	        
	        alert("Invalid Aadhaar Number");
	        $('input:checkbox').each(function() { this.checked = false; });
	        return;
	    }
	 
	 if ((ischkESIGNOtp())&& isAadhaarNumberValid) {
	    	
	    	document.getElementById('btnProceed').disabled = false;
	    	document.getElementById('chkESIGNbio').disabled = true;
	    	
	    	//document.getElementById('chkESIGNiris').disabled = true;
	    }
	 else if((ischkESIGNbio())&& isAadhaarNumberValid)
		 {
		   
		    document.getElementById('btnProceed').disabled = false;
	    	document.getElementById('chkESIGNOtp').disabled = true;
	    	//document.getElementById('chkESIGNiris').disabled = true;
		 }
	 else if((ischkESIGNiris())&& isAadhaarNumberValid)
		 {
		 
		   
		    document.getElementById('btnProceed').disabled = false;
	    	document.getElementById('chkESIGNbio').disabled = true;
	    	document.getElementById('chkESIGNOtp').disabled = true;
		 }
	 else
		 {
		 ocument.getElementById('btnProceed').disabled = true;
		 }
	 
  }
function checkverhoff(value) {
	
    var d = [[0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            [1, 2, 3, 4, 0, 6, 7, 8, 9, 5],
            [2, 3, 4, 0, 1, 7, 8, 9, 5, 6],
            [3, 4, 0, 1, 2, 8, 9, 5, 6, 7],
            [4, 0, 1, 2, 3, 9, 5, 6, 7, 8],
            [5, 9, 8, 7, 6, 0, 4, 3, 2, 1],
            [6, 5, 9, 8, 7, 1, 0, 4, 3, 2],
            [7, 6, 5, 9, 8, 2, 1, 0, 4, 3],
            [8, 7, 6, 5, 9, 3, 2, 1, 0, 4],
            [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]];
    var p = [[0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            [1, 5, 7, 6, 2, 8, 3, 0, 9, 4],
            [5, 8, 0, 3, 7, 9, 6, 1, 4, 2],
            [8, 9, 1, 6, 0, 4, 3, 5, 2, 7],
            [9, 4, 5, 3, 1, 2, 6, 8, 7, 0],
            [4, 2, 8, 6, 5, 7, 3, 9, 0, 1],
            [2, 7, 9, 3, 8, 0, 6, 4, 1, 5],
            [7, 0, 4, 6, 9, 1, 3, 2, 5, 8]];
    var j = [0, 4, 3, 2, 1, 5, 6, 7, 8, 9];

    if ($.trim(value) === '')
        return false;

    var c = 0;
    value.replace(/\D+/g, "").split("").reverse().join("").replace(/[\d]/g, function (u, i, o) {
        c = d[c][p[i & 7][parseInt(u, 10)]];
    });
    return (c === 0);
}


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



 <script>
  $( function() {
    $( "#startdate" ).datepicker();
  } );
  </script>
  
   <script>
  $( function() {
    $( "#EndDate" ).datepicker();
  } );
  </script>
  
  
<%@ include file="includes/innermenu.jsp" %>

		<form id="eSignForm"  method="post" action="https://14.142.129.242/EsignAuth/getEkycDetails" >
  			<input type="hidden"  name="msg" id="msg" value=""/>
     		<input type="hidden"  name="obj" id="obj" value=""/>
  	   </form>
  	   
  	   
<div class="container" style="margin-top:1%;">

<div class="row">

<table  class="table table-bordred table-striped">
      <thead>
      </thead>
      <tbody>
		 <form id="form1" method="post" action="#" enctype="multipart/form-data">
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
				<div  id="main_aadhaar_numbers"  style="width:500px; margin:0 auto;">
			
                    <label for="txtAadhaarNo"><span class="text-danger">*</span>Aadhaar Number:</label>
                    <input style="margin-left: 4px;width: 309px;border: 1px solid #ccc;height: 30px;border-radius: 5px;"   id="txtAadhaarNo"   type="text" name="txtAadhaarNo" placeholder="aadhaar number" required/>
                       
               </div>	
               </br></br></br>
               <div  id="main_bottom_auth_aadhaar" >
			
                    <label for="txtName">Name:</label>
                    <input style="margin-left: 4px;width: 209px;border: 1px solid #ccc;height: 30px;border-radius: 5px;" id="txtName"   type="text" name="txtName" placeholder="Name"/>
                    
                    <label for="txtLocation">Location:</label>
                    <input style="margin-left: 4px;width: 209px;border: 1px solid #ccc;height: 30px;border-radius: 5px;" id="txtLocation"   type="text" name="txtLocation" placeholder="Location"/>
                    
                    <label for="txtName">Reason:</label>
                    <input style="martxtReasongin-left: 4px;width: 209px;border: 1px solid #ccc;height: 30px;border-radius: 5px;" id="txtReason"   type="text" name="txtReason" placeholder="Reason"/>   
               </div>
               </br></br></br>
               <div id="main_bottom_auth_aadhaar">
                
                  <lable style="font-weight: bold;font-size: 14px;float: left;"><span style="color:red">*</span>Esign Type:</lable>
                  <input style="margin-left: 20px;" type="checkbox" id="chkESIGNOtp" name="chkESIGNOtp" class="authtypecheckbox" value="EsignOtp" onclick="EnableDisableEsignProceedButton()" ><label>Otp</label>
				  <input style="margin-left: 10px;" type="checkbox" id="chkESIGNbio" name="chkESIGNbio" class="authtypecheckbox" value="EsignBio" onclick="EnableDisableEsignProceedButton()" ><label>Bio</label></td>
               </div> 	 
               </br></br>
               <div id="main_bottom_auth_aadhaar">
                
                  	<lable style="font-weight: bold;font-size: 14px;float: left;"><span style="color:red">*</span>Upload File:</lable>
                  	<input style="margin-left: 40px;" type="file" name="file-1" id="file-1" class="inputfile inputfile-1" data-multiple-caption="{count} files selected" multiple style="height: 6px;"/>

            </div>
            </br></br></br></br>
            <div  id="main_aadhaar_numbers"  style="width:400px; margin:0 auto;">
			
                    <input class="btn btn-primary btn-sx" type="button" value="Proceed" id="btnProceed" onclick="EsignRequest()" disabled="disabled">
                       
            </div>
		 
		 </form>
				
	</tbody>

</table>
</div>
</div>


<script type="text/javascript">

    function EsignRequest()
    {
    	
  	   
    	  var authtype="";
    	  var formData = new FormData();
    	  var authtypevalue =  document.querySelector('.authtypecheckbox:checked').value;
    	  
    	  
    	  
    	  var aadhaar_no =jQuery.trim($('#txtAadhaarNo').val());
    	  var name ="";
    	  var location ="";
    	  var reason ="";	
    	  
    	  if(aadhaar_no =="")
		  {
		     alert("Aadhaar number is required!");
		     return false;
		  }
    	  
    	  if(authtypevalue =="EsignOtp")
  		 { 
     		  
     		  authtype="1";
     		  
  		 }else if(authtypevalue =="EsignBio")
 		 {
  			 authtype="2";
  			 
 		 }
  		 else{
  			 alert("Esign Type is required!");
 		       return false;
  			 
  		 }
    	  
    
    	  if(typeof($('#file-1')[0].files) =='undefined'||$('#file-1')[0].files.length==0)
    		  {
    		     alert("Please select pdf file!");
    		     return false;
    		  }
    	  else if($('#file-1')[0].files.length>1)
    		  {
    		 	 alert("You Can Select One file at a time");
 		    	 return false;
    		  	
    		  } 
    	  else{ 
    		   $.each($('#file-1')[0].files, function(i, file) {
    			  
    			  formData.append('file', file);
        		});
    	  }
    	  
    	  if(jQuery.trim($('#txtName').val()).length!=0||jQuery.trim($('#txtName').val()).length!=null) {
    		    
		      name=jQuery.trim($('#txtName').val());
    	      
	       }else{
	    	   
	    	 name="";
	       }
    	  if(jQuery.trim($('#txtLocation').val()).length!=0||jQuery.trim($('#txtLocation').val()).length!=null) {
  		    
    		      location =jQuery.trim($('#txtLocation').val());
        	      
  	       }else{
  	    	   
  	    	 location="";
  	       }
    	  
    	  if(jQuery.trim($('#txtReason').val()).length!=0||jQuery.trim($('#txtReason').val()).length!=null) {
    		   
    		   reason =jQuery.trim($('#txtReason').val());
	       }else{
	    	   
	    	   reason="";
	       }
    	 
    	  formData.append('aadhaarno', aadhaar_no); 
    	  formData.append('authmode', authtype);
    	  formData.append('name',name);
    	  formData.append('location',location);
    	  formData.append('reason', reason);
    	  
    	  
    	  
    	  
    	if(aadhaar_no !="")
    		{
    		
    		   $.ajax({
    			
    			   
    			   type : "POST",
    				url : "doesign?${_csrf.parameterName}=${_csrf.token}",
    				processData: false,
    				data: formData,
    				enctype:"multipart/form-data",
    				contentType: false,
    				
    					 beforeSend: function() {
    			                $.blockUI({ message: ' <img src="<c:url value="images/loading.gif"/>" />' });
    			            },  
    	          
    	               success : function(data) {
    			     	   var finaldata = JSON.parse(data);
    			     	   
    			     	 
    			     	   
    	            	   if(finaldata.statuscode =="1")
    	            		   {
    	            		    
    	            		   	 $('input:checkbox').each(function() { this.checked = false; });
    	            	  	     $('input[type=text]').each(function() {$(this).val('');});
    	            	  	     document.getElementById('chkESIGNbio').disabled = false;
    	            	  	     document.getElementById('chkESIGNOtp').disabled = false;
    	            		     document.getElementById("msg").value=finaldata.inputxml;
    	            		     document.getElementById("obj").value=finaldata.addharno;
    	            		     jQuery("#eSignForm").attr("target","_blank");
    	            		     document.getElementById("eSignForm").submit();
    	            		     $.unblockUI();
    	            		    
    	    	                //  window.location.reload();
    	            		   
    	            		   }
    	            	   else if(finaldata.statuscode =="2")
	            		   {
    	            		   $.unblockUI();
    	            		     alert("File is not Valid " +finaldata.error);  
	            		        
	    	                     window.location.reload();
	            		   }
    	            	   
    	            	   else if(finaldata.statuscode =="3")
	            		   {
    	            		   $.unblockUI();
    	            		   alert(finaldata.message); 
    	            		 
	            		   }
    	            	   else if(finaldata.statuscode =="4")
    	            		{
    	            		     $.unblockUI();
    	            		      alert("Invalid file ! Please try pdf file");
    	            		}
    	          	   
    	               },
    	   		
    		   });
    		   
    	
    		}
    	else
    		{
    		   $.unblockUI();
    		   alert("Aadhaar number and Otp are required!");
    		
    		}
    	
    	
    	
    }

    

</script>

 <%@ include file="includes/footer.jsp" %>

