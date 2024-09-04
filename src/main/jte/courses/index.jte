@import org.example.hexlet.dto.Page
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage coursesPage

@template.layout.page(
page = new Page(),
content = @`

    <form action="/courses" method="get">
        <input type="search" name="term" value="${coursesPage.getTerm()}" />
        <input type="submit" value="Search" />
    </form>

    <div><a href="courses/build">New course</a></div>

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
                    <td>${course.getId()}</td>
                    <td>${course.getName()}</td>
                    <td>${course.getDescription()}</td>
                </tr>
            @endfor
        </tbody>
    </table>

`
,
footer = @`
    <p>You are currently viewing the list of courses!</p>
`
)
