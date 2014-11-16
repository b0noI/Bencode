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

Result of POJO serializaiton should be Bencode dictionary ( __root dictionary__ ). Each key of this dictionary should have ID of serilizaed object. Value is bencoded value for this object. Each value should have Nencode dictionary type.

Each value in root dictinary should be dictinory that stores serialized values of speceific instance. This dictinary called __instance dictinary__
Keys of instance dictionary represents fileds names of POJO + 1 special key with name "$CLASS_TYPE" that store Class type of this instance. 
Each value of the instance dictinary could be:
* ByteString in case if:
  * field is primitive or can be unboxed to primitive;
  * field is referance to other object. In this case ByteString stores Id of toher object in RootDictinary;
* BecnodeList in case if:
  * field is array;
  
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

Project wiki: https://github.com/b0noI/Bencode/wiki
Issue tracker: https://github.com/b0noI/Bencode/issues
