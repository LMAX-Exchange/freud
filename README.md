Freud
=====

A framework for writing static analysis tests.

Maintainer
==========

[Amir Langer] (https://github.com/langera)

What is it?
===========

Freud gives the users an easy way to define their own static analysis tests.
 
Unlike other common analysis tools such as checkstyle, PMD or findbugs, it does not attempt to provide out-of-the-box generic tests. Those are good in many ways, but they are also far too generic. With Freud, the users can force a rule that is specific to the code base or easily create their own twist or variant of a "common" rule.

Using a DSL for every supported type of analysed entity, Freud allows users to define their own targeted test. What you get is the ability to write a test specifically tailored to your code and asserts your conventions.

The usage of a DSL also means the rules are concise and readable.

Freud is completely pluggable meaning you can easily write your own hamcrest matcher and assert anything you like. Freud will accept any matcher - not just its own pre-defined set of matchers.

Although written in Java and initially targeted towards analysing Java sources, Freud is also not restricted to any specific source or content type.

The support provided within Freud is for:

1. Java sources
2. Java class objects. (i.e analysing the java.lang.Class object)
3. Java class files (i.e analysing the ".class" file)
4. CSS files
5. properties files
6. Text files
7. Spring framework XML configuration files

Different tests are much easier to implement using different content such as java sources / text files / class obejcts / class files). Also, a lot of java projects contain properties files... some may even have spring framework configuration files...

Freud was desiged to be content type neutral and currently contains DSL and parser support for all content types above.

Analysing another type of content means writing a parser for that content that will convert a resource such as a file to an object representation of the content.

Then all is left is to write Matchers for that object representation.

Examples
========

### Every class that implements `equals(Object)` should also implement `hashcode()`

+```java
   Freud.iterateOver(Class.class).
	assertThat(  
		hasDeclaredMethod("equals", Object.class).and(hasDeclaredMethod("hashCode")).  
		or(no(hasDeclaredMethod("equals", Object.class)).and(no(hasDeclaredMethod("hashCode")))));
+```
		  
### Descendant tag selectors are the most expansive in CSS 

+```java
   Freud.iterateOver(CssRule.class).
	assertThat(selectors(CssSelector.Type.TAG).lessThanOrEqualTo(1))`  
+```

### Code block should not contain a call to System.out.print(ln) unless a specific SuppressWarnings annotation exists (an example of how a easy it is to put a spin on a rule. Something that in the real world is badly needed).

+```java
   Freud.iterateOver(CodeBlock.class).
	forEach(no(method(hasDeclaredAnnotation("SuppressWarnings", Matchers.containsString("printing to System.out here is OK. Honest."))))).  
	assertThat(no(hasMethodCall("System.out.print")).and(no(hasMethodCall("System.out.println"))))`  
+```


More Examples
=============

[Here] (https://github.com/LMAX-Exchange/freud/tree/master/src/test/org/langera/examples)