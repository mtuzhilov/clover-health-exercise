#
# **Engineering Exercise – Clover Health**

## **Instructions**

Part of the onsite includes a portion where we want to evaluate your code. Please complete this exercise and get it back to us within 48 hours of your visit.

## **Problem definition**

You have directories containing data files and specification files. The specification files describe the structure of the data files. Write an application in the language of your choice that reads format definitions from specification files. Use these definitions to load the data files into a database.

## **Problem details**

Data files exist in a data/ directory relative to your application and specification files exist in a specs/ directory relative to your application.

Specification files will have filenames equal to the file type they specify and extension of .csv. So fileformat1.csv would be the specification for files of type fileformat1.

Data files will have filenames based on their specification file name, followed by an underscore, followed by the drop date and an extension of .txt. For example, fileformat1\_2007-10-15.txt would be a data file to be parsed using specs/fileformat1.csv, which arrived on 10/15/2007.

Format files will be csv formated with columns &quot;column name&quot;, &quot;width&quot;, and &quot;datatype&quot;.

- &quot;column name&quot; will be the name of that column in the database table
- &quot;width&quot; is the number of characters taken up by the column in the data file
- &quot;datatype&quot; is the SQL data type that should be used to store the value in the database table.

Data files will be flat text files with lines matching single records for the database. Lines are formatted as specified by their associated specification file.

##

##

## **Example**

This is an example file pair; other files may vary in structure while still fitting the structure of the problem details (above):

specs/testformat1.csv:

&quot;column name&quot;,width,datatype

name,10,TEXT

valid,1,BOOLEAN

count,3,INTEGER

Here we have a specification that describes 3 columns:

- The first 10 characters labeled &quot;name&quot; of type TEXT
- The next 1 character labeled &quot;valid&quot; of type BOOLEAN (&#39;1&#39; = True, &#39;0&#39; = False)
- The last 3 characters labeled &quot;count&quot; of type INTEGER

data/testformat1\_2015-06-28.txt:

Foonyor 1 1

Barzane 0-12

Quuxitude 1103

Processing this data file results in the following table:

| | name | valid | count |
| --- | --- | --- | --- |
| 1 | Foonyor | TRUE | 1 |
| 2 | Barzane | FALSE | -12 |
| 3 | Quuxitude | TRUE | 103 |

## **Expectations**

**Expectations**

- Clover primarily uses Python, but your application can be written with language/libraries of your choosing. Take this opportunity to best demonstrate your talents!
- Database type and connection mechanism is left to your discretion.
- You are expected to write unit tests for different cases of the core logics. You do not have to use any specific unit test frameworks but should include instructions to run your tests should you use any. You may even write a simple driver program for us to run your unit test cases with mockup data.
- You should implement the conversions for the SQL data types: TEXT, BOOLEAN, and INTEGER
- You should be able to handle any specification file that matches the problem description (not just the given example)
- Files can be assumed to use UTF-8 encoding
- You should be prepared to discuss implementation decisions and possible extensions to your application.


# **Solution**

The solution is using a Spring Boot powered application to run a REST service with H2 in-memory database for demo purpose.
The application supports calling the REST service to import a data file by name. The application then processes the data
file and inserts the data into the H2 database. The data files can then subsequently be queried using the REST service 
by ID.

**Build**
```
# build using maven
> mvn clean install
```

**Run**
```
# run, this will start the server running on port 8080
> java -jar target/clover-health-demo.jar
```

**Import a data file**

```
# POST filename to import the datafile. The application knows how to access the file
curl -X POST http://localhost:8080/data-file/testformat1_2015-06-28.txt 
```

**Get all imported files**
```
# Call GET all to retrieve all imported data files
curl  http://localhost:8080/data-files | json_pp
```

Response
```json
[
   {
      "id" : 1,
      "name" : "testformat1_2015-06-28.txt",
      "importedAt" : "2021-03-21T21:18:54.552+0000",
      "columns" : [
         {
            "id" : 1,
            "width" : 10,
            "type" : "TEXT",
            "rows" : [
               {
                  "valueTxt" : "Foonyor",
                  "id" : 1
               },
               {
                  "id" : 4,
                  "valueTxt" : "Barzane"
               },
               {
                  "id" : 7,
                  "valueTxt" : "Quuxitude"
               }
            ],
            "name" : "name"
         },
         {
            "rows" : [
               {
                  "valueBool" : true,
                  "id" : 2
               },
               {
                  "id" : 5,
                  "valueBool" : false
               },
               {
                  "valueBool" : true,
                  "id" : 8
               }
            ],
            "type" : "BOOLEAN",
            "name" : "valid",
            "id" : 2,
            "width" : 1
         },
         {
            "name" : "count",
            "type" : "INTEGER",
            "rows" : [
               {
                  "id" : 3,
                  "valueInt" : 1
               },
               {
                  "valueInt" : -12,
                  "id" : 6
               },
               {
                  "valueInt" : 103,
                  "id" : 9
               }
            ],
            "width" : 3,
            "id" : 3
         }
      ]
   }
]
```
**Query the DB by going to H2 Console**
Navigate in your browser to http://localhost:8080/h2-console

![H2 Console](img/h2-console.png?raw=true "H2 Console")

Press Connect

![H2 Console1](img/h2-console2.png?raw=true "H2 Console2")

