@import org.example.hexlet.dto.BasePage
@import gg.jte.Content
@import org.example.hexlet.util.NamedRoutes

@param BasePage page = null
@param Content content
@param Content footer = null

<html lang="ru">
    <head>
        <meta charset="utf-8" />
        @if(page != null && page.getTitle() != null &&!page.getTitle().isBlank())
            <title>${page.getTitle()}</title>
        @endif
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
    </head>
    <body>
    @if(page != null && page.getTitle() != null &&!page.getTitle().isBlank())
            <h1>${page.getTitle()}</h1>
        @endif
        <div class="header">
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <a class="nav-link ${page != null && page.getCurrentPage() != null && page.getCurrentPage().equals("Main") ? "active" : ""}"
                       href="/">Main</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${page != null && page.getCurrentPage() != null && page.getCurrentPage().equals("Users") ? "active" : ""}"
                       href="${NamedRoutes.usersPath()}">Users</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${page != null && page.getCurrentPage() != null && page.getCurrentPage().equals("Courses") ? "active" : ""}"
                       href="${NamedRoutes.coursesPath()}">Courses</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${page != null && page.getCurrentPage() != null && page.getCurrentPage().equals("Cars") ? "active" : ""}"
                       href="${NamedRoutes.carsPath()}">Cars</a>
                </li>
            </ul>
        </div>
        @if(page != null && page.getFlash() != null)
            <div class="alert alert-success" role="alert">
                ${page.getFlash()}
            </div>
        @endif
        <div class="content">
            ${content}
        </div>
        @if (footer != null)
            <div class="footer" style="margin: 0; padding: 0; border: 0">
                ${footer}
            </div>
        @endif

    </body>
</html>