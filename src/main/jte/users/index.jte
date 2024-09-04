@import org.example.hexlet.dto.Page
@import org.example.hexlet.dto.users.UsersPage
@param UsersPage usersPage

@template.layout.page(
page = new Page(),
content = @`
    <div><a href="users/build">New user</a></div>

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
                    <td>${user.getId()}</td>
                    <td>${user.getName()}</td>
                    <td>${user.getEmail()}</td>
                </tr>
            @endfor
        </tbody>
    </table>
`
,
footer = @`
    <p>You are currently viewing the list of users!</p>
`
)
