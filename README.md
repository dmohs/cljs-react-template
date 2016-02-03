# cljs-react-template

A Leiningen template for ClojureScript + dmohs/react.

Includes figwheel, devtools, and devcards for a great development experience.

## Usage

Install [Leiningen](http://leiningen.org/) (or use docker as shown below). Then, create a new project from this template:

```bash
lein new dmohs.cljs-react myself/my-project --to-dir my-project
```

```bash
cd my-project
```

Copy index.html into target:

```bash
lein resource
```

Serve the files:

```bash
ruby -run -ehttpd target -p8000
```

(possibly in another shell) Start Figwheel with the Devcards UI:

```bash
lein with-profile +devcards figwheel
```

Once compiling has finished, you should be able to see the Devcards UI here:

http://localhost:8000/

## Other Compilation Modes

Note: Do a clean before rebuilding in a different mode or things may break.

Run your application (without Devcards) with Figwheel's hot reloading:

```bash
lein with-profile +figwheel do clean, resource, figwheel
```

Create a deployable build:

```bash
lein with-profile deploy do clean, resource, cljsbuild once
```

## Dockerized

Instead of installing Leiningen, you can just use the [Clojure](https://hub.docker.com/_/clojure/) docker image:

```bash
docker pull clojure
```

then:

```bash
docker run --rm -it -w /work -v "$PWD":/work -v "$HOME"/.m2:/root/.m2 -p 3449:3449 \
  clojure lein figwheel
```
