package com.example.filmo.model.remote.dataClass

import com.example.filmo.model.dataClass.Actor

data class TitleData(
    var id : String,
    var title : String,
    var originalTitle : String,
    var fullTitle : String,
    var year : String,
    var releaseDate : String,
    var runtimeMins : String,
    var runtimeStr : String,
    var plot : String,
    var plotLocal : String,
    var plotLocalIsRtl : Boolean,
    var awards : String,
    var image : String,
    var type : String,
    var directors : String,
    var directorList : List<StarShort>,
    var writers : String,
    var writerList : List<StarShort>,
    var stars : String,
    var starList : List<StarShort>,
    var actorList : List<Actor>,
    var fullCast : FullCastData,
    var genres : String,
    var genreList : List<KeyValueItem>?,
    var companies : String,
    var companyList : List<CompanyShort>,
    var countries : String,
    var countryList : List<KeyValueItem>,
    var languages : String,
    var languageList : List<KeyValueItem>,
    var contentRating : String,
    var imDbRating : String,
    var imDbRatingVotes : String,
    var metacriticRating : String,
    var ratings : RatingData,
    var wikipedia : WikipediaData,
    var posters : PosterData,
    var images : ImageData,
    var trailer : TrailerData,
    var boxOffice : BoxOfficeShort,
    var tagline : String,
    var keywords : String,
    var keywordList : List<String>,
    var similars : List<SimilarShort>,
    var tvSeriesInfo : TvSeriesInfo,
    var tvEpisodeInfo : TvEpisodeInfo,
    var errorMessage : String,
)

data class TvSeriesInfo(
    var yearEnd:String,
    var creators:String,
    var creatorList:List<Actor>,
    var seasons:List<String>,

)

data class PosterDataItem(
    var id:String,
    var link:String,
    var aspectRatio:Double,
    var language:String,
    var width:Int,
    var height:Int,
)

data class TvEpisodeInfo(
    var seriesId:String,
    var seriesTitle:String,
    var seriesFullTitle:String,
    var seriesYear:String,
    var seriesYearEnd:String,
    var seasonNumber:String,
    var episodeNumber:String,
    var previousEpisodeId:String,
    var nextEpisodeId:String,
)

data class SimilarShort(
    var id:String,
    var title:String,
    var image:String,
    var imDbRating:String,
)

data class StarShort(
    var id:String,
    var name:String,
)

data class BoxOfficeShort(
    var budget:String,
    var openingWeekendUSA:String,
    var grossUSA:String,
    var cumulativeWorldwideGross:String,
)

data class CompanyShort(
    var id:String,
    var name:String,
)

data class FullCastData(
    var imDbId:String,
    var title:String,
    var fullTitle:String,
    var type:String,
    var year:String,
    var directors:CastShort,
    var writers:CastShort,
    var actors:List<ActorShort>,
    var others:List<CastShort>,
    var errorMessage:String,
)

data class CastShort(
    var job:String,
    var items:List<CastShortItem>,
)

data class CastShortItem(
    var id:String,
    var name:String,
    var description:String,
)

data class ActorShort(
    var id:String,
    var image:String,
    var name:String,
    var asCharacter:String,
)

data class PosterData(
    var imDbId:String,
    var title:String,
    var fullTitle:String,
    var type:String,
    var year:String,
    var posters: List<PosterDataItem>,
    var backdors: List<PosterDataItem>,
    var errorMessage:String,
)

data class ImageData(
    var imDbId:String,
    var title:String,
    var fullTitle:String,
    var type:String,
    var year:String,
    var items: List<ImageDataDetail>,
    var errorMessage:String,
)

data class ImageDataDetail(
    var title:String,
    var image:String,
)

data class TrailerData(
    var imDbId:String,
    var title:String,
    var fullTitle:String,
    var type:String,
    var year:String,

    var videoId:String,
    var videoTitle:String,
    var videoDescription:String,
    var thumbnailUrl:String,
    var uploadDate:String,
    var link:String?,
    var linkEmbed:String,

    var errorMessage:String,
)


data class WikipediaData(
    var IMDbId:String,
    var Title:String,
    var FullTitle:String,
    var Type:String,
    var Year:String,

    var Language:String,
    var TitleInLanguage:String,
    var Url:String,
    var PlotShort:WikipediaDataPlot,
    var PlotFull:WikipediaDataPlot,

    var ErrorMessage:String,
)

data class WikipediaDataPlot(
    var PlainText:String,
    var Html:String,
)

data class RatingData(
    var IMDbId:String,
    var Title:String,
    var FullTitle:String,
    var Type:String,
    var Year:String,

    var IMDb:String,
    var Metacritic:String,
    var TheMovieDb:String,
    var RottenTomatoes:WikipediaDataPlot,
    var FilmAffinity:WikipediaDataPlot,

    var ErrorMessage:String,
)



