<html>
<head><title>Create Person</title></head>
<body>
    <h2>Create New Person</h2>
    <form action="person" method="post">
        <input type="hidden" name="action" value="create" />
        <label>Name: <input type="text" name="name" /></label><br/>
        <label>Age: <input type="number" name="age" /></label><br/>
        <input type="submit" value="Create" />
    </form>
</body>
</html>
