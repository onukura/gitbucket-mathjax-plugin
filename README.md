# gitbucket-mathjax-plugin

[![Build Status](https://travis-ci.org/onukura/gitbucket-mathjax-plugin.svg?branch=master)](https://travis-ci.org/onukura/gitbucket-mathjax-plugin)

A GitBucket plugin for rendering Markdown file with MathJax.

## Screenshot

![screenshot](https://github.com/onukura/gitbucket-mathjax-plugin/blob/assets/screenshot.png?raw=true)


## Install

1. Download *.jar from Releases.
2. Deploy it to `GITBUCKET_HOME/plugins`.
3. Restart GitBucket.

## Build from source

```sbt
sbt clean package
```

The built package is located at
`target/scala-2.13/gitbucket-mathjax-plugin_2.13-{plugin-version}.jar`.

```sbt
sbt assembly
```

This makes the assembly package
`target/scala-2.13/gitbucket-mathjax-plugin-{plugin-version}.jar`
for deployment.

## Note

MathJax and Markdown syntax would conflict in some cases. You need to modify your `.md` file a little bit to solve it. It mentioned at [issue](https://github.com/onukura/gitbucket-mathjax-plugin/issues/1). See also [MathJax Doc.](http://docs.mathjax.org/en/latest/input/tex/html.html?highlight=markdown#interactions-with-content-management-systems) for more details.

## Version

Plugin version|GitBucket version
:---|:---
1.0.x |4.32.x -
