<%@ page import="java.util.List" %>
<%@ page import="com.project.l3.schedular.model.Employee" %>
<%@ page import="com.project.l3.schedular.model.MeetingRoom" %>
<%@ page import="com.project.l3.schedular.model.Meeting" %>
<%@ page import="com.project.l3.schedular.util.DateFormater" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    List<MeetingRoom> rooms = (List<MeetingRoom>) request.getAttribute("rooms");
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

<main class="px-3 py-5 sm:px-40 md:px-60 lg:px-80">
    <h1 class="text-3xl font-medium">Ajouter un meeting</h1>
    <p class="mb-5 text-slate-600">Veuillez remplir les champs
        suivants pour ajouter un meeting</p>

    <form action="update" method="post">
        <div class="mb-6 flex flex-col gap-5">
            <input hidden="hidden" name="id" value="<%= meeting.getId() %>">

            <%-- The meeting date --%>
            <div>
                <label for="date"
                       class="mb-2 block text-sm font-medium text-gray-900">Date du meeting</label>
                <input type="date" id="date" name="date"
                       value=<%= DateFormater.format(meeting.getStartTime(), "yyyy-MM-dd") %>
                       class="bg-gray block w-full rounded-lg border border-gray-300 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500"
                       required/>
            </div>

            <%-- The meeting start and end time --%>
            <div class="flex gap-2">
                <div>
                    <label for="startTime" class="block mb-2 text-sm font-medium text-gray-900">Le meeeting commence à
                        :</label>
                    <div class="relative">
                        <div class="absolute inset-y-0 end-0 top-0 flex items-center pe-3.5 pointer-events-none">
                            <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true"
                                 xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 24 24">
                                <path fill-rule="evenodd"
                                      d="M2 12C2 6.477 6.477 2 12 2s10 4.477 10 10-4.477 10-10 10S2 17.523 2 12Zm11-4a1 1 0 1 0-2 0v4a1 1 0 0 0 .293.707l3 3a1 1 0 0 0 1.414-1.414L13 11.586V8Z"
                                      clip-rule="evenodd"/>
                            </svg>
                        </div>
                        <input type="time" id="startTime" name="startTime"
                               class="bg-gray-50 border leading-none border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                               value="<%= DateFormater.formatToHour(meeting.getStartTime()) %>"
                               min="06:00" max="18:00" required/>
                    </div>
                </div>

                <div>
                    <label for="endTime" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                        Le meeeting se termine à :
                    </label>
                    <div class="relative">
                        <div class="absolute inset-y-0 end-0 top-0 flex items-center pe-3.5 pointer-events-none">
                            <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true"
                                 xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 24 24">
                                <path fill-rule="evenodd"
                                      d="M2 12C2 6.477 6.477 2 12 2s10 4.477 10 10-4.477 10-10 10S2 17.523 2 12Zm11-4a1 1 0 1 0-2 0v4a1 1 0 0 0 .293.707l3 3a1 1 0 0 0 1.414-1.414L13 11.586V8Z"
                                      clip-rule="evenodd"/>
                            </svg>
                        </div>
                        <input type="time" id="endTime" name="endTime"
                               class="bg-gray-50 border leading-none border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                               value="<%= DateFormater.formatToHour(meeting.getEndTime()) %>"
                               min="06:00" max="18:00" required/>
                    </div>
                </div>
            </div>

            <%-- The meeting room --%>
            <div>
                <label for="employeeId"
                       class="mb-2 block text-sm font-medium text-gray-900">Salle de réunion</label>
                <select id="employeeId" name="roomId"
                        required
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
                    <%
                        for (MeetingRoom room : rooms) {
                    %>
                    <option value="<%=room.getId()%>"
                        <%= meeting.getRoom().getId() == room.getId() ? "selected" : "" %>
                    >
                        <%=room.getName()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>

            <%-- The meeting organizer --%>
            <div>
                <label for="employeeId"
                       class="mb-2 block text-sm font-medium text-gray-900">Organisateur</label>
                <select id="roomId" name="employeeId"
                        required
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
                    <%
                        for (Employee employee : employees) {
                    %>
                    <option
                            value="<%=employee.getId()%>"
                            <%= meeting.getOrganizer().getId() == employee.getId() ? "selected" : "" %>
                    >
                        <%= employee.getFullName()%> - <%=employee.getDepartment().getName() %>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>

        </div>

        <div class="flex justify-end">
            <a href="<%= request.getContextPath() + "/meetings"%>"
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
