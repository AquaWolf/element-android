/*
 * Copyright (c) 2020 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.app.features.homeserver

import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import org.matrix.android.sdk.internal.di.MoshiProvider

@JsonClass(generateAdapter = true)
data class ElementWellKnown(
        /**
         *Preferred Jitsi domain, provided in Wellknown
         */
        @Json(name = "im.vector.riot.jitsi")
        val jitsiServer: WellKnownPreferredConfig? = null,

        /**
         * The settings above were first proposed under a im.vector.riot.e2ee key, which is now deprecated.
         * Element will check for either key, preferring io.element.e2ee if both exist.
         */
        @Json(name = "io.element.e2ee")
        val elementE2E: E2EWellKnownConfig? = null,

        @Json(name = "im.vector.riot.e2ee")
        val riotE2E: E2EWellKnownConfig? = null
)

object ElementWellKnownMapper {

    val adapter: JsonAdapter<ElementWellKnown> = MoshiProvider.providesMoshi().adapter(ElementWellKnown::class.java)

    fun from(value: String): ElementWellKnown? {
        return adapter.fromJson(value)
    }
}

@JsonClass(generateAdapter = true)
data class E2EWellKnownConfig(
        /**
         * Option to allow homeserver admins to set the default E2EE behaviour back to disabled for DMs / private rooms
         * (as it was before) for various environments where this is desired.
         */
        @Json(name = "default")
        val e2eDefault: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class WellKnownPreferredConfig(
        @Json(name = "preferredDomain")
        val preferredDomain: String? = null
)

fun ElementWellKnown.isE2EByDefault() = elementE2E?.e2eDefault ?: riotE2E?.e2eDefault ?: true
