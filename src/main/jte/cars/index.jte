@import org.example.hexlet.dto.cars.CarsPage
@import org.example.hexlet.util.NamedRoutes
@param CarsPage page

@template.layout.page(
page = page,
content = @`
    <div style="margin-bottom: 10px"><a href="${NamedRoutes.buildCarPath()}">Создать автомобиль</a></div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">make</th>
            <th scope="col">model</th>
        </tr>
        </thead>
        <tbody>
        @for(var car : page.getCars())
            <tr>
                <td><a href="${NamedRoutes.carPath(car.getId())}">${car.getId()}</a></td>
                <td><a href="${NamedRoutes.carPath(car.getId())}">${car.getMake()}</a></td>
                <td><a href="${NamedRoutes.carPath(car.getId())}">${car.getModel()}</a></td>
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
