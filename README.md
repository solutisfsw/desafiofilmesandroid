# [Android] Desafio Viciados em Filmes

# Contexto
Preciso de um app para manter meu vício por filmes em dia. O app terá duas telas. Na tela inicial preciso de um esquema de abas para mostrar 3 listas:
* Os filmes mais recentes
* Os filmes mais populares
* Os filmes melhores avaliados

Ao clicar em um filme preciso ver todos os detalhes possíveis do filme clicado. De tempos em tempos eu gosto de seguir um filme então gostaria de marcar um deles como favorito. (preciso também de um indicativo visual que esse filme é favorito ou não nos detalhes do filme) Na tela inicial quero um botão que me leve diretamente ao filme favorito

# Requisitos
* Codificar em Kotlin ou Java
* Deve dar suporte mínimo ao android 4.4
* Para a listagem usar RecyclerView (https://developer.android.com/guide/topics/ui/layout/recyclerview) 
* Para o esquema de abas usar o componente de TabLayout (https://developer.android.com/reference/com/google/android/material/tabs/TabLayout)  (Dica: utilizar o wizard de criação de activity do android studio)
* Nas requisições REST fazer utilizando a biblioteca Retrofit (colocar algum indicativo de loading: https://square.github.io/retrofit/)
* Para apresentação dos posters dos filmes, utilizar a biblioteca Glide (https://github.com/bumptech/glide)
* Todas as requisições estão no movieDB (https://developers.themoviedb.org)
    * Filmes mais populares: https://developers.themoviedb.org/3/movies/get-popular-movies
    * Filmes melhores avaliados: https://developers.themoviedb.org/3/movies/get-top-rated-movies
    * Filmes mais recentes: https://developers.themoviedb.org/3/movies/get-upcoming
    * Detalhes de um filme: https://developers.themoviedb.org/3/movies/get-movie-details
* O filme favorito deve ser armazenado de forma segura no dispositivo, usando o sharedPreferences (https://developer.android.com/reference/android/content/SharedPreferences)(tutorial: https://www.alura.com.br/artigos/salvando-informacoes-com-o-shared-preferences)
* Todos as imagens necessárias estão no repositório, bem como as imagens de prototipo (que também se encontram abaixo)
 
# Protótipo
![inicio](https://github.com/solutisfsw/desafiofilmesandroid/blob/master/screenshots/desafio_inicio.png)
![detalhe 1](https://github.com/solutisfsw/desafiofilmesandroid/blob/master/screenshots/desafio_detalhes1.png)
![detalhe 2](https://github.com/solutisfsw/desafiofilmesandroid/blob/master/screenshots/desafio_detalhes2.png)

# Entrega
Você deve fazer o fork desse repositório na sua conta e após finalizado o desafio submeter um pull request para avaliação :v:
