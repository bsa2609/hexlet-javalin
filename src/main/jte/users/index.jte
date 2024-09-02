@import org.example.hexlet.dto.Page
@import org.example.hexlet.dto.users.UsersPage
@param UsersPage usersPage

@template.layout.page(
page = new Page(),
content = @`
    @for(var user : usersPage.getUsers())
        <div>${user.getName()}</div>
    @endfor
`
,
footer = @`
    <p>You are currently viewing the list of users!</p>
`
)
