EasyFastJson
============

Encapsulates Jackon JSON library, Fast Speed Easy Used Json Lib For java

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
>  `  String jsonString = org.wjw.efjson.JsonObject.toJson(t);  //no Carriage return and line feed and Indentation Raw Format JSON String`  
>  `  String jsonString = org.wjw.efjson.JsonObject.toJsonPrettily(t);  //Prettily Format JSON String`  
