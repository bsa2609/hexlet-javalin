@import org.example.hexlet.dto.Page
@import org.example.hexlet.dto.users.UsersPage
@import org.example.hexlet.util.NamedRoutes
@param UsersPage page

@template.layout.page(
page = new Page(),
content = @`
    <div style="margin-bottom: 10px"><a href="${NamedRoutes.buildUserPath()}">Создать пользователя</a></div>

    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">name</th>
                <th scope="col">e-mail</th>
            </tr>
        </thead>
        <tbody>
            @for(var user : page.getUsers())
                <tr>
                    <td><a href="${NamedRoutes.userPath(user.getId())}">${user.getId()}</a></td>
                    <td><a href="${NamedRoutes.userPath(user.getId())}">${user.getName()}</a></td>
                    <td><a href="${NamedRoutes.userPath(user.getId())}">${user.getEmail()}</a></td>
                </tr>
            @endfor
        </tbody>
    </table>
`
,
header = @`
    <ul class="nav nav-pills">
        <li class="nav-item">
            <a class="nav-link" aria-current="page" href="/">Main</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="${NamedRoutes.usersPath()}">Users</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${NamedRoutes.coursesPath()}">Course</a>
        </li>
    </ul>
`
,
footer = @`
    <div style="border: 1px solid grey; border-radius: 5px; padding: 0; margin: 0; padding-left: 20px; margin-top: 10px; height: 30px">
        <p style="color: grey; font-size: 1em">(c) 2024</p>
    </div>
`
)
