@import org.example.hexlet.dto.Page
@import gg.jte.Content

@param Page page
@param Content content
@param Content footer = null

<head>
    <meta charset="utf-8" />
    @if(page.getDescription() != null)
        <meta name="description" content="${page.getDescription()}">
    @endif
    <title>${page.getTitle()}</title>
</head>
<body>
<h1>${page.getTitle()}</h1>
<p>
    <a href="/users">Users</a>
    <a href="/courses">Courses</a>
    <a href="/">Main</a>
</p>
<div class="content">
    ${content}
</div>
@if (footer != null)
    <div class="footer">
        ${footer}
    </div>
@endif
</body>
