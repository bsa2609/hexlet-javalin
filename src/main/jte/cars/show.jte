@import org.example.hexlet.dto.cars.CarPage
@param CarPage page

@template.layout.page(
page = page,
content = @`
    <h1>${page.getCar().getMake()}</h1>
    <p>${page.getCar().getModel()}</p>
`
,
footer = @`
    <div style="border: 1px solid grey; border-radius: 5px; padding: 0; margin: 0; padding-left: 20px; margin-top: 10px; height: 30px">
        <p style="color: grey; font-size: 1em">(c) 2024</p>
    </div>
`
)
