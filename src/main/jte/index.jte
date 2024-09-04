@import org.example.hexlet.dto.Page

@template.layout.page(
page = new Page(),
content = @`
    <h2>Hello! You are on my first website.</h2>
`
,
footer = @`
    <div style="border: 1px solid grey; border-radius: 5px; padding: 0; margin: 0; padding-left: 20px; margin-top: 10px; height: 30px">
        <p style="color: grey; font-size: 1em">(c) 2024</p>
    </div>
`
)
