<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>팀 구성원 목록</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        table {
            width: 40%;
            margin: 0 auto;
            border-collapse: collapse;
        }
        th, td {
            border: 0px solid #ddd;
            padding: 8px;
            text-align: right;
        }
        th {

        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>
<h2>CAST</h2>
<table>
    <tr>
        <th></th>
        <th></th>
    </tr>
    <tr>
        <td rowspan="3" style="text-align: left">기획팀</td>
        <td>Soobin Choi</td>
    </tr>
    <tr>
        <td>노   현   정</td>
    </tr>
    <tr>
        <td>이   금   영</td>
    </tr>
        <th></th>
        <th></th>
    <tr>
        <td style="text-align: left">디자인팀</td>
        <td>이   수   연</td>
    </tr>
        <th></th>
        <th></th>
    <tr>
        <td rowspan="2" style="text-align: left">프런트팀</td>
        <td>서   지   연</td>
    </tr>
    <tr>
        <td>김   진   호</td>
    </tr>
        <th></th>
        <th></th>

    <tr>
        <td style="text-align: left">백엔드팀</td>
        <td>이   동   훈</td>
    </tr>
</table>
</body>
</html>
