<%--
  Created by IntelliJ IDEA.
  User: olivier
  Date: 7/2/24
  Time: 9:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.css" rel="stylesheet"/>

    <title>Nouveau département</title>
</head>
<body>

<main class="px-3 py-5 sm:px-40 md:px-60 lg:px-80">
    <h1 class="text-3xl font-medium">Ajouter un département</h1>
    <p class="mb-5 text-slate-600">Veuillez remplir les champs
        suivants pour ajouter un département</p>

    <form action="store" method="post">
        <div class="mb-6 flex flex-col gap-5">
            <div>
                <label for="departmentName"
                       class="mb-2 block text-sm font-medium text-gray-900">Nom du département</label>
                <input type="text" id="departmentName" name="name"
                       class="bg-gray block w-full rounded-lg border border-gray-300 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500"
                       placeholder="ex: Informatique" required/>
            </div>
        </div>

        <div class="flex justify-end">
            <a href="<%= request.getContextPath() + "/departments"%>"
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
