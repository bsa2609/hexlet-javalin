@import org.example.hexlet.dto.Page
@import org.example.hexlet.dto.courses.CoursesPage
@import org.example.hexlet.util.NamedRoutes
@param CoursesPage coursesPage

@template.layout.page(
page = new Page(),
content = @`
    <div style="margin-bottom: 10px"><a href="${NamedRoutes.buildCoursePath()}">Создать курс</a></div>

    <form action="${NamedRoutes.coursesPath()}" method="get">
        <input type="search" name="term" value="${coursesPage.getTerm()}" />
        <input type="submit" value="Search" />
    </form>

    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">name</th>
                <th scope="col">description</th>
            </tr>
        </thead>
        <tbody>
            @for(var course : coursesPage.getCourses())
                <tr>
                    <td><a href="${NamedRoutes.coursePath(course.getId())}">${course.getId()}</a></td>
                    <td><a href="${NamedRoutes.coursePath(course.getId())}">${course.getName()}</a></td>
                    <td><a href="${NamedRoutes.coursePath(course.getId())}">${course.getDescription()}</a></td>
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
