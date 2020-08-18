import gitbucket.core.controller.Context
import gitbucket.core.plugin.{RenderRequest, Renderer}
import gitbucket.core.service.RepositoryService.RepositoryInfo
import gitbucket.core.view.helpers.markdown

import play.twirl.api.Html

class MathjaxRenderer extends Renderer {

  def render(request: RenderRequest): Html = {
    import request._
    Html(toHtml(fileContent, branch, repository, enableWikiLink, enableRefsLink)(context))
  }

  def toHtml(fileContent: String,
             branch: String,
             repository: RepositoryInfo,
             enableWikiLink: Boolean,
             enableRefsLink: Boolean)(implicit context: Context): String = {

    val path = context.baseUrl
    val rendered = markdown(fileContent, repository, branch, enableWikiLink, enableRefsLink, enableLineBreaks = true)

    s"""
       |<script src="$path/plugin-assets/mathjax/mathjax-config.js"></script>
       |<script id="MathJax-script" async src="$path/plugin-assets/mathjax/v3.0.5/tex-chtml.js"></script>
       |$rendered
       |""".stripMargin

  }

  def shutdown(): Unit = {
  }

}
