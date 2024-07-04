<%@ page import="java.util.List" %>
<%@ page import="com.project.l3.schedular.model.Employee" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
%>

<!doctype html>
<html lang="fr">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.css" rel="stylesheet"/>

    <title>Employées</title>
</head>
<body class="py-4 px-5">
<jsp:include page="/WEB-INF/components/header.jsp"/>
<h1 class="mb-4 text-3xl font-extrabold text-gray-900 md:text-5xl lg:text-6xl">Employées.</h1>

<div class="flex items-end justify-between">
    <p class="text-xl">
        L'entreprise compte actuellement <%= employees.size() %> employés.
    </p>

    <a href="<%= request.getContextPath() + "/employees/new" %>"
       class="me-2 rounded-full bg-gray-800 px-4 py-2 text-sm font-medium text-white hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 dark:border-gray-700 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700">
        Nouveau employé
    </a>
</div>

<div class="top-5 relative overflow-x-auto border-t border-r border-l">
    <table class="w-full text-left text-sm text-gray-500 rtl:text-right">
        <thead class="bg-gray-50 text-xs uppercase text-gray-700">
        <tr>
            <th scope="col" class="px-6 py-3">#ID</th>
            <th scope="col" class="px-6 py-3">Nom</th>
            <th scope="col" class="px-6 py-3">Email</th>
            <th scope="col" class="px-6 py-3">Téléphone</th>
            <th scope="col" class="px-6 py-3">Département</th>
            <th scope="col" class="px-6 py-3">
                <span class="sr-only">Edit</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <% for (Employee employee : employees) { %>

        <tr class="border-b bg-white hover:bg-gray-50">
            <th scope="row" class="whitespace-nowrap px-6 py-4 font-medium text-gray-900"><%= employee.getId() %>
            </th>
            <td class="px-6 py-4"><%= employee.getFullName() %>
            </td>
            <td class="px-6 py-4"><%= employee.getEmail() %>
            </td>
            <td class="px-6 py-4"><%= employee.getPhone() %>
            </td>
            <td class="px-6 py-4"><%= employee.getDepartment().getName() %>
            </td>

            <td class="flex justify-end px-6 py-4 text-right">
                <a href="<%= request.getContextPath() + "/employees/delete?employeeId=" + employee.getId() %>"
                   class="mb-2 me-2 rounded-full border border-red-700 px-4 py-2 text-center text-sm font-medium text-red-700 hover:bg-red-800 hover:text-white focus:outline-none focus:ring-4 focus:ring-red-300 dark:border-red-500 dark:text-red-500 dark:hover:bg-red-600 dark:hover:text-white dark:focus:ring-red-900">Supprimer</a>
                <a href="<%= request.getContextPath() + "/employees/edit?employeeId=" + employee.getId() %>"
                   class="mb-2 me-2 rounded-full bg-gray-800 px-4 py-2 text-sm font-medium text-white hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 dark:border-gray-700 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700">Modifier</a>
            </td>
        </tr>

        <% } %>
        </tbody>
    </table>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.js"></script>
</body>
</html>
