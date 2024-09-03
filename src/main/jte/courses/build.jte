<head>
    <meta charset="utf-8" />
    <title>New user</title>
</head>
<form action="/courses" method="post">
    <div>
        <label>
            Название
            <input type="text" name="name" />
        </label>
    </div>
    <div>
        <label>
            Описание
            <input type="text" required name="description" />
        </label>
    </div>
    <input type="submit" value="Создать" />
</form>
