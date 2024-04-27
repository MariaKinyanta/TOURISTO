import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import org.unh.touristo.AjouterRestaurantActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminProfileScreen() {
    val context= LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mon compte",textAlign = TextAlign.Center) })
        },

    ) {
        Column(modifier= Modifier.padding(paddingValues = it)) {

        }

    }
}
