<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<header class="flex justify-between p-3">
    <div class="font-bold text-gray-900 font-mono"><span class="text-blue-600">.</span>Schedular</div>

    <div class="flex justify-between gap-3">
        <a href="<%= request.getContextPath() + "/meetings" %>">Meetings</a>
        <a href="<%= request.getContextPath() + "/employees" %>">Employés</a>
        <a href="<%= request.getContextPath() + "/rooms" %>">Salles</a>
        <a href="<%= request.getContextPath() + "/departments" %>">Départements</a>
    </div>
</header>