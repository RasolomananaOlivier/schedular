<%@ page import="java.util.List" %>
<%@ page import="com.project.l3.schedular.model.Employee" %>
<%@ page import="com.project.l3.schedular.model.Meeting" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    Meeting meeting = (Meeting) request.getAttribute("meeting");
%>

<html lang="fr">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.css" rel="stylesheet"/>

    <title>Nouveau meeting</title>
</head>
<body>
<jsp:include page="/WEB-INF/components/header.jsp" />

<main class="px-3 py-5 sm:px-40 md:px-60 lg:px-80">
    <h1 class="text-3xl font-medium">Ajouter un sujet de discussion</h1>
    <p class="mb-5 text-slate-600">Veuillez remplir les champs
        suivants pour ajouter un sujet de discussion</p>

    <form action="store" method="post">
        <div class="mb-6 flex flex-col gap-5">
            <input hidden="hidden" name="meetingId" value="<%= meeting.getId() %>">

            <%--The agenda topic --%>
            <div>
                <label for="description"
                       class="mb-2 block text-sm font-medium text-gray-900">Thème</label>
                <input type="text" id="description" name="description"
                       class="bg-gray block w-full rounded-lg border border-gray-300 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500"
                       required/>
            </div>

            <%-- The topic presenter --%>
                <div>
                    <label for="presenterId"
                           class="mb-2 block text-sm font-medium text-gray-900">Présentateur</label>
                    <select id="presenterId" name="presenterId"
                            required
                            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
                        <%
                            for (Employee employee : employees) {
                        %>
                        <option value="<%=employee.getId()%>">
                            <%= employee.getFullName()%> - <%=employee.getDepartment().getName() %>
                        </option>
                        <%
                            }
                        %>
                    </select>
                </div>

        </div>

        <div class="flex justify-end">
            <a href="<%= request.getContextPath() + "/agendas?meetingId=" +  meeting.getId() %>"
               class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-100 font-medium rounded-full text-sm px-4 py-2 me-2">
                Retour
            </a>
            <button type="submit"
                    class="me-2 rounded-full bg-gray-800 px-4 py-2 text-sm font-medium text-white hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300">
                Confirmer
            </button>
        </div>

    </form>
</main>

<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.js"></script>
</body>
</html>
