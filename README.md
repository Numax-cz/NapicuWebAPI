# NapicuWebAPI - Preview

## Instalace

1. ### Naklonování repozitáře
   ```sh
   git clone https://github.com/Numax-cz/NapicuWebAPI.git
   ``` 

## Nastavení configu
Nastavte soubor pro development <br />
`src/main/resources/application-dev.yml` <br/>

   Nastavte soubor pro development <br />
  `src/main/resources/application-dev.yml` <br/>

- Pro změnu profilů použijte tento příkaz
    ```sh
     java -jar ./app.jar --spring.profiles.active=dev
    ``` 
- Pro vytvoření nového profilu vytvoříte soubor v `src/main/resources/application-NAZEV-PROFILU.yml`
    ```sh
     java -jar ./app.jar --spring.profiles.active=NAZEV-PROFILU
    ```
- Pro spuštění aplikace na požadovaném portu použijte
    ```sh
     java -jar ./app.jar --server.port=7070
    ```
- Ve výchozím nastavení se aplikace spustí na portu 8080





## Buildnutí aplikace
1. ### Nastavení javy
- Nastavíme systémovou proměnnou `JAVA_HOME` odkazující na cestu k Javě
- Nebo nastavíme v `mvnw.cmd cestu` k Javě viz:

```shell 
   if "%JAVA_HOME%" == "" set "JAVA_HOME=D:\Apps\JDK 15.0.2\"
```

2. ### Spuštění scriptu pro buildnutí
```shell
./Build.cmd
```


## Spuštění aplikace pro development
```shell
./RunDev.cmd
```
- Aplikace se defaultně spustí na portu 8080

## Spuštění aplikace pro produkci - Docker
```shell
 java -jar ./app.jar --server.port=80 --spring.profiles.active=prod
```