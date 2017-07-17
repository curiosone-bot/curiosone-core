package workflow;

public class Automa
{

	public enum States
	{
		START,
		WAITING,
		GET_QUESTION,
		ASK_QUESTION,
		ANSWER_QUESTION,
		LEARN,
		GET_SENTENCE,
		PROCESS_ANSWER,
		RELAX,
		CHILLING_OUT,
		END;
	}

	private States state;

	@SuppressWarnings("unused")
	private int nSentences; // ? dopo aver ricevuto n frasi, chiede se continuare o giocare

	@SuppressWarnings("unused")
	private int noAnswer; // ? se non sa rispondere a n frasi, chiede di cambiare argomento

	// private GrammarProcessor gram;
	// private Sentence sentence;
	// private GameCenter gc;
	
	public Automa()
	{
		state = States.START;
		operation();
	}
	
	public void operation()
	{
		switch(state)
		{
			case START: start(); break;
			case WAITING: waiting(); break;
			case GET_QUESTION: getQuestion(); break;
			case ASK_QUESTION: askQuestion(); break;
			case ANSWER_QUESTION: answerQuestion(); break;
			case LEARN: learn(); break;
			case GET_SENTENCE: getSentence(); break;
			case PROCESS_ANSWER: processAnswer(); break;
			case RELAX: relax(); break;
			case CHILLING_OUT: chillingOut(); break;
			case END: end();
		}
	}

	public void start()
	{
		System.out.println("Hi I'm Curiosone, ask me something!");
		state = States.WAITING;
		operation();
	}

	public void waiting()
	{
		 /*
		  *   attende n secondi una frase. Quattro possibilità:
		  *   1) Riceve una domanda:
		  *   		- va nello stato GET_QUESTION, conosce la risposta?
		  *   			- Si, va nello stato ANSWER_QUESTION, risponde e torna nello stato WAITING
		  *   	     	- No, va in un nuovo stato ASK_QUESTION e successivamente in LEARN, impara, torna in waiting
		  *
		  *   In entrambi i casi aggiorna la mood
		  *
		  *   ?? se non conosce la risposta di più domande va nello stato NEW_TOPIC ??
		  *
		  *   2) Non è una domanda:
		  *   		- va nello stato GET_SENTENCE, conosce la risposta?
		  *   			- Si, va nello stato PROCESS_ANSWER, risponde e torna in WAITING
		  *   			- No, va nello stato ASK_QUESTION, chiede, va nello stato LEARN, impara, aggiorna mood, torna in WAITING
		  *
		  *   3) Non riceve nulla, va nello stato RELAX e propone di giocare.
		  *   		 - Si, va nello stato CHILLING_OUT e avvia un gioco(random?)[Terminato il gioco torna in WAITING]
		  *   		 - No, torna in WAITING
		  *
		  *   4) Riceve "/exit, /close, /bye etc.." va nello stato END e si chiude la sessione
		  */
	}

	public void getQuestion() {  /* ... */ }
	public void askQuestion() {  /* ... */ }
	public void answerQuestion() {/* ... */}
	public void learn() {  /* ... */ }
	public void getSentence() { /* ... */ }
	public void processAnswer() {  /* ... */ }
	public void relax() {  /* ... */ }
	public void chillingOut() { /* ... */ }
	public void end() { /* ... */ }

	//aggiungere comportamento con mood
	
}
