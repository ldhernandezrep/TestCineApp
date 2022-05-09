package com.example.testcineapplication.repository

import com.example.testcineapplication.core.ConnectionInternet
import com.example.testcineapplication.data.local.*
import com.example.testcineapplication.data.remote.*

class MoviesRoutesRepositoryImple(
    private val dataSource: RemoteMovieResourceDataSource,
    private val localMovieDataSource: LocalMovieDataSource,
    private val localMediaDataSource: LocalMediaDataSource,
    private val localRoutesDataSource: LocalRoutesDataSource
) :
    MoviesResourceRepository {

    /**
     * Obtiene la lista de peliculas y la lista de rutas para bindear en el recycler principal
     */
    override suspend fun getMovies(): MoviesRoute {
        var result: MoviesRoute
        var retun: MoviesRoute
        var resultMovie: List<Movie>
        var listMedia: List<Media> = listOf()

        if (ConnectionInternet.isInternetAvailable()) {
            result = dataSource.getMoviesAndResourcesByCine()
            result.movies.forEach(
                {
                    var movieEntity: MovieEntity = it.toMovieEntity()
                    var idMovie: Int = it.id
                    localMovieDataSource.saveMovie(movieEntity)
                    it.media.forEach({
                        var mediaEntity: MediaEntity = it.toMediaEntity(idMovie)
                        localMediaDataSource.saveMedia(mediaEntity)
                    })
                })
            result.routes.forEach(
                {

                    var routeEntoty: RoutesEntity = RoutesEntity(
                        it.code,
                        it.sizes.large,
                        it.sizes.medium,
                        it.sizes.large,
                        it.sizes.xlarge
                    )
                    localRoutesDataSource.saveRoutes(routeEntoty)
                }
            )

            var listRoute = mutableListOf<Routes>()

            localRoutesDataSource.getRoutesMovies().forEach(
                {
                    var routesSize: RoutesSize =
                        RoutesSize(it.large, it.medium, it.small, it.xlarge)
                    var routes: Routes = Routes(it.code, routesSize)
                    listRoute.add(routes)
                }
            )


            listMedia = localMediaDataSource.getMediaOfMovie().toListMediaMode().results
            resultMovie = localMovieDataSource.getMovies().toListMovie(listMedia).results
            retun = MoviesRoute(resultMovie, listRoute)
        } else {
            var listRoute = mutableListOf<Routes>()

            localRoutesDataSource.getRoutesMovies().forEach(
                {
                    var routesSize: RoutesSize =
                        RoutesSize(it.large, it.medium, it.small, it.xlarge)
                    var routes: Routes = Routes(it.code, routesSize)
                    listRoute.add(routes)
                }
            )

            listMedia = localMediaDataSource.getMediaOfMovie().toListMediaMode().results
            resultMovie = localMovieDataSource.getMovies().toListMovie(listMedia).results
            retun = MoviesRoute(resultMovie, listRoute)
        }
        return retun
    }

    /**
     * Obtiene la pelicula con su recurso de rutas para poder bindear el detalle
     * @param idMovie es el id de la pelicula que queremos consultar es del tipo entero
     */
    override suspend fun getMoviesBYId(idMovie: Int): MoviesRoute {
        var listMedia: List<Media> = listOf()
        var listRoute = mutableListOf<Routes>()
        var listMovie = mutableListOf<Movie>()
        var retun: MoviesRoute
        localRoutesDataSource.getRoutesMovies().forEach(
            {
                var routesSize: RoutesSize =
                    RoutesSize(it.large, it.medium, it.small, it.xlarge)
                var routes: Routes = Routes(it.code, routesSize)
                listRoute.add(routes)
            }
        )
        listMedia = localMediaDataSource.getMediaOfMovieById(idMovie).toListMediaMode().results
        var resultMovie = localMovieDataSource.getMoviesBYId(idMovie)[0].toMovieModel(listMedia)
        listMovie.add(resultMovie)
        retun = MoviesRoute(listMovie, listRoute)
        return retun

    }

}