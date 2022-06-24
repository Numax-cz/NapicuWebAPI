# NapicuWebAPI - Preview

## Instalace
   
### Git 
   ```sh
   git clone https://github.com/Numax-cz/NapicuWebAPI.git
   ``` 

### Docker
   ```sh
  docker pull ghcr.io/numax-cz/napicuwebapi:latest
   ``` 

---

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
## Nastavení env v Dockeru
```
DB_HOST
DB_TABLE
DB_PASS
API_LIMITS
API_OPEN_WEATHER_KEY
```
* Ve výchozím nastavení je `API_LIMITS` nasatven na 40 requestů za minutu
* Aplikace si potřebné tabulky vytvoří sama
---

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

---


## Spuštění aplikace na požadovaném portu 
- Aplikace se defaultně spouští na portu 8080
- Pro spuštění aplikace na požadovaném portu použijte
1. Pomocí yml configuračního souboru
    ```yml
    server:
      port: 7070
    ```
2. Pomocí příkazu
    ```sh
    java -jar ./app.jar --server.port=7070
    ```

## Spuštění aplikace pro development
```shell
./RunDev.cmd
```

## Spuštění aplikace pro produkci - Docker
```shell
 java -jar ./app.jar --server.port=80 --spring.profiles.active=prod
```