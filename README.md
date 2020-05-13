# [Android] Desafio Viciados em Filmes

# Contexto
Preciso de um app para manter meu vício por filmes em dia. O app terá duas telas. Na tela inicial preciso de um esquema de abas para mostrar 3 listas: 
    - Os filmes mais recentes
    - Os filmes mais populares
    - Os filmes melhores avaliados
Ao clicar em um filme preciso ver todos os detalhes possíveis do filme clicado.
De tempos em tempos eu gosto de seguir um filme então gostaria de marcar um deles como favorito. (preciso também de um indicativo visual que esse filme é favorito ou não na tela inicial) 

# Requisitos
 - Pode fazer em Kotlin ou Java
 - O app deve ter como API mínima o android 4.4
 - Aplicar os conceitos do material design (https://material.io/develop/android/)
 - Para a listagem deve-se usar o componente de RecyclerView (https://developer.android.com/guide/navigation/navigation-swipe-view)
 - Para o esquema de abas usa-se TabLayout com ViewPager (https://developer.android.com/guide/navigation/navigation-swipe-view)
 - Nas requisições REST usar o Retrofit (https://square.github.io/retrofit/)
 - Todas as requisições estão no movieDB (https://developers.themoviedb.org)
    .Filmes mais populares: https://developers.themoviedb.org/3/movies/get-popular-movies
    .Filmes melhores avaliados: https://developers.themoviedb.org/3/movies/get-top-rated-movies
    .Filmes mais recentes: https://developers.themoviedb.org/3/movies/get-latest-movie
    .Detalhes de um filme: https://developers.themoviedb.org/3/movies/get-movie-details
 - Os filmes favoritos serão armazenados no banco local do dispositivo, usando SharedPreferences (https://developer.android.com/training/data-storage/shared-preferences)
 
# Protótipo
//Anexar telas

# Entrega
Você deve fazer o fork desse repositório na sua conta e após finalizado o desafio submeter um pull request para avaliação
