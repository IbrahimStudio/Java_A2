# Java_A2

*Assignment 2 of the course "Ingegneria Software" held by Professor Agostino Poggi @ Università di Parma*


L’obiettivo è creare una semplice applicazione che permette di gestire le vendite di prodotti online. Il 
software deve essere commentato con Javadoc e deve essere introdotto un documento che descriva il 
funzionamento del sistema. Il codice e il Javadoc devono essere scritti in inglese.
L’applicazione si basa su due nodi: client e server. La loro interazione si basa sull’uso delle socket.
Il client può interagire con il server dopo l’autenticazione: nome utente–password, e di inviare richieste al 
server tramite la console.
Il server viene creato con un insieme di prodotti.
Un prodotto è descritto da: nome del prodotto, prezzo e identificatore.
Le coppie nome utente–password sono inserite dal server.
I prodotti sono inseriti tramite la console del server e quando sono restituiti dal client
I prodotti sono eliminati dal server quando sono trasferiti al client
Il client può inviare al server:
- la coppia nome utente–password
- la richieste di visionare la lista dei prodotti
- la richiesta di acquisto di un prodotto (nome del prodotto, prezzo e identificatore)
- la richiesta di restituire un prodotto (nome del prodotto, prezzo e identificatore)
- la richiesta di mettere a disposizione un nuovo tipo di prodotto di cui indica il nome
- un messaggio di chiusura
Il server può inviare al client:
- un messaggio di accesso effettuato
- un messaggio di accesso fallito
- L’invio della lista dei prodotti
- L’invio del prodotto acquistato dal client
- un messaggio di chiusura
Il client deve essere chiuso prima del server
