@import org.example.hexlet.dto.MainPage
@import org.example.hexlet.util.NamedRoutes
@param MainPage page

@template.layout.page(
page = page,
content = @`
    <h2>Hello! You are on my first website.</h2>

    @if(page.getCurrentUser() == null)
        <a href="${NamedRoutes.buildSessionsPath()}">Login</a>
    @else
        Добро пожаловать, ${page.getCurrentUser()}.
        Чтобы разлогиниться, удалите куку JSESSIONID из браузера
    @endif

    @if(!page.isVisited())
        Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
    @endif

`
,
footer = @`
    <div style="border: 1px solid grey; border-radius: 5px; padding: 0; margin: 0; padding-left: 20px; margin-top: 10px; height: 30px">
        <p style="color: grey; font-size: 1em">(c) 2024</p>
    </div>
`
)
