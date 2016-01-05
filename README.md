# cljs-react-template

A Leiningen template for ClojureScript + dmohs/react.

Includes figwheel, devtools, and devcards for a great development experience.

## Usage

Install [Leiningen](http://leiningen.org/). Then, create a new project from this template:

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

(possibly in another shell) Start Figwheel:

```bash
lein figwheel
```

Once compiling has finished, you should be able to see the Devcards UI here:

http://localhost:8000/
