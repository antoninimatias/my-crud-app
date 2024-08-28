<%@ page import="com.example.model.Person" %>
<html>
<head><title>Update Person</title></head>
<body>
    <h2>Update Person</h2>
    <%
        // Obtém o objeto Person da requisição
        Person person = (Person) request.getAttribute("person");

        // Verifica se o objeto person é null
        if (person == null) {
            out.println("Person not found");
            return;
        }
    %>
    
    <form action="person" method="post">
    
        <input type="hidden" name="action" value="update" />
        <input type="hidden" name="id" value="<%= person.getId() %>" />
        <label>Name: <input type="text" name="name" value="<%= person.getName() %>" /></label><br/>
        <label>Age: <input type="number" name="age" value="<%= person.getAge() %>" /></label><br/>
        <input type="submit" value="Update" />
    
    </form>
</body>
</html>
