<%@ page import="com.project.l3.schedular.model.Department" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.l3.schedular.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Department> departments = (List<Department>) request.getAttribute("departments");
    Employee employee = (Employee) request.getAttribute("employee");
%>
<html lang="fr">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.css" rel="stylesheet"/>

    <title>Modifier un employé</title>
</head>
<body>
<jsp:include page="/WEB-INF/components/header.jsp" />

<main class="px-3 py-5 sm:px-40 md:px-60 lg:px-80">
    <h1 class="text-3xl font-medium">Modifier un employé</h1>
    <p class="mb-5 text-slate-600">Veuillez remplir les champs
        suivants pour modifier un employé</p>

    <form action="update" method="post">
        <div class="mb-6 flex flex-col gap-5">
            <input name="id" value="<%= employee.getId() %>" hidden="hidden">

            <div>
                <label for="firstName"
                       class="mb-2 block text-sm font-medium text-gray-900">Nom de l'employé</label>
                <input type="text" id="firstName" name="firstName"
                       value="<%= employee.getFirstName() %>"
                       class="bg-gray block w-full rounded-lg border border-gray-300 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500"
                       placeholder="John" required/>
            </div>

            <div>
                <label for="lastName"
                       class="mb-2 block text-sm font-medium text-gray-900">Prénom de l'employé</label>
                <input type="text" id="lastName" name="lastName"
                       value="<%= employee.getLastName() %>"
                       class="bg-gray block w-full rounded-lg border border-gray-300 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500"
                       placeholder="Doe" required/>
            </div>

            <div>
                <label for="email"
                       class="mb-2 block text-sm font-medium text-gray-900">Email de l'employé</label>
                <input type="email" id="email" name="email"
                       value="<%= employee.getEmail() %>"
                       class="bg-gray block w-full rounded-lg border border-gray-300 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500"
                       placeholder="john@doe.com" required/>
            </div>

            <div>
                <label for="phone"
                       class="mb-2 block text-sm font-medium text-gray-900">Numéro de téléphone de l'employé</label>
                <input type="tel" id="phone" name="phone"
                       value="<%= employee.getPhone() %>"
                       class="bg-gray block w-full rounded-lg border border-gray-300 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500"
                       placeholder="0346632470" required/>
            </div>

            <div>
                <label for="departmentId"
                       class="mb-2 block text-sm font-medium text-gray-900">Département</label>
                <select id="departmentId" name="departmentId"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
                    <%
                        for (Department department : departments) {
                    %>
                    <option
                            <%= employee.getDepartment().getId() == department.getId() ? "selected" : "" %>
                            value="<%=department.getId()%>">
                        <%=department.getName()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>
        </div>

        <div class="flex justify-end">
            <a href="<%= request.getContextPath() + "/employees"%>"
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
