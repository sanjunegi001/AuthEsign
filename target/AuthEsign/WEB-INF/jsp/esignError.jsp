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

<table  class="table table-bordred table-striped">
       <tr>
                        <td style="height: 96px;border-collapse: collapse;border-spacing: 0;float: left;">
                          <img style="width:96px;height:96px;" src="<c:url value="images/error.png"/>" />
                       </td>
                       <td style="margin-top: 24px;margin-left: 19px;float: left;">
                          <table>
                               
                                <tr>
                                   <td><span style="font-weight:bold ;font-size: 16px;">TransactionId:</span></td>
                                   <td><span style="color: #797272;font-weight: bold;font-size: 14px;padding-left:2px;"><c:out value="${esigntransactionnm}"/></span></td>
                                </tr>
                             	<tr>
                                    <td><span style="font-weight:bold;font-size: 16px;">ErrorCode:</span></td>
                                    <td><span style="color: red;font-weight: bold;font-size: 14px;"><c:out value="${error}"/></span></td>
                                </tr> 
                                <tr>
                                    <td><span style="font-weight:bold;font-size: 16px;">Message:</span></td>
                                    <td><span style="color: red;font-weight: bold;font-size: 14px;"><c:out value="${message}"/></span></td>
                                </tr> 
                           </table>
                       </td>
                   </tr>               

</table>
</div>
</div> 

 <%@ include file="includes/footer.jsp" %>

