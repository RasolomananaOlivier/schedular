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

    <title>Modifier département</title>
</head>
<body>

<main class="px-3 py-5 sm:px-40 md:px-60 lg:px-80">
    <h1 class="text-3xl font-medium">Modifier un département</h1>
    <p class="mb-5 text-slate-600">Veuillez remplir les champs
        suivants pour modifier le département</p>

    <form action="update" method="post">
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
            <button type="submit"
                    class="w-full rounded-full bg-blue-700 px-5 py-2.5 text-center text-sm font-medium text-white hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 sm:w-auto dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                Confirmer
            </button>
        </div>

    </form>
</main>

<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.js"></script>
</body>
</html>
