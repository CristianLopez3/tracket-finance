<%@ page import="com.finance.tracket.models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: cristian
  Date: 5/15/24
  Time: 7:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Users</title>
</head>
<body>

<div class="container bg-gray-900  flex justify-center items-center p-24">


    <div>
        <%
            List<User> users = (List) request.getSession().getAttribute("users");
            if (users != null) {
                for (User user : users) {
        %>
        <ul class="max-w-md divide-y divide-gray-200 dark:divide-gray-700">
            <li class="pb-3 sm:pb-4">
                <div class="flex items-center space-x-4 rtl:space-x-reverse">
                    <div class="flex-shrink-0">
                        <p class="w-8 h-8 rounded-full bg-gray-700">
                    </div>
                    <div class="flex-1 min-w-0">
                        <p class="text-sm font-medium text-gray-900 truncate dark:text-white">
                            <%= user.getName() %>
                        </p>
                        <p class="text-sm text-gray-500 truncate dark:text-gray-400">
                            <%= user.getPosition() %>
                        </p>
                    </div>
                    <div class="inline-flex items-center text-base font-semibold text-gray-900 dark:text-white">
                        100%
                    </div>
                </div>
            </li>
        </ul>
        <% }
        } %>

    </div>

</div>


</body>
</html>
