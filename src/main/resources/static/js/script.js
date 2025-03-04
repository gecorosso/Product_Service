const app = Vue.createApp({
	data() {
		return {
			message: 'Ciao da file innestato, funziona dentro la cartella .js!',
			prodotti: [],  // Array per contenere i prodotti
			newProduct: {  // Nuovo prodotto da aggiungere
				descrizione: '',
				categoria: ''
			},
			showModal: false  // Controlla la visibilità del modale
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
			})
			.catch(error => {
				console.error('Errore nell\'aggiunta del prodotto:', error);
			});
		},

		// Mostra il modale
		openModal() {
			this.showModal = true;  // Imposta il modale come visibile
		},

		// Chiude il modale
		closeModal() {
			this.showModal = false;  // Nasconde il modale
		}
	}
});

// Collega Vue all'elemento con ID "applicazione"
app.mount('#applicazione');
