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
<select class="form-control" name="citiescat" id="citiescat" style="width: 20%">
    <option selected>Select city</option>
<#list cities as c>
    <option value="${c}">${c}</option>
</#list>
</select>

<h4><label>High school</label></h4>
<select class="form-control" name="highschcat" id="highschcat" style="width: 20%">
    <option selected>Select high school</option>
</select>

<h4><label>Department</label></h4>
<select class="form-control" name="departmentcat" id="departmentcat" style="width: 20%">
    <option selected>Select department</option>
</select>

<h4><label>Group</label></h4>
<form method="get" action="/schedule">
    <select class="form-control" name="groupId" id="groupcat" style="width: 20%">
        <option selected>Select group</option>
    </select>
    <input type="submit" class="btn" value="Search">
</form>

<table class="table" width="70%" align="center" border="5">
    <tr>
        <th>Day</th>
        <th>8.30-10.00</th>
        <th>10.10-11.40</th>
        <th>11.50-13.20</th>
        <th>13.35-15.05</th>
    </tr>
    </div>

    <tr>
        <th>Monday</th>
    <#if monday ?? && monday?size != 0>
        <#list monday as m>
            <#if m.time == '8.30'>
                <td>${m.subjectName}</td></#if>
            <#if m.time == '10.10'>
                <td>${m.subjectName}</td></#if>
            <#if m.time == '11.50'>
                <td>${m.subjectName}</td>
            </#if>
            <#if m.time == '13.35'>
                <td>${m.subjectName}</td>
            </#if>
        </#list>
    <#else>
        <td>-</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </#if>
    </tr>

    <tr>
        <th>Tuesday</th>
    <#if tuesday ?? && tuesday?size != 0>
        <#list tuesday as m>
            <#if m.time == '8.30'>
                <td>${m.subjectName}</td></#if>
            <#if m.time == '10.10'>
                <td>${m.subjectName}</td></#if>
            <#if m.time == '11.50'>
                <td>${m.subjectName}</td>
            </#if>
            <#if m.time == '13.35'>
                <td>${m.subjectName}</td>
            </#if>
        </#list>
    <#else>
        <td>-</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </#if>
    </tr>

    <tr>
        <th>Wednesday</th>
    <#if wednesday ??>
        <#list wednesday as m>
            <#if m.time == '8.30'>
                <td>${m.subjectName}</td></#if>
            <#if m.time == '10.10'>
                <td>${m.subjectName}</td></#if>
            <#if m.time == '11.50'>
                <td>${m.subjectName}</td>
            </#if>
            <#if m.time == '13.35'>
                <td>${m.subjectName}</td>
            </#if>
        </#list>
    <#else>
        <td>-</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </#if>
    </tr>

    <tr>
        <th>Thursday</th>
    <#if thursday ??>
        <#list thursday as m>
            <#if m.time == '8.30'>
                <td>${m.subjectName}</td></#if>
            <#if m.time == '10.10'>
                <td>${m.subjectName}</td></#if>
            <#if m.time == '11.50'>
                <td>${m.subjectName}</td>
            </#if>
            <#if m.time == '13.35'>
                <td>${m.subjectName}</td>
            </#if>
        </#list>
    <#else>
        <td>-</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </#if>
    </tr>

    <tr>
        <th>Friday</th>
    <#if friday ??>
        <#list friday as m>
            <#if m.time == '8.30'>
                <td>${m.subjectName}</td></#if>
            <#if m.time == '10.10'>
                <td>${m.subjectName}</td></#if>
            <#if m.time == '11.50'>
                <td>${m.subjectName}</td>
            </#if>
            <#if m.time == '13.35'>
                <td>${m.subjectName}</td>
            </#if>
        </#list>
    <#else>
        <td>-</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </#if>
    </tr>
</table>

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


    function httpGet() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open("GET", "/schedule", false); // false for synchronous request
        xmlHttp.send(null);
        return xmlHttp.responseText;
    }

</script>
</body>
</html>