<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"]/>
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
<@security.authorize access="hasAnyRole('ADMIN', 'USER')">
<ul class="nav nav-tabs">
    <li>
        <a href="/">Main</a>
    </li>
    <@security.authorize  access="hasRole('ADMIN')">

        <li>
            <a href="/newcat">Add new categories</a>
        </li>
        <li>
            <a href="/newschedule">Add schedule</a>
        </li>
        <li>
            <a href="/deletecat">Delete categories</a>
        </li>
    </@security.authorize>
    <li>
        <a href="/logout">Logout</a>
    </li>
</ul>
</@security.authorize>



<h4><label>City</label></h4>
<select class="form-control" name="citiescat" id="citiescat">
    <option selected>Select city</option>
<#list cities as c>
    <option value="${c}">${c}</option>
</#list>
</select>

<#if RequestParameters.successHighSchool ??>
<h5>${RequestParameters.successHighSchool}</h5>
</#if>

<h4><label>High school</label></h4>
<form method="post" action="/deleteHighSchool">
    <select class="form-control" name="highSchId" id="highschcat">
        <option selected>Select high school</option>
    </select>
    <input type="submit" value="Delete" class="btn-danger">
</form>

<#if RequestParameters.successDepartment ??>
<h5>${RequestParameters.successDepartment}</h5>
</#if>

<h4><label>Department</label></h4>
<form action="/deleteDep" method="post">
    <select class="form-control" name="depId" id="departmentcat">
        <option selected>Select department</option>
    </select>
    <input type="submit" value="Delete" class="btn-danger">
</form>

<#if RequestParameters.successGroup ??>
<h5>${RequestParameters.successGroup}</h5>
</#if>

<h4><label>Group</label></h4>
<form action="/deleteGroup" method="post">
    <select class="form-control" name="groupId" id="groupcat">
        <option selected>Select group</option>
    </select>
    <input type="submit" value="Delete" class="btn-danger">
</form>

<#if RequestParameters.successSchedule ??>
<h5>${RequestParameters.successSchedule}</h5>
</#if>

<label><h4>Delete schedule of this group:</h4></label>
<form action="/deleteSchedule" method="post">
    <select class="form-control" name="groupId" id="groupcat2">
        <option selected>Select group</option>
    </select>
    <input type="submit" value="Deleteschedule" class="btn-danger">
</form>

<script type="text/javascript">

    $('#citiescat').change(
            function () {
                $.ajax({
                    type: "GET",
                    url: "high_schools",
                    data: {city: $('#citiescat').val()},
                    success: function (data) {
                        var div_data = " <option selected>Select high school</option>";
                        $.each(data, function (i, obj) {
                            div_data += "<option value=" + obj.id + ">" + obj.name + "</option>";
                        });
                        $('#highschcat').html(div_data);
                    }
                });
            });

    $('#highschcat').change(
            function () {
                $.ajax({
                    type: "GET",
                    url: "departments",
                    data: {highschId: $('#highschcat').val()},

                    success: function (data) {
                        var div_data = " <option selected>Select department</option>";
                        $.each(data, function (i, obj) {
                            div_data += "<option value=" + obj.id + ">" + obj.name + "</option>";
                        });

                        $('#departmentcat').html(div_data);

                    }
                });
            });

    $('#departmentcat').change(
            function () {
                $.ajax({
                    type: "GET",
                    url: "groups",
                    data: {depId: $('#departmentcat').val()},

                    success: function (data) {
                        var div_data = " <option selected>Select group</option>";
                        $.each(data, function (i, obj) {
                            div_data += "<option value=" + obj.id + ">" + obj.number + "</option>";
                        });

                        $('#groupcat').html(div_data);
                        $('#groupcat2').html(div_data);
                    }
                });
            });

</script>

</body>
</html>