<%@ include file="includes/header.jsp" %>


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


<%@ include file="includes/innersubmenu.jsp" %>



<div class="container" style="margin-top:1%;">

<div class="row">
<br>
<H4 style="color:green; text-align:center;">LIST OF ESIGNED DOCUMENTS</H4>
<br>
<table  class="table table-bordred table-striped">
                      <thead  style="font-size: 11px;">
                      <th>NAME</th>
                      <th>CREATED</th>
                      <th>SIZE</th>
                     
                      
          </thead>
      <tbody>
<c:if test ="${empty employee}">
 <td><H5 style="text-align:center;">No Document Found</H5></td>

</c:if>
  <c:forEach items="${employee}" var="employee">  

  <tr>
 

<input type="hidden" name="Id" id="Id" value="<c:out value="${employee.id}"/>">
  
 <td>
                      <c:out value="${employee.name}" />
                     </td>
                     <td>
                       <c:out value="${employee.date}" />
                     </td>
                   
                      <td>
                       <c:out value="${employee.fsize}" />KB
                      </td>
                     
                     <td>
                       <a href="download.html?dfpath=<c:out value="${employee.docs}" />">Download</a>
                     </td>
                     
                     <td>
                       <a href="<c:out value="${employee.id}"/>" id="deleteEsign">Delete</a>
                     </td>

</tr>
 </c:forEach>
</tbody>

</table>
</div>
</div>
<script type="text/javascript">

$(document).ready(function() {

	$("a#deleteEsign").on('click', function() {
	if(confirm("Are you sure to delete this record?")){
		
	    var eseid = $(this).attr("href");
		
		$.ajax({
			
			    type : "POST",
				url : "esignDelete?${_csrf.parameterName}=${_csrf.token}",
				processData: false,
				data: "esignseid=" + eseid,
				beforeSend : function() {
	    			  
	                $('#esignmodal').css("display", "block");
	                $('#esignfade').css("display", "block");
	               },
	               success : function(data) {
	           
	            	   var esigndata = JSON.parse(data);
	            	   if(esigndata.status=="1")
	            		   {
	            		       alert("File is deleted successfully!");
	            		       window.location.reload();
	            		   }
	            	   else if(esigndata.status=="0")
	            		   {
	            		      alert("File is not deleted?");
	            		   }
	            	   else
	            		   {
	            		   alert("File is not deleted?");    
	            		   }
	            	    
	            	    
	            	   
	               },
			
			
			
			
			
		  });
        
	     return false;  
		
	}else{
		
		return false;
	}
	});
	
});

</script>   

 <%@ include file="includes/footer.jsp" %>

