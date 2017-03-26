<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.LocalDateTime" %>

<html>
<head>
    <title>${meal.id==null?'Add Meal':'Edit Meal'}</title>
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
    </style>
</head>
<body>
<form action='meals' method="post" accept-charset="UTF-8">
    <table id="mealstable">
        <tr>
            <th width="120">DateTime</th>
            <th width="240">Descriptor</th>
            <th width="120">Calories</th>
            <th width="60"></th>
        </tr>
        <tr>
            <input type="hidden" name="action" value="${meal.id==null?'add':'edit'}"/>
            <input type="hidden" name="id" value="${meal.id==null?'0':meal.id}"/>
            <td>
                <input type="datetime-local" pattern="yyyy-MM-dd HH:mm" name="datetime" value="${meal.dateTime==null?LocalDateTime.now():meal.dateTime}"/>
            </td>
            <td>
                <input type="text" name="description" value="${meal.description}" placeholder="Что за еда..." />
            </td>
            <td>
                <input type="text" pattern="[0-9]{1,}" name="calories" value="${meal.calories}" placeholder="калории"/>
            </td>
            <td>
                <input type="submit" value="${meal.id==null?'Add':'Edit'}"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
