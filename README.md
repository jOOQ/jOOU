### Overview

jOOU stands for jOOU Object Oriented Unsigned. It is filling up the gap with one of Java's most wanted features: The unsigned number types.

### Dependencies

None!

### Download

**For use with Java 9+**

```xml
<dependency>
  <groupId>org.jooq</groupId>
  <artifactId>joou</artifactId>
  <version>0.9.4</version>
</dependency>
```

**For use with Java 6+**

```xml
<dependency>
  <groupId>org.jooq</groupId>
  <artifactId>joou-java-6</artifactId>
  <version>0.9.4</version>
</dependency>
```

### Description

This library provides an implementation for the four basic unsigned integer types as wrappers:

- `UByte`
- `UShort`
- `UInteger`
- `ULong`

These classes extend `java.lang.Number` and implement `java.lang.Comparable<?>`, just like the JDK's own wrapper types. Besides, there is a utility class called `org.joou.Unsigned` with factory methods allowing for creating unsigned wrappers like this:

```java
import static org.joou.Unsigned.*;

// and then...
UByte    b = ubyte(1);
UShort   s = ushort(1);
UInteger i = uint(1);
ULong    l = ulong(1);
```

This project was created for https://www.jooq.org, to provide better support for MySQL, Postgres, and other databases' unsigned integer data types, and has been open sourced as an independent library for usage outside of jOOQ.

----
See also this stack overflow question here: http://stackoverflow.com/questions/8193031/is-there-a-java-library-for-unsigned-number-type-wrappers

