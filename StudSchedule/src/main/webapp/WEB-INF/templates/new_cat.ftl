<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"]>
<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<html>
<head>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="css/original.css"/>
    <title>
        Students' schedule
    </title>
</head>

<body>

<@security.authorize  access="hasRole('ADMIN')">
<ul class="nav nav-tabs">
    <li>
        <a href="/">Main</a>
    </li>
    <li>
        <a href="/newcat">Add new categories</a>
    </li>
    <li>
        <a href="/newschedule">Add schedule</a>
    </li>
    <li>
        <a href="/deletecat">Delete categories</a>
    </li>
</ul>
</@security.authorize>

<div class="fieldset">

    <h4><label>Add high school:</label></h4>

<#if RequestParameters.successHighSchool ??>
    <h5>${RequestParameters.successHighSchool}</h5>
</#if>

<#if RequestParameters.errorHighSchool ??>
    <h5>${RequestParameters.errorHighSchool}</h5>
</#if>

<@sf.form action="/newHighSchool" method="post" modelAttribute="highSchool">
    <@sf.input path="city" class="form-control" required="required" placeholder="Enter city name"/>
        <@sf.errors path="city"/>

        <@sf.input path="address" type="text" cssClass="form-control" placeholder="Enter address" required="required"/>
        <@sf.errors path="address"/>

        <@sf.input path="name" type="text" cssClass="form-control" placeholder="Enter name" required="required"/>
        <@sf.errors path="name"/>

        <input type="submit" value="Add" class="btn-info">
    </@sf.form>
</div>

<fieldset>
    <h4><label>Add department:</label></h4>

    <#if RequestParameters.successDepartment??>
        <h5>${RequestParameters.successDepartment}</h5>
    </#if>

    <form action="/newDep" method="post">
        <select name="highSchoolId" class="form-control">
            <option selected>Select high school</option>
            <#list highSchools as s>
                <option value="${s.id}">${s.name}</option>
            </#list>
        </select>

        <input name="name" type="text" cssClass="form-control" placeholder="Enter name" required/>
        <br>
        <input type="submit" value="Add" class="btn-info">
    </form>
</fieldset>

<fieldset>
    <h4><label>Add group:</label></h4>

    <#if RequestParameters.successGroup??>
        <h5>${RequestParameters.successGroup}</h5>
    </#if>

    <form action="/newGroup" method="post">
        <select name="depId" class="form-control">
            <option selected>Select department</option>
            <#list departments as s>
                <option value="${s.id}">${s.name}</option>
            </#list>
        </select>

        <input name="number" type="text" cssClass="form-control" placeholder="Enter group's number" required/>
        <input name="head" type="text" cssClass="form-control" placeholder="Enter head's name" required/>
        <input name="headPhone" type="number" cssClass="form-control" placeholder="Enter head's phone number" required/>
        <input name="amount" type="number" cssClass="form-control" placeholder="Enter amount of students" required/>
        <br>
        <input type="submit" value="Add" class="btn-info">
    </form>
</fieldset>
</body>
</html>