<%@ page import="com.example.dao.*" %>
<%@ page import="com.example.model.*" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Lista de Usuários</title></head>
<body>
    <h2>Lista de Usuários</h2>
    <a href="create.jsp">Criar novo usuário</a>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Idade</th>
            <th>Ações</th>
        </tr>
        <%
            List<Person> persons = (List<Person>) request.getAttribute("persons");
        	
        	if(persons == null){
        		PersonDAO pd = new PersonDAOImpl();
            	persons = pd.getAllPersons();
        	}

        	for (Person person : persons) { 
        %>
		        <tr>
		            <td><%= person.getId() %></td>
		            <td><%= person.getName() %></td>
		            <td><%= person.getAge() %></td>
		            <td>
		                <a href="person?action=edit&id=<%= person.getId() %>">Editar</a>
		                <a href="person?action=delete&id=<%= person.getId() %>">Excluir</a>
		            </td>
		        </tr>
        <%
           	}
        %>
    </table>
    
</body>
</html>
