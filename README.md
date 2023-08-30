<img src="https://raw.githubusercontent.com/Blamer-io/blamer/main/blamer-hub.svg" width="150" alt="blamer-bot"/>

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](https://www.rultor.com/b/blamer-io/hub)](https://www.rultor.com/p/Blamer-io/hub)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)
<br>

[![mvn](https://github.com/Blamer-io/bot/actions/workflows/mvn.yaml/badge.svg)](https://github.com/Blamer-io/bot/actions/workflows/mvn.yaml)
[![codecov](https://codecov.io/gh/Blamer-io/hub/branch/master/graph/badge.svg?token=5HCTK3KRGL)](https://codecov.io/gh/Blamer-io/hub)

[![Hits-of-Code](https://hitsofcode.com/github/Blamer-io/hub)](https://hitsofcode.com/view/github/Blamer-io/hub)
[![Lines-of-Code](https://tokei.rs/b1/github/Blamer-io/hub)](https://github.com/Blamer-io/hub)
[![PDD status](http://www.0pdd.com/svg?name=Blamer-io/hub)](http://www.0pdd.com/p?name=Blamer-io/hub)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/Blamer-io/hub/blob/master/LICENSE.txt)

Project architect: [@l3r8yJ](https://github.com/l3r8yJ)

Blamer Hub is an engine that distributes messages between microservices.

**Motivation**. We are needed some application that will integrate GitHub notifications and
Telegram messages in a decoupled, microservices way.

**Principles**. These are the [design principles](https://www.elegantobjects.org/#principles) behind blamer-hub.

**How to use**. 

TBD..

## How to Contribute

Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install
```

You will need Maven 3.8.7+ and Java 17+.

Our [rultor image](https://github.com/eo-cqrs/eo-kafka-rultor-image) for CI/CD.
