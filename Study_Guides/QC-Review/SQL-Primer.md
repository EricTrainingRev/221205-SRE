# Basic SQL Exercises

### Part One

Given the Following Tables, provide an SQL Statement that would recreate the **structure** of these tables (note, you are *not* required to include the 'insert' statements for the data of the tables).

Writer
|writer_id|writer_name| 
|-|-|
|1001|Agatha Christie|
|1002|George Orwell|
|1003|Kurt Vonnegut|
|1004|Mark Twain|
|1005|Stephen King|

Books
|book_id|title|author|publication|page_count|
|-|-|-|-|-|
|3050|Murder on the Orient Express|1001|1934|256|
|3051|It|1005|1986|1138|
|3052|And Then There Were None|1001|1939|272|
|3053|Pet Sematary|1005|1983|373|
|3054|Slaughterhouse-Five|1003|1969|215|
|3055|Nineteen Eighty-Four|1002|1949|328|
|3056|Adventures of Huckleberry Finn|1004|1884|366|
|3057|Death on the Nile|1001|1937|288|
|3058|Animal Farm|1002|1945|112|
|3059|The Adventures of Tom Sawyer|1004|1876|274|
|3060|The Shining|1005|1977|447|
|3061|Salem's Lot|1005|1975|439|
|3062|Cat's Cradle|1003|1963|304|

### Part Two

Given the following tables, provide an SQL Statement that will change the name of the 'book_id' column of the Books table to 'isbn_13'.

Writer
|writer_id|writer_name| 
|-|-|
|1001|Agatha Christie|
|1002|George Orwell|
|1003|Kurt Vonnegut|
|1004|Mark Twain|
|1005|Stephen King|

Books
|book_id|title|author|publication|page_count|
|-|-|-|-|-|
|3050|Murder on the Orient Express|1001|1934|256|
|3051|It|1005|1986|1138|
|3052|And Then There Were None|1001|1939|272|
|3053|Pet Sematary|1005|1983|373|
|3054|Slaughterhouse-Five|1003|1969|215|
|3055|Nineteen Eighty-Four|1002|1949|328|
|3056|Adventures of Huckleberry Finn|1004|1884|366|
|3057|Death on the Nile|1001|1937|288|
|3058|Animal Farm|1002|1945|112|
|3059|The Adventures of Tom Sawyer|1004|1876|274|
|3060|The Shining|1005|1977|447|
|3061|Salem's Lot|1005|1975|439|
|3062|Cat's Cradle|1003|1963|304|

### Part Three

Given the following tables, provide an SQL Statement that will change the name of the "Writer" table to "Author". Next, provide statements that will rename the writer_id and writer_name columns to author_id and author_name respectively.

Writer
|writer_id|writer_name| 
|-|-|
|1001|Agatha Christie|
|1002|George Orwell|
|1003|Kurt Vonnegut|
|1004|Mark Twain|
|1005|Stephen King|

Books
|book_id|title|author|publication|page_count|
|-|-|-|-|-|
|3050|Murder on the Orient Express|1001|1934|256|
|3051|It|1005|1986|1138|
|3052|And Then There Were None|1001|1939|272|
|3053|Pet Sematary|1005|1983|373|
|3054|Slaughterhouse-Five|1003|1969|215|
|3055|Nineteen Eighty-Four|1002|1949|328|
|3056|Adventures of Huckleberry Finn|1004|1884|366|
|3057|Death on the Nile|1001|1937|288|
|3058|Animal Farm|1002|1945|112|
|3059|The Adventures of Tom Sawyer|1004|1876|274|
|3060|The Shining|1005|1977|447|
|3061|Salem's Lot|1005|1975|439|
|3062|Cat's Cradle|1003|1963|304|

### Part Four

Given the following information, create SQL scripts to create the three tables described.

- Principles have a name, hire date, and salary.
- Schools have a name, and a reference to a principle
- Students have a name, a Grade (7th, 8th, 9th, etc...), a home address, an emergency contact phone number, and a reference to a school
