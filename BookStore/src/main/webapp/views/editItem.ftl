<#include "menuTemplate.ftl">
<#macro content>
<title>BookStore</title>
    <#if !Session.session_uname??>
    <script language='javascript' xmlns="http://www.w3.org/1999/html">
        var delay = 0;
        setTimeout("document.location.href='login.ftl'", delay);
    </script>

    </#if>


<h4 id="center">Edit product:</h4>
<br/>
<div class="col-md-8">
    <div class="col-md-5">
        <form action="editItem" method="post" enctype="multipart/form-data">
            <br>
            <input type="hidden" name="id" value="${id}">
            <input type="text" class="input" name="name" placeholder="Name" required><br><br>
            <input type="text" class="input" name="description" placeholder="Description" required><br><br>
            <input type="number" class="input" name="price" placeholder="Price" required><br><br>
            <input type="text" class="input" name="genre" placeholder="Genre" required><br><br>
            <input type="file" class="btn" name="image" size="50" required><br><br>
            <input type="submit" class="btn btn-primary" id="add" value="Edit"/>
        </form>
    </div>
</div>
</#macro>