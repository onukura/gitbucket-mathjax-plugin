import gitbucket.core.controller.Context
import gitbucket.core.plugin.{RenderRequest, Renderer}
import gitbucket.core.service.RepositoryService.RepositoryInfo
import gitbucket.core.view.helpers.markdown

import play.twirl.api.Html

class MathjaxRenderer extends Renderer {

  def render(request: RenderRequest): Html = {
    import request._
    Html(toHtml(fileContent, branch, repository)(context))
  }

  def toHtml(fileContent: String,
             branch: String,
             repository: RepositoryInfo)(implicit context: Context): String = {

    val path = context.baseUrl
    val rendered = markdown(
      markdown = fileContent,
      repository = repository,
      branch = branch,
      enableWikiLink = false,
      enableRefsLink = true,
      enableAnchor = true,
      enableLineBreaks = true,
      enableTaskList = true,
      hasWritePermission = true
    )

    s"""
       |<script src="$path/plugin-assets/mathjax/mathjax-config.js"></script>
       |<script id="MathJax-script" async src="$path/plugin-assets/mathjax/v3.0.5/tex-chtml.js"></script>
       |$rendered
       |""".stripMargin

  }

  def shutdown(): Unit = {
  }

}
