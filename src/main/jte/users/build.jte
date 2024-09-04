@import org.example.hexlet.dto.users.BuildUserPage
@param BuildUserPage page

<html lang="ru">
    <head>
        <title>Регистрация пользователя</title>
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

        <form action="/users" method="post">
            <div class="mb-3">
                <label class="form-label">
                    Имя
                    <input type="text" name="name" value="${page.getName()}" class="form-control" />
                </label>
            </div>
            <div class="mb-3">
                <label class="form-label">
                    Email
                    <input type="email" required name="email" value="${page.getEmail()}" class="form-control" />
                </label>
            </div>
            <div class="mb-3">
                <label class="form-label">
                    Пароль
                    <input type="password" required name="password" class="form-control" />
                </label>
            </div>
            <div class="mb-3">
                <label class="form-label">
                    Подтверждение пароля
                    <input type="password" required name="passwordConfirmation" class="form-control" />
                </label>
            </div>
            <button type="submit" class="btn btn-primary">Зарегистрировать</button>
        </form>
    </body>
</html>
