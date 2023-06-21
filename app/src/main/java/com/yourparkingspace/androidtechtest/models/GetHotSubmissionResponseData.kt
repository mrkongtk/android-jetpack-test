package com.yourparkingspace.androidtechtest.models

import com.google.gson.annotations.SerializedName

data class GetHotSubmissionResponseData(
    val kind: String,
    val data: RootData
)

data class RootData (
    val after: String,
    val dist: Long,
    val modhash: String,

    @SerializedName("geo_filter")
    val geoFilter: Any? = null,

    val children: List<Child>,
    val before: Any? = null
)

data class Child (
    val kind: Kind,
    val data: ChildData
)

data class ChildData (
    @SerializedName("approved_at_utc")
    val approvedAtUTC: Any? = null,

    val subreddit: Subreddit,
    val selftext: String,

    @SerializedName("author_fullname")
    val authorFullName: String,

    val saved: Boolean,

    @SerializedName("mod_reason_title")
    val modReasonTitle: Any? = null,

    val gilded: Long,
    val clicked: Boolean,
    val title: String,

    @SerializedName("link_flair_richtext")
    val linkFlairRichText: List<Any?>,

    @SerializedName("subreddit_name_prefixed")
    val subredditNamePrefixed: SubredditNamePrefixed,

    val hidden: Boolean,
    val pwls: Long,

    @SerializedName("link_flair_css_class")
    val linkFlairCSSClass: LinkFlairCSSClass,

    val downs: Long,

    @SerializedName("thumbnail_height")
    val thumbnailHeight: Long? = null,

    @SerializedName("top_awarded_type")
    val topAwardedType: Any? = null,

    @SerializedName("hide_score")
    val hideScore: Boolean,

    val name: String,
    val quarantine: Boolean,

    @SerializedName("link_flair_text_color")
    val linkFlairTextColor: LinkFlairTextColor,

    @SerializedName("upvote_ratio")
    val upvoteRatio: Double,

    @SerializedName("author_flair_background_color")
    val authorFlairBackgroundColor: Any? = null,

    @SerializedName("subreddit_type")
    val subredditType: SubredditType,

    val ups: Long,

    @SerializedName("total_awards_received")
    val totalAwardsReceived: Long,

    @SerializedName("thumbnail_width")
    val thumbnailWidth: Long? = null,

    @SerializedName("author_flair_template_id")
    val authorFlairTemplateID: Any? = null,

    @SerializedName("is_original_content")
    val isOriginalContent: Boolean,

    @SerializedName("user_reports")
    val userReports: List<Any?>,

    @SerializedName("secure_media")
    val secureMedia: Any? = null,

    @SerializedName("is_reddit_media_domain")
    val isRedditMediaDomain: Boolean,

    @SerializedName("is_meta")
    val isMeta: Boolean,

    val category: Any? = null,

    @SerializedName("link_flair_text")
    val linkFlairText: String,

    @SerializedName("can_mod_post")
    val canModPost: Boolean,

    val score: Long,

    @SerializedName("approved_by")
    val approvedBy: Any? = null,

    @SerializedName("is_created_from_ads_ui")
    val isCreatedFromAdsUI: Boolean,

    @SerializedName("author_premium")
    val authorPremium: Boolean,

    val thumbnail: String,
    val edited: Boolean,

    @SerializedName("author_flair_css_class")
    val authorFlairCSSClass: Any? = null,

    @SerializedName("author_flair_richtext")
    val authorFlairRichtext: List<Any?>,

    val gildings: Gildings,

    @SerializedName("content_categories")
    val contentCategories: Any? = null,

    @SerializedName("is_self")
    val isSelf: Boolean,

    @SerializedName("mod_note")
    val modNote: Any? = null,

    val created: Double,

    @SerializedName("link_flair_type")
    val linkFlairType: FlairType,

    val wls: Long,

    @SerializedName("removed_by_category")
    val removedByCategory: Any? = null,

    @SerializedName("banned_by")
    val bannedBy: Any? = null,

    @SerializedName("author_flair_type")
    val authorFlairType: FlairType,

    val domain: String,

    @SerializedName("allow_live_comments")
    val allowLiveComments: Boolean,

    @SerializedName("selftext_html")
    val selftextHTML: String? = null,

    val likes: Any? = null,

    @SerializedName("suggested_sort")
    val suggestedSort: Any? = null,

    @SerializedName("banned_at_utc")
    val bannedAtUTC: Any? = null,

    @SerializedName("view_count")
    val viewCount: Any? = null,

    val archived: Boolean,

    @SerializedName("no_follow")
    val noFollow: Boolean,

    @SerializedName("is_crosspostable")
    val isCrosspostable: Boolean,

    val pinned: Boolean,

    @SerializedName("over_18")
    val over18: Boolean,

    @SerializedName("all_awardings")
    val allAwardings: List<AllAwarding>,

    val awarders: List<Any?>,

    @SerializedName("media_only")
    val mediaOnly: Boolean,

    @SerializedName("link_flair_template_id")
    val linkFlairTemplateID: String? = null,

    @SerializedName("can_gild")
    val canGild: Boolean,

    val spoiler: Boolean,
    val locked: Boolean,

    @SerializedName("author_flair_text")
    val authorFlairText: Any? = null,

    @SerializedName("treatment_tags")
    val treatmentTags: List<Any?>,

    val visited: Boolean,

    @SerializedName("removed_by")
    val removedBy: Any? = null,

    @SerializedName("num_reports")
    val numReports: Any? = null,

    val distinguished: Any? = null,

    @SerializedName("subreddit_id")
    val subredditID: SubredditID,

    @SerializedName("author_is_blocked")
    val authorIsBlocked: Boolean,

    @SerializedName("mod_reason_by")
    val modReasonBy: Any? = null,

    @SerializedName("removal_reason")
    val removalReason: Any? = null,

    @SerializedName("link_flair_background_color")
    val linkFlairBackgroundColor: String,

    val id: String,

    @SerializedName("is_robot_indexable")
    val isRobotIndexable: Boolean,

    @SerializedName("report_reasons")
    val reportReasons: Any? = null,

    val author: String,

    @SerializedName("discussion_type")
    val discussionType: Any? = null,

    @SerializedName("num_comments")
    val numComments: Long,

    @SerializedName("send_replies")
    val sendReplies: Boolean,

    @SerializedName("whitelist_status")
    val whitelistStatus: WhitelistStatus,

    @SerializedName("contest_mode")
    val contestMode: Boolean,

    @SerializedName("mod_reports")
    val modReports: List<Any?>,

    @SerializedName("author_patreon_flair")
    val authorPatreonFlair: Boolean,

    @SerializedName("author_flair_text_color")
    val authorFlairTextColor: Any? = null,

    val permalink: String,

    @SerializedName("parent_whitelist_status")
    val parentWhitelistStatus: WhitelistStatus,

    val stickied: Boolean,
    val url: String,

    @SerializedName("subreddit_subscribers")
    val subredditSubscribers: Long,

    @SerializedName("created_utc")
    val createdUTC: Double,

    @SerializedName("num_crossposts")
    val numCrossposts: Long,

    val media: Any? = null,

    @SerializedName("is_video")
    val isVideo: Boolean,

    @SerializedName("post_hint")
    val postHint: PostHint? = null,

    @SerializedName("url_overridden_by_dest")
    val urlOverriddenByDest: String? = null,

    val preview: Preview? = null
)

data class AllAwarding (
    @SerializedName("giver_coin_reward")
    val giverCoinReward: Any? = null,

    @SerializedName("subreddit_id")
    val subredditID: Any? = null,

    @SerializedName("is_new")
    val isNew: Boolean,

    @SerializedName("days_of_drip_extension")
    val daysOfDripExtension: Any? = null,

    @SerializedName("coin_price")
    val coinPrice: Long,

    val id: String,

    @SerializedName("penny_donate")
    val pennyDonate: Any? = null,

    @SerializedName("award_sub_type")
    val awardSubType: String,

    @SerializedName("coin_reward")
    val coinReward: Long,

    @SerializedName("icon_url")
    val iconURL: String,

    @SerializedName("days_of_premium")
    val daysOfPremium: Long? = null,

    @SerializedName("tiers_by_required_awardings")
    val tiersByRequiredAwardings: Any? = null,

    @SerializedName("resized_icons")
    val resizedIcons: List<ResizedIcon>,

    @SerializedName("icon_width")
    val iconWidth: Long,

    @SerializedName("static_icon_width")
    val staticIconWidth: Long,

    @SerializedName("start_date")
    val startDate: Any? = null,

    @SerializedName("is_enabled")
    val isEnabled: Boolean,

    @SerializedName("awardings_required_to_grant_benefits")
    val awardingsRequiredToGrantBenefits: Any? = null,

    val description: String,

    @SerializedName("end_date")
    val endDate: Any? = null,

    @SerializedName("sticky_duration_seconds")
    val stickyDurationSeconds: Any? = null,

    @SerializedName("subreddit_coin_reward")
    val subredditCoinReward: Long,

    val count: Long,

    @SerializedName("static_icon_height")
    val staticIconHeight: Long,

    val name: String,

    @SerializedName("resized_static_icons")
    val resizedStaticIcons: List<ResizedIcon>,

    @SerializedName("icon_format")
    val iconFormat: String? = null,

    @SerializedName("icon_height")
    val iconHeight: Long,

    @SerializedName("penny_price")
    val pennyPrice: Long? = null,

    @SerializedName("award_type")
    val awardType: String,

    @SerializedName("static_icon_url")
    val staticIconURL: String
)

data class ResizedIcon (
    val url: String,
    val width: Long,
    val height: Long
)

enum class FlairType(val value: String) {
    Text("text");

    companion object {
        public fun fromValue(value: String): FlairType = when (value) {
            "text" -> Text
            else   -> throw IllegalArgumentException()
        }
    }
}

data class Gildings (
    @SerializedName("gid_2")
    val gid2: Long? = null
)

enum class LinkFlairCSSClass(val value: String) {
    Adblock("adblock"),
    General("general");

    companion object {
        public fun fromValue(value: String): LinkFlairCSSClass = when (value) {
            "adblock" -> Adblock
            "general" -> General
            else      -> throw IllegalArgumentException()
        }
    }
}

enum class LinkFlairTextColor(val value: String) {
    Dark("dark");

    companion object {
        public fun fromValue(value: String): LinkFlairTextColor = when (value) {
            "dark" -> Dark
            else   -> throw IllegalArgumentException()
        }
    }
}

enum class WhitelistStatus(val value: String) {
    AllAds("all_ads");

    companion object {
        public fun fromValue(value: String): WhitelistStatus = when (value) {
            "all_ads" -> AllAds
            else      -> throw IllegalArgumentException()
        }
    }
}

enum class PostHint(val value: String) {
    Link("link");

    companion object {
        public fun fromValue(value: String): PostHint = when (value) {
            "link" -> Link
            else   -> throw IllegalArgumentException()
        }
    }
}

data class Preview (
    val images: List<Image>,
    val enabled: Boolean
)

data class Image (
    val source: ResizedIcon,
    val resolutions: List<ResizedIcon>,
    val id: String
)

enum class Subreddit(val value: String) {
    Technology("technology");

    companion object {
        public fun fromValue(value: String): Subreddit = when (value) {
            "technology" -> Technology
            else         -> throw IllegalArgumentException()
        }
    }
}

enum class SubredditID(val value: String) {
    T52Qh16("t5_2qh16");

    companion object {
        public fun fromValue(value: String): SubredditID = when (value) {
            "t5_2qh16" -> T52Qh16
            else       -> throw IllegalArgumentException()
        }
    }
}

enum class SubredditNamePrefixed(val value: String) {
    RTechnology("r/technology");

    companion object {
        public fun fromValue(value: String): SubredditNamePrefixed = when (value) {
            "r/technology" -> RTechnology
            else           -> throw IllegalArgumentException()
        }
    }
}

enum class SubredditType(val value: String) {
    Public("public");

    companion object {
        public fun fromValue(value: String): SubredditType = when (value) {
            "public" -> Public
            else     -> throw IllegalArgumentException()
        }
    }
}

enum class Kind(val value: String) {
    T3("t3");

    companion object {
        public fun fromValue(value: String): Kind = when (value) {
            "t3" -> T3
            else -> throw IllegalArgumentException()
        }
    }
}

