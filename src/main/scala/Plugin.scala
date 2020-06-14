import javax.servlet.ServletContext
import gitbucket.core.plugin.{PluginRegistry}
import gitbucket.core.service.SystemSettingsService
import gitbucket.core.service.SystemSettingsService.SystemSettings
import io.github.gitbucket.solidbase.model.Version

import scala.util.Try

class Plugin extends gitbucket.core.plugin.Plugin {
  override val pluginId: String = "mathjax"
  override val pluginName: String = "Markdown with mathjax renderer Plugin"
  override val description: String = "Rendering Markdown files with mathjax."
  override val versions: List[Version] = List(
    new Version("1.0.0"),
    new Version("1.0.1"),
  )

  override val assetsMappings = Seq("/mathjax" -> "/mathjax/assets")

  private[this] var renderer: Option[MathjaxRenderer] = None

  override def initialize(registry: PluginRegistry, context: ServletContext, settings: SystemSettingsService.SystemSettings): Unit = {
    val test = Try{ new MathjaxRenderer() }
    val mathjax = test.get
    registry.addRenderer("md", mathjax)
    renderer = Option(mathjax)
    super.initialize(registry, context, settings)
  }

  override def shutdown(registry: PluginRegistry, context: ServletContext, settings: SystemSettings): Unit = {
    renderer.map(r => r.shutdown())
  }

}