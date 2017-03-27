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
        }

        #mealstable th {
            font-family: Arial, sans-serif;
            font-size: 16px;
            font-weight: bold;
            padding: 10px 5px;
            border: 1px solid #a8a8a8;
            overflow: hidden;
            word-break: normal;
            color: #333;
            background-color: #ababab;
        }

        #mealstable td {
            font-family: Arial, sans-serif;
            font-size: 16px;
            padding: 10px 5px;
            border: 1px solid #a8a8a8;
            overflow: hidden;
            word-break: normal;
            /*color: #333;*/
        }

        #mealstable td.calories {
            text-align: center;
        }
        #mealstable td.dscr {
            text-align: left;
        }

        #mealstable tr.exdOdd {
            color: #ff4f56;
            background-color: #d7d7d7;
        }

        #mealstable tr.exdEven {
            color: #ff4f56;
            background-color: #e9e9e9;

        }

        #mealstable tr.nrmOdd {
            color: #348b37;
            background-color: #d7d7d7;
        }

        #mealstable tr.nrmEven {
            color: #348b37;
            background-color: #e9e9e9;
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
        <th width="130">DateTime</th>
        <th width="240">Descriptor</th>
        <th width="120">Calories</th>
        <th width="60"></th>
        <th width="60"></th>
    </tr>
    <c:set var="i" scope="page" value="${0}"/>
    <c:forEach items="${listMeals}" var="meal">
        <c:set var="i" scope="page" value="${i+1}"/>
        <tr class=${i%2==0?(meal.exceed ? 'exdEven':'nrmEven'):(meal.exceed ? 'exdOdd':'nrmOdd')}>
            <td><javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td class = "dscr">${meal.description}</td>
            <td class="calories">${meal.calories}</td>
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
