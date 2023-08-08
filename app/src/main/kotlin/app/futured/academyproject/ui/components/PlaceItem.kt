package app.futured.academyproject.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import app.futured.academyproject.R
import app.futured.academyproject.data.model.local.Place
import app.futured.academyproject.tools.preview.PlacesProvider
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import kotlinx.collections.immutable.PersistentList

@Composable
fun PlaceItem(
    place: Place,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {

    Card(
        modifier = modifier
            .padding(
                vertical = 6.dp,
                horizontal = 12.dp
            )
            .fillMaxWidth()
            .clickable {
                onClick(place.id)
            },
        shape = RoundedCornerShape(
            corner = CornerSize(16.dp),
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(227,237,239)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {

            PlaceImage(place = place)

            Column(
                modifier = Modifier
                    .padding(6.dp),
            ) {
                Text(
                    text = place.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = place.type,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }

}

@Composable
private fun PlaceImage(place: Place) {
    Surface(
        modifier = Modifier
            .padding(12.dp)
            .size(50.dp),
        shape = CircleShape,
        shadowElevation = 4.dp,
        color = Color.LightGray,
    ) {
        val placeImagePainter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(place.image1Url)
                .crossfade(true)
                .transformations(CircleCropTransformation())
                .build(),
            contentScale = ContentScale.Crop,
        )

        Image(
            painter = placeImagePainter,
            contentDescription = stringResource(R.string.place_image),
        )
    }
}


@Preview
@Composable
private fun PlaceItemPreview(
    @PreviewParameter(PlacesProvider::class) places: PersistentList<Place>,
) = Showcase {
    PlaceItem(place = places.first(), onClick = {})
}