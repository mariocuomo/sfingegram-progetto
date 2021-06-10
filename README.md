# Sfingegram-progetto
Esempio di sistema distribuito - architettura a microservizi - per sostenere qualità significative come scalabilità, disponibilità e prestazioni.

**TEAM** :computer:
<ul type="circle">
  <li> :man: <a href="https://github.com/mariocuomo">Mario Cuomo</a> </li> 
  <li> :man: <a href="https://github.com/AndGiaccone">Andrea Giaccone</a></li> 
  <li> :man: <a href="https://github.com/AndreaDeSanctis">Andrea De Sanctis</a></li> 
</ul>

Progetto proposto durante il corso [Architettura dei Sistemi Software](http://cabibbo.dia.uniroma3.it/asw/) a Roma Tre.<br>
Di seguito una descrizione del progetto.<br>
Al termine una descrizione delle modifiche apportate dal team.

--- 

# SFINGEGRAM 

Progetto del corso di Architettura dei Sistemi Software per l'anno accademico 2020-2021. 


## Descrizione di questo progetto 

Questo progetto contiene il codice di *Sfingegram*, un semplice social network per la condivisione di enigmi (ovvero, giochi enigmistici). 
Gli utenti del sistema possono pubblicare degli enigmi. 
Possono poi seguire gli enigmi di specifici autori o di specifici tipi.  
Quando un utente accede alla pagina degli enigmi che segue, gli vengono mostrati gli enigmi degli autori e dei tipi che segue. 

L'applicazione *Sfingegram* è composta dai seguenti microservizi: 

* Il servizio *enigmi* gestisce gli enigmi. 
  Ogni enigma ha un autore, un tipo, un titolo, un testo (che può essere composta da più righe) e una soluzione (che può essere composta da più parole). 
  Operazioni: 
  * `POST /enigmi` aggiunge un nuovo enigma (dati autore, tipo, titolo, testo e soluzione)
  * `GET /enigmi/{id}` trova un enigma, dato l'id 
  * `GET /enigmi/{id}/soluzione` trova la soluzione di un enigma, dato l'id 
  * `GET /enigmi` trova tutti gli enigmi (senza la soluzione)
  * `GET /cercaenigmi/autore/{autore}` trova tutti gli enigmi di un certo autore (senza soluzione)
  * `GET //cercaenigmi/autori/{elenco-di-autori}` trova tutti gli enigmi di un insieme di autori (senza soluzione) 
  * `GET /cercaenigmi/tipo/{tipo}` trova tutti gli enigmi di un certo tipo (senza soluzione)
  * `GET /cercaenigmi/tipi/{elenco-di-tipi}` trova tutti gli enigmi di un insieme di tipi (senza soluzione)
  
* Il servizio *connessioni* gestisce le connessioni degli utenti, ovvero gli autori e i tipi di enigmi seguiti dagli utenti. 
  Le connessioni sono delle coppie utente-autore oppure utente-tipo, in cui gli autori sono in genere altri utenti del sistema. 
  Operazioni: 
  * `POST /connessioniconautori` aggiunge una nuova connessione utente-autore (dati utente e autore)
  * `GET /connessioniconautori` trova tutte le connessioni utente-autore
  * `GET /connessioniconautori/{utente}` trova tutte le connessioni utente-autore di un certo utente
  * `POST /connessionicontipi` aggiunge una nuova connessione utente-tipo (dati utente e tipo)
  * `GET /connessionicontipi` trova tutte le connessioni utente-tipo
  * `GET /connessionicontipi/{utente}` trova tutte le connessioni utente-tipo di un certo utente

* Il servizio *enigmi-seguiti* consente a un utente di trovare gli enigmi di tutti gli autori e di tutti i tipi che segue. 
  Operazioni: 
  * `GET /enigmiseguiti/{utente}` trova tutti gli enigmi seguiti da un certo utente, ovvero gli enigmi scritti da autori seguiti da quell'utente o di tipi di enigmi seguiti da quell'utente (gli enigmi sono senza soluzione)
  
* Il servizio *api-gateway* (esposto sulla porta *8080*) è l'API gateway dell'applicazione che: 
  * espone il servizio *enigmi* sul path `/enigmi` - ad esempio, `GET /enigmi/enigmi`
  * espone il servizio *connessioni* sul path `/connessioni` - ad esempio, `GET /connessioni/connessioniconautori/{utente}`
  * espone il servizio *enigmi-seguiti* sul path `/enigmi-seguiti` - ad esempio, `GET /enigmi-seguiti/enigmiseguiti/{utente}`


## Esecuzione 

Per eseguire questo progetto: 

* avviare *Sfingegram* eseguendo lo script `start-sfingegram.sh` 

* per inizializzare le basi di dati con dei dati di esempio, eseguire gli script `do-init-enigmi.sh` e `do-init-connessioni.sh` 


Sono anche forniti alcuni script di esempio: 

* lo script `run-curl-client.sh` esegue un insieme di interrogazioni di esempio 

* lo script `do-get-enigmi.sh` trova tutti gli enigmi 

* lo script `do-get-enigma.sh` trova un enigma 

* lo script `do-get-enigmi-di-autore.sh` trova tutti gli enigmi di un certo autore 

* lo script `do-get-enigmi-di-autori.sh` trova tutti gli enigmi di un insieme di autori  

* lo script `do-get-enigmi-di-tipo.sh` trova tutti gli enigmi di un certo tipo  

* lo script `do-get-enigmi-di-tipi.sh` trova tutti gli enigmi di un insieme di tipi  

* lo script `do-get-connessioni.sh` trova tutte le connessioni 

* lo script `do-get-enigmi-seguiti.sh` trova tutti gli enigmi seguiti da un certo utente 

Ed inoltre: 

* lo script `do-post-altri-enigmi.sh` aggiunge nuovi enigmi 

* lo script `do-post-altre-connessioni.sh` aggiunge nuove connessioni 

Alla fine, l'applicazione può essere arrestata usando lo script `stop-sfingegram.sh`


## Descrizione delle attività da svolgere 

Si veda la descrizione del progetto sul sito web del corso di [Architettura dei sistemi software](http://cabibbo.dia.uniroma3.it/asw/).

---

## Attività svolte dal team
Descrizione sintetica delle tecnologie utilizzate
* Basi di dati MySQL per i servizi enigmi, connessioni e enigmi-seguiti
* Docker-Compose per comporre i container
* Kafka per realizzare canali per messaggi tra i servizi

<p align="center">
  <img src="https://github.com/mariocuomo/sfingegram-progetto/blob/main/images%20for%20readme.md/0.png" width="550" height="400">
</p>

L’iter che abbiamo seguito per realizzare le modifiche è stato il seguente.

Per prima cosa abbiamo containerizzato i servizi impostando i file *docker-compose.yml* e *Dockerfile*. Nel file docker-compose.yml abbiamo descritto – oltre ai container relativi ai servizi già presenti – i 2 container sui quali girano le basi di dati **MySQL**. Un ulteriore container è quello relativo al servizio **consul**.

<p align="center">
  <img src="https://github.com/mariocuomo/sfingegram-progetto/blob/main/images%20for%20readme.md/1.png" width="550" height="400">
</p>

La seconda operazione è stata quella di integrare **Kafka** e **ZooKeeper** – entrambi in due ulteriori container. In particolare, abbiamo impostato due canali Kafka: *enigmi* e *connessioni*. Il servizio enigmi pubblica sul primo, il servizio connessioni sul secondo (entrami i servizi si comportano quindi come publisher). Il servizio enigmi-seguiti è un subscriber dei due canali.
Per cercare di minimizzare la ripetizione del codice, abbiamo definito un progetto comune: **common**. Tale progetto contiene classi che descrivono la struttura dei messaggi scambiati.<br>
`NOTA` sul canale *connessioni* si ha la pubblicazione sia delle connessioni utente-autore che di quelle utente-tipo. La differenziazione consiste nel tipo di messaggio inviato.

<p align="center">
 <img src="https://github.com/mariocuomo/sfingegram-progetto/blob/main/images%20for%20readme.md/2.png" width="550" height="400">
</p>

La terza operazione è stata quella di modificare la logica del servizio enigmi-seguiti. 
Abbiamo definito un ulteriore container che contiene la base di dati per questo servizio.
In prima battuta è stata realizzata la prima soluzione proposta che poi si è rivelata propedeutica per adottare la seconda.<br>
È stato applicato il pattern **CQRS(Command-Query Responsibility Segregation)** usando viste materializzate per effettuare query localmente.

<p align="center">
 <img src="https://github.com/mariocuomo/sfingegram-progetto/blob/main/images%20for%20readme.md/3.png" width="550" height="400">
</p>
