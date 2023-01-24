package com.mudassir.data.model

import com.squareup.moshi.Json

data class GiphyRemoteResponse(
    @Json(name = "data")
    val `data`: List<GiphyData>? = null ,
    @Json(name = "meta")
    val meta: Meta? = null ,
    @Json(name = "pagination")
    val pagination: Pagination? = null
) {
    data class GiphyData(
        @Json(name = "analytics")
        val analytics: Analytics? = null ,
        @Json(name = "analytics_response_payload")
        val analyticsResponsePayload: String? = null ,
        @Json(name = "bitly_gif_url")
        val bitlyGifUrl: String? = null ,
        @Json(name = "bitly_url")
        val bitlyUrl: String? = null ,
        @Json(name = "content_url")
        val contentUrl: String? = null ,
        @Json(name = "embed_url")
        val embedUrl: String? = null ,
        @Json(name = "id")
        val id: String? = null ,
        @Json(name = "images")
        val images: Images? = null ,
        @Json(name = "import_datetime")
        val importDatetime: String? = null ,
        @Json(name = "is_sticker")
        val isSticker: Int? = null ,
        @Json(name = "rating")
        val rating: String? = null ,
        @Json(name = "slug")
        val slug: String? = null ,
        @Json(name = "source")
        val source: String? = null ,
        @Json(name = "source_post_url")
        val sourcePostUrl: String? = null ,
        @Json(name = "source_tld")
        val sourceTld: String? = null ,
        @Json(name = "title")
        val title: String? = null ,
        @Json(name = "trending_datetime")
        val trendingDatetime: String? = null ,
        @Json(name = "type")
        val type: String? = null ,
        @Json(name = "url")
        val url: String? = null ,
        @Json(name = "user")
        val user: User? = null ,
        @Json(name = "username")
        val username: String? = null
    ) {
        data class Analytics(
            @Json(name = "onclick")
            val onclick: Onclick? = null ,
            @Json(name = "onload")
            val onload: Onload? = null ,
            @Json(name = "onsent")
            val onsent: Onsent? = null
        ) {
            data class Onclick(
                @Json(name = "url")
                val url: String? = null
            )

            data class Onload(
                @Json(name = "url")
                val url: String? = null
            )

            data class Onsent(
                @Json(name = "url")
                val url: String? = null
            )
        }

        data class Images(
            @Json(name = "downsized")
            val downsized: Downsized? = null ,
            @Json(name = "downsized_large")
            val downsizedLarge: DownsizedLarge? = null ,
            @Json(name = "downsized_medium")
            val downsizedMedium: DownsizedMedium? = null ,
            @Json(name = "downsized_small")
            val downsizedSmall: DownsizedSmall? = null ,
            @Json(name = "downsized_still")
            val downsizedStill: DownsizedStill? = null ,
            @Json(name = "fixed_height")
            val fixedHeight: FixedHeight? = null ,
            @Json(name = "fixed_height_downsampled")
            val fixedHeightDownsampled: FixedHeightDownsampled? = null ,
            @Json(name = "fixed_height_small")
            val fixedHeightSmall: FixedHeightSmall? = null ,
            @Json(name = "fixed_height_small_still")
            val fixedHeightSmallStill: FixedHeightSmallStill? = null ,
            @Json(name = "fixed_height_still")
            val fixedHeightStill: FixedHeightStill? = null ,
            @Json(name = "fixed_width")
            val fixedWidth: FixedWidth? = null ,
            @Json(name = "fixed_width_downsampled")
            val fixedWidthDownsampled: FixedWidthDownsampled? = null ,
            @Json(name = "fixed_width_small")
            val fixedWidthSmall: FixedWidthSmall? = null ,
            @Json(name = "fixed_width_small_still")
            val fixedWidthSmallStill: FixedWidthSmallStill? = null ,
            @Json(name = "fixed_width_still")
            val fixedWidthStill: FixedWidthStill? = null ,
            @Json(name = "looping")
            val looping: Looping? = null ,
            @Json(name = "original")
            val original: Original? = null ,
            @Json(name = "original_mp4")
            val originalMp4: OriginalMp4? = null ,
            @Json(name = "original_still")
            val originalStill: OriginalStill? = null ,
            @Json(name = "preview")
            val preview: Preview? = null ,
            @Json(name = "preview_gif")
            val previewGif: PreviewGif? = null ,
            @Json(name = "preview_webp")
            val previewWebp: PreviewWebp? = null ,
            @Json(name = "480w_still")
            val wStill: WStill? = null
        ) {
            data class Downsized(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class DownsizedLarge(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class DownsizedMedium(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class DownsizedSmall(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "mp4")
                val mp4: String? = null ,
                @Json(name = "mp4_size")
                val mp4Size: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class DownsizedStill(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class FixedHeight(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "mp4")
                val mp4: String? = null ,
                @Json(name = "mp4_size")
                val mp4Size: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "webp")
                val webp: String? = null ,
                @Json(name = "webp_size")
                val webpSize: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class FixedHeightDownsampled(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "webp")
                val webp: String? = null ,
                @Json(name = "webp_size")
                val webpSize: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class FixedHeightSmall(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "mp4")
                val mp4: String? = null ,
                @Json(name = "mp4_size")
                val mp4Size: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "webp")
                val webp: String? = null ,
                @Json(name = "webp_size")
                val webpSize: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class FixedHeightSmallStill(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class FixedHeightStill(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class FixedWidth(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "mp4")
                val mp4: String? = null ,
                @Json(name = "mp4_size")
                val mp4Size: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "webp")
                val webp: String? = null ,
                @Json(name = "webp_size")
                val webpSize: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class FixedWidthDownsampled(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "webp")
                val webp: String? = null ,
                @Json(name = "webp_size")
                val webpSize: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class FixedWidthSmall(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "mp4")
                val mp4: String? = null ,
                @Json(name = "mp4_size")
                val mp4Size: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "webp")
                val webp: String? = null ,
                @Json(name = "webp_size")
                val webpSize: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class FixedWidthSmallStill(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class FixedWidthStill(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class Looping(
                @Json(name = "mp4")
                val mp4: String? = null ,
                @Json(name = "mp4_size")
                val mp4Size: String? = null
            )

            data class Original(
                @Json(name = "frames")
                val frames: String? = null ,
                @Json(name = "hash")
                val hash: String? = null ,
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "mp4")
                val mp4: String? = null ,
                @Json(name = "mp4_size")
                val mp4Size: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "webp")
                val webp: String? = null ,
                @Json(name = "webp_size")
                val webpSize: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class OriginalMp4(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "mp4")
                val mp4: String? = null ,
                @Json(name = "mp4_size")
                val mp4Size: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class OriginalStill(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class Preview(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "mp4")
                val mp4: String? = null ,
                @Json(name = "mp4_size")
                val mp4Size: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class PreviewGif(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class PreviewWebp(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )

            data class WStill(
                @Json(name = "height")
                val height: String? = null ,
                @Json(name = "size")
                val size: String? = null ,
                @Json(name = "url")
                val url: String? = null ,
                @Json(name = "width")
                val width: String? = null
            )
        }

        data class User(
            @Json(name = "avatar_url")
            val avatarUrl: String? = null ,
            @Json(name = "banner_image")
            val bannerImage: String? = null ,
            @Json(name = "banner_url")
            val bannerUrl: String? = null ,
            @Json(name = "description")
            val description: String? = null ,
            @Json(name = "display_name")
            val displayName: String? = null ,
            @Json(name = "instagram_url")
            val instagramUrl: String? = null ,
            @Json(name = "is_verified")
            val isVerified: Boolean? = null ,
            @Json(name = "profile_url")
            val profileUrl: String? = null ,
            @Json(name = "username")
            val username: String? = null ,
            @Json(name = "website_url")
            val websiteUrl: String? = null
        )
    }

    data class Meta(
        @Json(name = "msg")
        val msg: String? = null ,
        @Json(name = "response_id")
        val responseId: String? = null ,
        @Json(name = "status")
        val status: Int? = 0
    )

    data class Pagination(
        @Json(name = "count")
        val count: Int? = null ,
        @Json(name = "offset")
        val offset: Int? = null ,
        @Json(name = "total_count")
        val totalCount: Int = 0
    )
}