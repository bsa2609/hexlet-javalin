@import org.example.hexlet.dto.Page
@import gg.jte.Content

@param Page page
@param Content content
@param Content footer = null

<html lang="ru">
    <head>
        <meta charset="utf-8" />
        @if(page.getDescription() != null)
            <meta name="description" content="${page.getDescription()}">
        @endif
        <title>${page.getTitle()}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
    </head>
    <body>
        <h1>${page.getTitle()}</h1>
        <nav class="nav">
            <a class="nav-link" href="/users">Users</a>
            <a class="nav-link" href="/courses">Courses</a>
            <a class="nav-link active" aria-current="page" href="/">Main</a>
        </nav>

        <div class="content">
            ${content}
        </div>
        @if (footer != null)
            <div class="footer">
                ${footer}
            </div>
        @endif

    </body>
</html>