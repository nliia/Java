<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<html>
<head>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="css/original.css"/>
    <title>
        Add new schedule
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

<h4><label>High school</label></h4>
<select class="form-control" name="highschId" id="highschcat">
    <option selected>Select high school</option>
</select>

<h4><label>Department</label></h4>
<select class="form-control" name="departmentId" id="departmentcat">
    <option selected>Select department</option>
</select>
<#if  RequestParameters.successSchedule ??>
<h5>${RequestParameters.successSchedule}</h5>
</#if>
<#if RequestParameters.errorSchedule ??>
<h5>${RequestParameters.errorSchedule}</h5>
</#if>

<h4><label>Group</label></h4>
<form method="post" action="/newSchedule">

    <select class="form-control" name="groupId" id="groupcat">
        <option selected>Select group</option>
    </select>

    <select class="form-control" name="weekday">
        <#list weekdays as w>
            <option name="${w}" value="${w}">${w}</option>
        </#list>
    </select>
    <label><h4>First lesson: 8.30-10.00</h4></label>
    <input type="text" name="name1" required class="form-control">

    <label><h4>Second lesson: 10.10-11.40</h4></label>
    <input type="text" name="name2" required class="form-control">

    <label><h4>Third lesson: 11.50-13.20</h4></label>
    <input type="text" name="name3" required class="form-control">

    <label><h4>Fourth lesson: 13.35-15.05</h4></label>
    <input type="text" name="name4" required class="form-control">

    <input type="submit" class="btn" value="Add schedule">
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
                    }
                });
            });

    $('#groupcat').change(
            function () {
                $.ajax({
                    type: "GET",
                    url: "schedule",
                    data: {depId: $('#departmentcat').val()},

                    success: function (data) {
                        var div_data = " <option selected>Select group</option>";
                        $.each(data, function (i, obj) {
                            div_data += "<option value=" + obj.id + ">" + obj.number + "</option>";
                        });

                        $('#groupcat').html(div_data);
                    }
                });
            });
</script>
</body>
</html>