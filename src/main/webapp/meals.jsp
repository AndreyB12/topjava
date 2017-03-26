<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Meal list</title>
    <style type="text/css">
        #mealstable {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        #mealstable th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-bottom: 1px solid #ddd;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        #mealstable td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-bottom: 1px solid #ddd;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
        }

        #mealstable td.id {
            text-align: center;
        }

        #mealstable tr.exd {
            background-color: #ff4f56;
        }

        #mealstable tr.nexd {
            background-color: #348b37;
        }

    </style>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<form action='meals' method="get">
    <input type="hidden" name="action" value="add"/>
    <input type="submit" value="Add Meal"/>
</form>
<table id="mealstable">
    <tr>
        <%--<th width="60">ID</th>--%>
        <th width="120">DateTime</th>
        <th width="240">Descriptor</th>
        <th width="120">Calories</th>
        <th width="60"></th>
        <th width="60"></th>
    </tr>
    <c:forEach items="${listMeals}" var="meal">
        <tr class=${meal.exceed ? 'exd':'nexd'}>
            <td><javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>
                <form action='meals' method="get">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="${meal.id}"/>
                    <input type="submit" value="delete"/>
                </form>
            </td>
            <td>
                <form action='meals' method="get">
                    <input type="hidden" name="action" value="edit"/>
                    <input type="hidden" name="id" value="${meal.id}"/>
                    <input type="submit" value="edit"/>
                </form>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
