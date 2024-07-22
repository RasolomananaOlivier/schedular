<%@ page import="com.project.l3.schedular.model.Attendance" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.l3.schedular.model.Meeting" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Meeting meeting = (Meeting) request.getAttribute("meeting");
%>

<!doctype html>
<html lang="fr">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.css" rel="stylesheet"/>

    <title>Invités</title>
</head>
<body class="py-4 px-5">
<jsp:include page="/WEB-INF/components/header.jsp"/>
<h1 class="mb-4 text-3xl font-extrabold text-gray-900 md:text-5xl lg:text-6xl">Invités.</h1>

<div class="flex items-end justify-between">
    <p class="text-xl">
        Le meeting compte actuellement <%= meeting.getAttendances().size() %> invités.
    </p>
</div>

<div class="top-5 relative overflow-x-auto border-t border-r border-l">
    <form action="<%=request.getContextPath() + "/attendances/update?meetingId=" + meeting.getId() %>" method="post">
        <table class="mb-5 w-full text-left text-sm text-gray-500 rtl:text-right">
            <thead class="bg-gray-50 text-xs uppercase text-gray-700">
            <tr>
                <th scope="col" class="px-6 py-3">#ID</th>
                <th scope="col" class="px-6 py-3">Nom</th>
                <th scope="col" class="px-6 py-3">Email</th>
                <th scope="col" class="px-6 py-3">Téléphone</th>
                <th scope="col" class="px-6 py-3">Département</th>
                <th scope="col" class="px-6 py-3">Status</th>
            </tr>
            </thead>
            <tbody>
            <% for (Attendance attendance : meeting.getAttendances()) { %>

            <tr class="border-b bg-white hover:bg-gray-50">
                <th scope="row"
                    class="whitespace-nowrap px-6 py-4 font-medium text-gray-900"><%= attendance.getEmployee().getId() %>
                </th>
                <td class="px-6 py-4"><%= attendance.getEmployee().getFullName() %>
                </td>
                <td class="px-6 py-4"><%= attendance.getEmployee().getEmail() %>
                </td>
                <td class="px-6 py-4"><%= attendance.getEmployee().getPhone() %>
                </td>
                <td class="px-6 py-4"><%= attendance.getEmployee().getDepartment().getName() %>
                </td>
                <td>
                    <label for="<%= attendance.getEmployee().getId() %>" class="sr-only"></label>
                    <select class="px-6 py-4" id="<%= attendance.getEmployee().getId() %>" name="attendance_<%=attendance.getId()%>_status">
                        <option value="invited" <%= attendance.getStatus().equals("invited") ? "selected" : ""%>>Invité</option>
                        <option value="present" <%= attendance.getStatus().equals("present") ? "selected" : ""%>>Présent</option>
                        <option value="absent" <%= attendance.getStatus().equals("absent") ? "selected" : ""%>>Absent</option>
                    </select>
                </td>
            </tr>

            <% } %>
            </tbody>
        </table>

        <div class="flex justify-end">
            <a href="<%= request.getContextPath() + "/attendances?meetingId=" + meeting.getId()%>"
               class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-100 font-medium rounded-full text-sm px-4 py-2 me-2">
                Retour
            </a>
            <button type="submit"
                    class="me-2 rounded-full bg-gray-800 px-4 py-2 text-sm font-medium text-white hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300">
                Confirmer
            </button>
        </div>
    </form>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.js"></script>
</body>
</html>
