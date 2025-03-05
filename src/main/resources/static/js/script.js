const app = Vue.createApp({
	data() {
		return {
			message: 'Ciao da file innestato, funziona dentro la cartella .js!',
			prodotti: [],  // Array per contenere i prodotti
			newProduct: {  // Nuovo prodotto da aggiungere
				descrizione: '',
				categoria: ''
			},
			showModal: false,  // Controlla la visibilità del modale
			submitLabel: 'Aggiungi'  // Etichetta del pulsante di invio
		};
	},
	methods: {
		// Carica il messaggio dal backend
		fetchMessage() {
			fetch('/prodotti/message')  
				.then(response => response.text())  
				.then(data => {
					this.message = data;  
				})
				.catch(error => {
					console.error('Errore nella richiesta:', error);
				});
				
				
		},

		// Carica tutti i prodotti dal backend
		fetchDb() {
			fetch('/prodotti/getAll')  
				.then(response => response.json())  // Converte la risposta in JSON
				.then(data => {
					this.prodotti = data;  // Salva i prodotti
				})
				.catch(error => {
					console.error('Errore nella richiesta:', error);
				});
		},

		// Aggiunge un nuovo prodotto
		submitProduct() {
			// Chiamata POST per inviare il nuovo prodotto
			fetch('/prodotti/create', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(this.newProduct)  // Converte il nuovo prodotto in JSON
			})
			.then(response => response.json())  // Risposta del backend
			.then(data => {
				console.log(data);  // Stampa il nuovo prodotto nella console
				this.prodotti.push(data);  // Aggiungi il prodotto all'array
				this.closeModal();  // Chiudi il modale
				this.newProduct = { descrizione: '', categoria: '' };  // Reset dei campi
			    this.fetchDb();  // Ricarica i prodotti 	
			})
			.catch(error => {
				console.error('Errore nell\'aggiunta del prodotto:', error);
			});
		},
		popolamentoForm(prodotto){
			this.openModal();
			//Importantissimo Passaggio!!! 
			//ridefinisce il newProduct con il id del prodotto!!
			//così da poterlo passare alla chiamata PUT (Modifica)
			this.newProduct={
				id: prodotto.id,
				descrizione: prodotto.descrizione,
				categoria: prodotto.categoria	
			}
			//popola i campi del form con i dati del prodotto						
			this.newProduct.descrizione = prodotto.descrizione;	
			this.newProduct.categoria = prodotto.categoria;
			this.submitLabel = 'Modifica';  // Cambia l'etichetta del pulsante
			
		},
		deleteProdotto(){
			alert("deleteProdotto");
		},
		// Mostra il modale
		openModal() {
			this.showModal = true;  // Imposta il modale come visibile
			this.submitLabel = 'Aggiungi';  // Cambia l'etichetta del pulsante
		},

		// Chiude il modale
		closeModal() {
			this.showModal = false;  // Nasconde il modale
		}
		
		
		
		
	}
});

// Collega Vue all'elemento con ID "applicazione"
app.mount('#applicazione');
