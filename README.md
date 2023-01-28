# File converter service

Сервис для преобразования файлов из .xml в .json или из .json в .xml со структурой как 
у исполнителя файла artist.xml или artist.json.

## Запуск
Собранный JAR-файл находится во вкладке с релизами.


Запуск с двумя аргументами:
> java -jar <путь к библиотеке> <путь к файлу, из которого считываются данные> 
> <путь к файлу, куда записываются данные>

Запуск с тремя аргументами:
> java -jar <путь к библиотеке> <путь к файлу, из которого считываются данные>
> <путь к файлу, куда записываются данные> <расширение файла, откуда считываются данные>

Запуск с четырьмя аргументами:
> java -jar <путь к библиотеке> <путь к файлу, из которого считываются данные>
> <путь к файлу, куда записываются данные> <расширение файла, откуда считываются данные> <кодировка исходного файла>

## License
[MIT](https://choosealicense.com/licenses/mit/)
