# sort
Программа выполняет сортировку содержимого файлов методом вставки.

### Описание

Программа может сортировать строчные или целочисленные значения по возрастанию или убыванию. Умеет выполнять сортировку для нескольких файлов одного типа одновременно

### Документация
*Пример запуска для строк по возрастанию:*

`java –jar sort.jar C:\in_dir\ --out-prefix=sorted_ --content-type=s --sort-mode=a`

*Или, что тоже самое:*

`java –jar sort.jar C:\in_dir\ -o=sorted_ -c=s -s=a`

*Параметры и опции запуска:*

`(C:\in_dir\)` - параметр указанывает путь до каталога с файлами, данные которых нужно отсортировать\
`-o, --out-prefix` задает префикс для отсортированных файлов\
`-c, --content-type` - тип содержимого файлов: **_s_** - строки, **_i_** - целые числа\
`-s, --sort-mode` - тип сортировки: _**a**_ - по возрастанию, **_d_** - по убыванию\
`-h, --help` - справка о программе


*Пример выполнения для строковых значений и сортировки по возрастанию:*

Исходный файл, например `test.txt` из директории `(C:\in_dir\)` содержит строки:

``` 
    nice 
    any
    love
    tell
    top
    second
    why
```

После выполнения примера:

`java –jar sort.jar C:\in_dir\ --out-prefix=sorted_ --content-type=s --sort-mode=a`

В каталоге `(C:\in_dir\)` появится файл с префиксом `sorted_` - `sorted_test.txt` и содержимым:

``` 
    any
    love
    nice
    second
    tell
    top
    why
```

<aside class="warning">
Если каталог содержит подкаталоги, то их содержимое не учитывается
</aside>