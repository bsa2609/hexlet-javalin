@import org.example.hexlet.dto.Page
@import org.example.hexlet.dto.users.UserPage
@import org.example.hexlet.util.NamedRoutes
@import java.time.format.DateTimeFormatter
@param UserPage userPage

@template.layout.page(
page = new Page(),
content = @`
    <h1>${userPage.getUser().getName()}</h1>
    <p>Эл. адрес: ${userPage.getUser().getEmail()}</p>
    <p>На сайте с: ${String.valueOf(userPage.getUser().getCreatedAt().toLocalDate()) + " "
        + userPage.getUser().getCreatedAt().getHour() + ":"
        + userPage.getUser().getCreatedAt().getMinute() + ":"
        + userPage.getUser().getCreatedAt().getSecond()}</p>
`
,
header = @`
    <ul class="nav nav-pills">
        <li class="nav-item">
            <a class="nav-link" aria-current="page" href="/">Main</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${NamedRoutes.usersPath()}">Users</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${NamedRoutes.coursesPath()}">Courses</a>
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
