<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="popup" class="hidden fixed inset-0 bg-black bg-opacity-50 z-[1] flex items-center justify-center">
    <div class="bg-white rounded-lg shadow-lg max-w-md text-center">
        <div class="h-5 rounded-t-lg bg-red-700 mb-4"></div>
        <div class="p-6">
            <p id="popup-description" class="mb-6 text-lg"></p>
            <button id="confirm-delete" class="mb-2 me-2 rounded-full border border-red-700 px-4 py-2 text-center text-sm font-medium text-red-700 hover:bg-red-800 hover:text-white focus:outline-none focus:ring-4 focus:ring-red-300 dark:border-red-500 dark:text-red-500 dark:hover:bg-red-600 dark:hover:text-white dark:focus:ring-red-900">Oui, supprimer</button>
            <button id="cancel-delete" class="mb-2 me-2 rounded-full bg-gray-800 px-4 py-2 text-sm font-medium text-white hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 dark:border-gray-700 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700">Annuler</button>
        </div>
    </div>
</div>

<script>
    function showPopup() {
        document.getElementById('popup').classList.remove('hidden');
    }

    function hidePopup() {
        document.getElementById('popup').classList.add('hidden');
    }

    function setPopupDescription(description) {
        document.getElementById('popup-description').textContent = description;
    }

    function setDeleteHandler(handler) {
        document.getElementById('confirm-delete').addEventListener("click", handler);
        document.getElementById('cancel-delete').addEventListener("click", () => {
            hidePopup();
            document.getElementById('confirm-delete').removeEventListener("click", handler);
        });
    }
</script>