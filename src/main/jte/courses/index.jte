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

    @for(var course : coursesPage.getCourses())
        <div>${course.getName()}</div>
    @endfor
`
,
footer = @`
    <p>You are currently viewing the list of courses!</p>
`
)
