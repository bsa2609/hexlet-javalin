@import org.example.hexlet.dto.Page
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage coursesPage

@template.layout.page(
page = new Page(),
content = @`
    @for(var course : coursesPage.getCourses())
        <div>${course.getName()}</div>
    @endfor
`
,
footer = @`
    <p>You are currently viewing the list of courses!</p>
`
)
