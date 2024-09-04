@import org.example.hexlet.dto.users.UserPage
@param UserPage page

@template.layout.page(
page = page,
content = @`
    <h1>${page.getUser().getName()}</h1>
    <p>Эл. адрес: ${page.getUser().getEmail()}</p>
    <p>На сайте с: ${String.valueOf(page.getUser().getCreatedAt().toLocalDate()) + " "
        + page.getUser().getCreatedAt().getHour() + ":"
        + page.getUser().getCreatedAt().getMinute() + ":"
        + page.getUser().getCreatedAt().getSecond()}</p>
`
,
footer = @`
    <div style="border: 1px solid grey; border-radius: 5px; padding: 0; margin: 0; padding-left: 20px; margin-top: 10px; height: 30px">
        <p style="color: grey; font-size: 1em">(c) 2024</p>
    </div>
`
)
