Cut Off
======

*"A Java annotation library increase productivity for fun and profit"*

Cut Off is a simple way of making sure deadlines are met. 

Using a simple annotation in the form of `@Deadline(yyyy-mm-dd)`, any code that passes its deadline date causes compilation errors - alerting you **immediately** through your IDE. 


Installation
----

**Note:** This guide is for installation on Eclipse - Other IDE's will be available shortly.

+ Add the latest Cut Off .jar file to your build path
  + *Project > Properties > Java Build Path > Add External JARs* 
  
+ Add .jar to Factory Path in Annotation Processing Menu
  + *Project > Properties > Java Compiler > Annotations Processing*     
  + **Enable project specific settings**
  + **Enable annotation processing** and **Enable processing in editor**
  + In Factory Path menu - **Add Cut Off .jar file**
  + **Click Apply**
  + **Exit preferences** - setup should be complete

Example
----

    import co.uk.edgeorgedev.cutoff.Deadline;

    // Method with deadline

    @Deadline(2014-12-25)
    public void helloWorld(){
	     System.out.println("Cut Off is rad!");
    }


Credits
-----
Created by [Ed George](http://edgeorgedev.co.uk) - Feel free to tweet me [@edgeorge92](http://www.twitter.com/edgeorge92)

License
----
Released under the [MIT License](https://github.com/ed-george/CutOff/blob/master/LICENSE) - *Copyright (c) Ed George 2014.*