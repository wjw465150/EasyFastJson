EasyFastJson
============

Encapsulates Jackon JSON library, Fast Speed Easy Used Json Lib For java

> Date Format is: `yyyy-MM-dd HH:mm:ss Z`  
>example: 
> java.util.Date birth = new java.util.Date();
> JSON String out is: `2013-08-16 13:17:03 +0800` 

Two kinds of use:  
--------------
###1.  Standard JSON way:
>  `org.wjw.efjson.JsonObject JSONObjectA = new org.wjw.efjson.JsonObject(JsonString);  //create JSON Object`
>  `JSONObjectA.putBoolean();`  
>
>  `JSONObjectA.putBinary();`  
>  `JSONObjectA.putNumber();`  
>  `JSONObjectA.putArray();`  
>  `JSONObjectA.putString();`  
>  `JSONObjectA.putObject();`  
>  
>  `  String jsonString = JSONObjectA.encode();   //no Carriage return and line feed and Indentation Raw Format JSON String`  
>  `  String jsonString = JSONObjectA.encodePrettily();  //Prettily Format JSON String`  
  

###2. Like Gson Java Bean way:
>  `  T obj = org.wjw.efjson.JsonObject.fromJson(JsonString,T.class);  //create Java Bean from JSON String`  
>  `  List<T> list = JsonObject.fromJson(jsonStr, java.util.List.class, T.class);  //create List from JSON String`  
>  `  Map<String, T> map = JsonObject.fromJson(jsonStr, java.util.Map.class, String.class, T.class);  //create Map from JSON String`  
>  `  Map<String, List<T>> mmap = JsonObject.fromJson(jsonStr, new TypeReference<Map<String, List<T>>>(){});  //create Complex collection nested types from JSON String`  
>  `  String jsonString = org.wjw.efjson.JsonObject.toJson(t);  //no Carriage return and line feed and Indentation Raw Format JSON String`  
>  `  String jsonString = org.wjw.efjson.JsonObject.toJsonPrettily(t);  //Prettily Format JSON String`  
