import gitbucket.core.controller.Context
import gitbucket.core.plugin.{RenderRequest, Renderer}
import gitbucket.core.service.RepositoryService.RepositoryInfo
import gitbucket.core.view.helpers.markdown

import play.twirl.api.Html

class MathjaxRenderer extends Renderer {

  def render(request: RenderRequest): Html = {
    import request._
    Html(toHtml(filePath, fileContent, branch, repository, enableWikiLink, enableRefsLink, enableAnchor)(context))
  }

  def toHtml(
              filePath: List[String],
              fileContent: String,
              branch: String,
              repository: RepositoryInfo,
              enableWikiLink: Boolean,
              enableRefsLink: Boolean,
              enableAnchor: Boolean)(implicit context: Context): String = {

    val path = context.baseUrl
    val rendered = markdown(fileContent, repository, branch, enableWikiLink, enableRefsLink, enableLineBreaks = true)

    s"""<script src="$path/plugin-assets/mathjax/mathjax-config.js"></script>
       <script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>
       <script type="text/javascript" id="MathJax-script" async
         src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-chtml.js">
       </script>$rendered"""

  }

  def shutdown(): Unit = {
  }

}
