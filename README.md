````

# Java PagerdutyClient

This is a command-line interface implementation of an address book utility written in Java using the PagerDuty users API.

## To run the utility

Simply use a fairly recent version of the java runtime to execute the jar file as follows:

```
java -jar PagerDutyClient.jar
```

The utility will display the following instructions...

```
Welcome to the PagerDuty Client!
This utility allows you to search and display records from the address book.

You can enter the following values at the prompt:
QUERYING:
- Type the query you would like to use to search the address book
- Press <enter> to display all results
NAVIGATING:
- Enter the number of the contact you would like to view from the search results
- Enter 'n' to display the next page of results
GENERAL:
- Enter 'h' to display this help message
- Enter 'q' to quit the application
```
## Here is a sample run

Here is a sample run of the utility where the user performs the following actions:
- Search the address book for 'ace'.  The utility returns 1 record which matches against the email address.
- Display the record by enter '1'.
- Search the address book for a dummy value 'doh' which returns no results.
- Search the address book for 'br' which returns 5 records.
- Display the 4th record by entering '4'.
- Search the entire address book by entering '' an empty search.
- Retrieve the next page of the results by entering 'n'.

```
> ace
1       Elon Musk
ace> 1
Elon Musk
Time Zone: America/Los_Angeles
Email (Default): elon.musk@spacex.example
Phone (Work): (111)555-0018
SMS (Mobile): (111)555-0018

ace> doh
There are no results.
doh> br
1       Brett Willemsen
2       British Broadcasting Corporation
3       Canadian Broadcasting Corporation
4       Jim Bridenstine
5       National Broadcasting Company
br> 4
Jim Bridenstine
Time Zone: America/Los_Angeles
Team: North American Space Agency (NASA)
Email (Default): jim.bridenstine@nasa.example
Phone (Work): (111)555-0023
SMS (Mobile): (111)555-0023

br>
1       Alan B. Shepard, Jr.
2       Aleksei Sorokin
3       Andriyan Nikolayev
4       Belka
5       Boris Volynov
6       Boris Yegorov
7       Brett Willemsen
8       British Broadcasting Corporation
9       Canada
10      Canadian Broadcasting Corporation
> n
11      Chernushka
12      Donald K. Slayton
13      Douglas Hurley
14      Elon Musk
15      Georgi Katys
16      Gherman Titov
17      Grigori Nelyubov
18      Ivan Ivanovich
19      Japan
20      Jim Bridenstine
> q

```
````