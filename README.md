JBS (Java Bencode Serialization)
=======

This project allows you to serialize and desirilze POJO to Bencode format. 

Because Benoced format itself don't have enough information to discribe how POJO should be serialized to Bencode this project use it's own protocol of Serilization over Bencode with name JBSF (Java Bencode Serialization Format)

Bencode format
=======
more about bencode format - http://en.wikipedia.org/wiki/Bencode

JBSF
=======
This format describes how POJO should be serialized and deserialized to Bencode format.

For details see wiki: https://github.com/b0noI/Bencode/wiki/Java-Bencode-Serialization-Format-(JBSF)
  
Adding library to project
=======
For using this lib xustom Maven repo shuld be added to project:

```xml
 <repositories>
   <repository>
   <id>aif.com</id>
   <url>http://192.241.238.122:8081/artifactory/libs-snapshot-local/</url>
   </repository>
 </repositories>
 ```

And add dependency like this:
```xml
<dependency>
    <groupId\>com.bencode\</groupId>
    <artifactId\>serialization\</artifactId>
    <version\>1.0-SNAPSHOT\</version>
</dependency>
```

Usage
=======

Example of serialization of POJO to Bencode:

```Java
// imports
import com.bencode.serializator.ISerializer;
import com.bencode.serializator.referance.ReferenceSerializer;
import com.bencode.deserializator.referance.IDeserializer;


// Pojo for serialization
public static class Pojo {

  public Integer[][] ref;
  
}


// ...
Pojo pojo = new Pojo();
ISerializer serializer = new ReferenceSerializer();

// bytes - POJO in Bencode format
byte[] bytes = serializer.serialize(pojo).getElement();

// deserializing from BEncode format
Pojo pojo2 = IDeserializer.deserialize(bytes);

// ...
```

Links
======

* Project wiki: https://github.com/b0noI/Bencode/wiki
* Issue tracker: https://github.com/b0noI/Bencode/issues
