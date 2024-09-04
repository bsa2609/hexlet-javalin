@import org.example.hexlet.dto.courses.BuildCoursePage
@import org.example.hexlet.util.NamedRoutes
@param BuildCoursePage page

<html lang="ru">
    <head>
        <title>Создание курса</title>
        <meta charset="utf-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
        <style>
            div {
                margin: 10px;
            }
            button {
                margin: 10px;
            }
        </style>
    </head>
    <body>
        @if(page.getErrors() != null)
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif

        <form action="${NamedRoutes.coursesPath()}" method="post">
            <div class="mb-3">
                <label class="form-label">
                    Название
                    <input type="text" name="name" value="${page.getName()}" class="form-control" />
                </label>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Описание</label>
                <textarea id="description" name="description" class="form-control">${page.getDescription()}</textarea>
            </div>
            <button type="submit" class="btn btn-primary">Создать</button>
        </form>
    </body>
</html>
