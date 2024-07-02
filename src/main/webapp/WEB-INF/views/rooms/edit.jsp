<%@ page import="com.project.l3.schedular.model.MeetingRoom" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MeetingRoom room = (MeetingRoom) request.getAttribute("room");
%>
<html lang="fr">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.css" rel="stylesheet"/>

    <title>Nouvelle salle</title>
</head>
<body>

<main class="px-3 py-5 sm:px-40 md:px-60 lg:px-80">
    <h1 class="text-3xl font-medium">Modifier une salle</h1>
    <p class="mb-5 text-slate-600">Veuillez remplir les champs
        suivants pour modifier la salle</p>

    <form action="update" method="post">
        <div class="mb-6 flex flex-col gap-5">
            <input name="id"  value="<%= room.getId() %>" hidden="hidden">

            <div>
                <label for="name"
                       class="mb-2 block text-sm font-medium text-gray-900">Nom de la salle</label>
                <input type="text" id="name" name="name"
                       value="<%= room.getName() %>"
                       class="bg-gray block w-full rounded-lg border border-gray-300 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500"
                       placeholder="Salle de conférence A" required/>
            </div>

            <div>
                <label for="location"
                       class="mb-2 block text-sm font-medium text-gray-900">Localisation de la salle</label>
                <input type="text" id="location" name="location"
                       value="<%= room.getLocation() %>"
                       class="bg-gray block w-full rounded-lg border border-gray-300 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500"
                       placeholder="Bâtiment 1, Etage 3, Salle 305" required/>
            </div>

            <div>
                <label for="capacity"
                       class="mb-2 block text-sm font-medium text-gray-900">Capacité de la salle</label>
                <input type="number" id="capacity" name="capacity"
                       value="<%= room.getCapacity() %>"
                       class="bg-gray block w-full rounded-lg border border-gray-300 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500"
                       placeholder="10" required/>
            </div>
        </div>

        <div class="flex justify-end">
            <a href="<%= request.getContextPath() + "/rooms"%>"
               class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-100 font-medium rounded-full text-sm px-4 py-2 me-2">
                Retour
            </a>
            <button type="submit"
                    class="me-2 rounded-full bg-gray-800 px-4 py-2 text-sm font-medium text-white hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300">
                Sauvegarder
            </button>
        </div>

    </form>
</main>

<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/flowbite.min.js"></script>
</body>
</html>
