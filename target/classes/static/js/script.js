const app = Vue.createApp({
            data() {
                return {
                    //Variabili
                    prodotti: [],  // Array per contenere i prodotti
                    searchText: '', // Variabile per il testo della ricerca
                    newProduct: {  // Nuovo prodotto da aggiungere
                        descrizione: '',
                        categoria: ''
                    },
                    showModal: false,  // Controlla la visibilità del modale
                    submitLabel: 'Aggiungi',  // Etichetta del pulsante di invio
                    prodottiVisibili: false,  // Controlla la visibilità della lista prodotti

                    // Paginazione
                    currentPage: 1, // Pagina attuale
                    itemsPerPage: 5, // Numero di elementi per pagina
                    data: []
                };
            },
            computed: {
                // Calcola il numero totale di pagine
                totalPages() {
                    return Math.ceil(this.prodotti.length / this.itemsPerPage);
                },
                // Estrae solo i prodotti della pagina corrente
                paginatedProdotti() {
                    const start = (this.currentPage - 1) * this.itemsPerPage;
                    const end = start + this.itemsPerPage;
                    return this.prodotti.slice(start, end);
                }
            },
            methods: {
                toggleProdotti() {
                    this.searchText = '';  // Resetta il campo di ricerca
                    if (this.prodottiVisibili) {
                        // Se i prodotti sono già visibili, li nasconde
                        this.prodotti = [];
                        this.prodottiVisibili = false;
                    } else {
                        // Altrimenti, carica i prodotti
                        this.fetchDb();
                    }
                },

                // Carica tutti i prodotti dal backend
                fetchDb() {
                    fetch('/prodotti/getAll')
                        .then(response => response.json())  // Converte la risposta in JSON
                        .then(data => {
                            this.prodotti = data;  // Salva i prodotti
                            this.prodottiVisibili = true;  // Mostra la lista prodotti
                            this.currentPage = 1; // Reset della paginazione quando si caricano nuovi prodotti
                        })
                        .catch(error => {
                            console.error('Errore nella richiesta:', error);
                        });
                },
                //Motore di ricerca
                searchWithText() {
					if (!this.searchText.trim()) {
                        alert("Inserisci una categoria valida per la ricerca!");
                        return;
                    }                    
                    const url = `prodotti/search/${encodeURIComponent(this.searchText)}`;
					
					fetch(url).then(response => {
						if (!response.ok) {
							alert("Errore nella risposta del server nella ricerca del prodotto" + response.status);
							throw new Error("Errore nella risposta del server");
						}
						return response.json();
					}).then(data => {	
						console.log("RISULTATO API:" + data);
						if (!Array.isArray(data)) {
							console.error("Errore nella risposta del server, non è un array", data);
							alert("Errore nella risposta del server formato non Valido");
							return;
						}
						this.prodotti = data.length ? data : []; // Se vuoto, assegna un array vuoto
						if (this.prodotti.length === 0) {
							alert("Nessun prodotto trovato per la categoria selezionata.");
						}
						this.currentPage = 1; // Resetta la paginazione quando si effettua una ricerca.
						this.prodottiVisibili = true; // Mostra i prodotti dopo la ricerca
					}).catch(error => {
						console.error('Errore nella ricerca:', error);
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
                popolamentoForm(prodotto) {
                    this.openModal();
                    //Importantissimo Passaggio!!!
                    //ridefinisce il newProduct con il id del prodotto!!
                    //così da poterlo passare alla chiamata PUT (Modifica)
                    this.newProduct = {
                        id: prodotto.id,
                        descrizione: prodotto.descrizione,
                        categoria: prodotto.categoria
                    }
                    //popola i campi del form con i dati del prodotto
                    this.newProduct.descrizione = prodotto.descrizione;
                    this.newProduct.categoria = prodotto.categoria;
                    this.submitLabel = 'Modifica';  // Cambia l'etichetta del pulsante

                },
                deleteProdotto(prodotto) {
                    if (!confirm("Sei sicuro di voler eliminare il prodotto: " + prodotto.descrizione + "?")) {
                        return; // Annulla l'operazione se l'utente preme "Annulla"
                    }

                    fetch('/prodotti/delete/' + prodotto.id, {
                        method: 'DELETE'
                    })
                        .then(response => {
                            if (response.ok) {
                                alert("Prodotto eliminato con successo!");
                                this.prodotti = this.prodotti.filter(p => p.id !== prodotto.id); // Rimuovi localmente il prodotto
                            } else {
                                alert("Errore durante l'eliminazione del prodotto.");
                                console.error("Errore DELETE:", response.status);
                            }
                        })
                        .catch(error => {
                            console.error('Errore nella cancellazione del prodotto:', error);
                        });

                    this.newProduct = { descrizione: '', categoria: '' };  // Reset dei campi

                },

                // Mostra il modale
                openModal() {
                    this.newProduct = Object.assign({}, { descrizione: '', categoria: '' });
                    this.showModal = true;  // Imposta il modale come visibile
                    this.submitLabel = 'Aggiungi';  // Cambia l'etichetta del pulsante
                },

                // Chiude il modale
                closeModal() {
                    this.newProduct = Object.assign({}, { descrizione: '', categoria: '' });
                    this.showModal = false;  // Nasconde il modale
                },

                changePage(page) {
                    if (page >= 1 && page <= this.totalPages) {
                        this.currentPage = page;
                    }
                },

                prevPage() {
                    if (this.currentPage > 1) {
                        this.currentPage--;
                    }
                },

                nextPage() {
                    if (this.currentPage < this.totalPages) {
                        this.currentPage++;
                    }
                }

            }
        });

        // Collega Vue all'elemento con ID "applicazione"
        app.mount('#applicazione');