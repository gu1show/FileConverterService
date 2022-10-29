# File converter service

Service for transforming files from .xml to .json or from .json to .xml with structure as in the file artist.(xml or json). 

## Run
> java -jar \<path to library> \<path to the first file> \<path to the second file>

## Requarements 

- DOM parser

Maven dependency:
```xml
<dependency>
  <groupId>org.w3c</groupId>
  <artifactId>dom</artifactId>
  <version>2.3.0-jaxb-1.0.6</version>
</dependency>
```
- org.json

Maven dependency:
```xml
<dependency>
  <groupId>org.json</groupId>
  <artifactId>json</artifactId>
  <version>20220924</version>
</dependency>
```
## License
[MIT](https://choosealicense.com/licenses/mit/)
