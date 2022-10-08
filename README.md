# NapicuWebAPI

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

- Nastavte soubor pro development `src/main/resources/application-dev.yml`


- Pro změnu profilů použijte tento příkaz
    ```sh
     java -jar ./app.jar --spring.profiles.active=dev
    ``` 
- Pro vytvoření nového profilu vytvoříte soubor v `src/main/resources/application-NAZEV-PROFILU.yml`
    ```sh
     java -jar ./app.jar --spring.profiles.active=NAZEV-PROFILU
    ```
- Nastavení env v Dockeru
    ```
    DB_HOST
    DB_TABLE
    DB_USER
    DB_PASS
    API_LIMITS
    API_OPEN_WEATHER_KEY
    ```
* Ve výchozím nastavení je `API_LIMITS` nasatven na 40 requestů za minutu
* Aplikace si potřebné tabulky vytvoří sama

---

## Buildnutí aplikace

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


## Spuštění aplikace pro produkci
```shell
 java -jar ./app.jar --spring.profiles.active=prod-example
```

## Spuštění aplikace v dockeru
```shell
docker run --init -d --name NapicuAPI --env-file secrets.env -p 8080:80  ghcr.io/numax-cz/napicuwebapi:latest
```
