EasyFastJson
============

Encapsulates Jackon JSON library, Fast Speed Easy Used Json Lib For java

> Default Date Format is: `yyyy-MM-dd HH:mm:ss Z`  
>example: 
> java.util.Date birth = new java.util.Date();  
> JSON String out is: `2013-08-16 13:17:03 +0800`   
> Change DateFormat: `org.wjw.efjson.JsonObject.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));`,this change is global!  

Two kinds of use:  
--------------
###1.  Standard JSON way:
>  `org.wjw.efjson.JsonObject JSONObjectA = new org.wjw.efjson.JsonObject(JsonString);  //create JSON Object`  
>
>  `JSONObjectA.putBoolean(String fieldName, Boolean value);`  
>  `JSONObjectA.putBinary(String fieldName, byte[] binary);`  
>  `JSONObjectA.putNumber(String fieldName, Number value);`  
>  `JSONObjectA.putArray(String fieldName, JsonArray value);`  
>  `JSONObjectA.putString(String fieldName, String value);`  
>  `JSONObjectA.putObject(String fieldName, JsonObject value);`  
>
>  `JSONObjectA.getBoolean(String fieldName);`  
>  `JSONObjectA.getBinary(String fieldName);`  
>  `JSONObjectA.getNumber(String fieldName);`  
>  `JSONObjectA.getArray(String fieldName);`  
>  `JSONObjectA.getString(String fieldName);`  
>  `JSONObjectA.getObject(String fieldName);`  
>  
>  `   //no Carriage return and line feed and Indentation Raw Format JSON String`<br/>
>  `  String jsonString = JSONObjectA.encode();   `  

>  `   //Prettily Format JSON String`<br/>
>  `  String jsonString = JSONObjectA.encodePrettily();  `  
  

###2. Like Gson Java Bean way:
>  `   //create Java Bean from JSON String`<br/>
>  `  T obj = org.wjw.efjson.JsonObject.fromJson(JsonString,T.class); `  

>  `   //create List from JSON String`<br/>
>  `  List<T> list = JsonObject.fromJson(jsonStr, java.util.List.class, T.class);  `  

>  `   //create Map from JSON String`<br/>
>  `  Map<String, T> map = JsonObject.fromJson(jsonStr, java.util.Map.class, String.class, T.class);  `  

>  `  //create Complex collection nested types from JSON String`<br/>
>  `  Map<String, List<T>> mmap = JsonObject.fromJson(jsonStr, new TypeReference<Map<String, List<T>>>(){});  `  

>  `   //no Carriage return and line feed and Indentation Raw Format JSON String`<br/>
>  `  String jsonString = org.wjw.efjson.JsonObject.toJson(t);  `  

>  `   //Prettily Format JSON String`<br/>
>  `  String jsonString = org.wjw.efjson.JsonObject.toJsonPrettily(t);  `  
