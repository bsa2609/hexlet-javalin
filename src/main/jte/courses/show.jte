@import org.example.hexlet.dto.courses.CoursePage
@param CoursePage page

<html>
<head>
    <title>Хекслет</title>
    <meta charset="utf-8" />
</head>
<body>
<main>
    <h1>${page.getCourse().getName()}</h1>
    <p>${page.getCourse().getDescription()}</p>
</main>
</body>
</html>
