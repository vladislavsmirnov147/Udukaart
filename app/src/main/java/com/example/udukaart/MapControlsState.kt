
package com.example.udukaart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class MapControlsState(
    selectedPhotoId: String? = null,
    albumOpen: Boolean = false
) {
    var selectedPhotoId by mutableStateOf(selectedPhotoId)
        private set

    var albumOpen by mutableStateOf(albumOpen)
        private set

    fun selectPhoto(photoId: String) {
        selectedPhotoId =
            if (selectedPhotoId == photoId) null else photoId
    }

    fun setAlbumOpen(open: Boolean) {
        albumOpen = open
    }

    fun toggleAlbum() {
        albumOpen = !albumOpen
    }

    companion object {
        val Saver: Saver<MapControlsState, List<Any>> = Saver(
            save = { state ->
                listOf(
                    state.selectedPhotoId ?: "",
                    state.albumOpen
                )
            },
            restore = { saved ->
                MapControlsState(
                    selectedPhotoId = (saved[0] as String).ifEmpty { null },
                    albumOpen = saved[1] as Boolean
                )
            }
        )
    }
}

@Composable
fun rememberMapControlsState(): MapControlsState {
    return rememberSaveable(saver = MapControlsState.Saver) {
        MapControlsState()
    }
}
