package com.sedagard.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class PodcastResponseDto(
  val status: Long? = null,
  val success: Boolean? = null,
  val error: Boolean? = null,
  val msg: String? = null,
  val result: Result? = null,
  val action: JsonElement? = null,
  val seo: JsonElement? = null,
  val timestamp: Long? = null,

  val title: String? = null,
  val id: String? = null,
  val description: String? = null,
  val image: String? = null,
  val explicit: Boolean? = null,
  val link: String? = null,
  val author: String? = null,
  val language: String? = null,
  val generator: String? = null,
  val startPodcastDate: String? = null,
  val lastEpisodeDate: String? = null,
  val categories: List<CategoryResponseDto>? = null,
  val email: String? = null,
  val keywords: String? = null,

  @SerialName("newFeedUrl")
  val newFeedURL: String? = null,

  val locked: Locked? = null,
)

@Serializable
data class Result(
  val download: List<Download>? = null,
)

@Serializable
data class Download(
  val id: Long? = null,
  val quality: String? = null,
  val size: String? = null,
  val resolution: String? = null,
  val encoder: String? = null,
  val version: JsonElement? = null,
  val tags: List<String>? = null,
  val part: JsonElement? = null,
  val note: JsonElement? = null,
  val source: JsonElement? = null,
  val dl: String? = null,

  @SerialName("persian_subtitle")
  val persianSubtitle: JsonElement? = null,

  val screenshot: String? = null,
  val mediainfo: String? = null,
)

@Serializable
enum class Locked(val value: String) {
  @SerialName("no")
  No("no"),

  @SerialName("yes")
  Yes("yes"),
}
