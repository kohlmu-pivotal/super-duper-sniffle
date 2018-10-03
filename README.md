# super-duper-sniffle

## Intro
This project is a sandbox project. The idea behind the project is to have a sandbox where new ideas can be easily written and tested in a "working" IMDG. Simplicity is KING!. This code needs to be simple to read,maintain and test. No exceptions!! 

### Design guidelines and principles
The core design guidelines for this project are:
*  NULLS do not exist... Deal with it....
*  All code is to be written in [Kotlin](https://kotlinlang.org/)
*  All modules need to be predictable, regardless of number of threads being used! Ideally, the whole project should run the same on 1 thread as it would with 100.
*  Clean API's and SPI's are a must! Without them, code shall not be accepted.
*  FINAL is your friend!
*  Constructor args are there to be used and used wisely
*  Assembly of complex modules is to be done by a configuration service, and factories
*  Excessive conditional statements are evil! Avoid them at all cost!
*  Think about function sizes and compiler constraints, after all... this is an IMDG... and we would like to have some amount performance
*  Consistency and predictability is important!! 
*  Finally, treat this as a location to learn new things. [Kotlin](https://kotlinlang.org/), [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html), [Kotlin Native](https://kotlinlang.org/docs/reference/native-overview.html), [Ktor](https://ktor.io/), [MockK](https://mockk.io/), [Spring Fu](https://github.com/spring-projects/spring-fu), [Spek](https://spekframework.org/), [GraalVM](https://www.graalvm.org/), [Kobalt](http://beust.com/kobalt/home/index.html) .... (you can see theme here :) )

### Goal
This project was started as sandbox to test out new features and functionality. Some would include:
*  Distributed Tracing
*  Monitoring
*  Consistent hashing data structures
*  Lock-free algorithms
*  Reactive
*  Message-Driven eventing & Asynchronous modelling

### House keeping
*  All work is to be done in branches before being merged into `master` (just to keep `master` clean)
*  Think Java9+ modularity... don't expose your internals
*  When making changes to common service definitions, all changes need to go through PR.

# FINALLY
Just have fun... and learn stuff...





