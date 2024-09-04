@import org.example.hexlet.dto.Page
@import org.example.hexlet.dto.users.UsersPage
@import org.example.hexlet.util.NamedRoutes
@param UsersPage usersPage

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
            @for(var user : usersPage.getUsers())
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
footer = @`
    <div style="border: 1px solid grey; border-radius: 5px; padding: 0; margin: 0; padding-left: 20px; margin-top: 10px; height: 30px">
        <p style="color: grey; font-size: 1em">(c) 2024</p>
    </div>
`
)
