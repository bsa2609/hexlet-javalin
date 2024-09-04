@import org.example.hexlet.dto.Page
@import org.example.hexlet.dto.MainPage
@import org.example.hexlet.util.NamedRoutes
@param MainPage page

@template.layout.page(
page = new Page(),
content = @`
    <h2>Hello! You are on my first website.</h2>
    @if(!page.isVisited())
        Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
    @endif

`
,
header = @`
    <ul class="nav nav-pills">
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/">Main</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${NamedRoutes.usersPath()}">Users</a>
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
