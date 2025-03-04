const app = Vue.createApp({
	data() {
		return {
			message: 'Ciao da file innestato funziona dentro la cartella .js'
		}
	},watch:{}
	,methods:{}
	,computed:{}
	,mounted() {
		
	}	
});

//mount() serve per collegare l'istanza Vue all'elemento HTML è il suo metodo
app.mount('#applicazione');