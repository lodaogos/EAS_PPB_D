package com.example.karyaseni

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.karyaseni.screens.LoginScreen
import com.example.karyaseni.screens.OtpScreen
import com.example.karyaseni.screens.RegisterScreen
import com.google.firebase.FirebaseApp
import com.example.karyaseni.screens.DetailScreen
import com.example.karyaseni.screens.UploadScreen
import com.example.karyaseni.data.ImageRepository
import com.example.karyaseni.ImageModel

import com.google.firebase.auth.FirebaseAuth
import java.util.UUID
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.karyaseni.screens.MyProfileScreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this) // Add this if not already
        setContent {
            MyGalleryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }
}

// Model data untuk foto
data class ImageModel(
    val id: String,
    val title: String,
    val imageUri: String,
    val description: String,
    val uploader: String,
    val date: String
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF90CAF9),
    secondary = Color(0xFF81D4FA),
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColors = lightColorScheme(
    primary = Color(0xFF1976D2),
    secondary = Color(0xFF0288D1),
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

// Theme configuration
@Composable
fun MyGalleryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

// Navigation for the app
@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("otp") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = { navController.navigate("register") }
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("otp") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable("otp") {
            OtpScreen(
                onOtpVerified = {
                    navController.navigate("gallery") {
                        popUpTo("otp") { inclusive = true }
                    }
                }
            )
        }

        composable("gallery") {
            GalleryScreen(
                onImageClick = { imageId ->
                    navController.navigate("detail/$imageId")
                },
                onUploadClick = {
                    navController.navigate("upload")
                },
                navController = navController
            )
        }

        composable(
            route = "detail/{imageId}",
            arguments = listOf(navArgument("imageId") { type = NavType.StringType })
        ) { backStackEntry ->
            val imageId = backStackEntry.arguments?.getString("imageId") ?: ""
            DetailScreen(
                imageId = imageId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("upload") {
            UploadScreen(
                onBack = {
                    navController.popBackStack()
                },
                onUploadSuccess = {
                    navController.navigate("gallery") {
                        popUpTo("gallery") { inclusive = true }
                    }
                }
            )
        }

        composable("profile") {
            MyProfileScreen(
                navController = navController,
                onBackClick = { navController.popBackStack() }
            )
        }


    }
}

// Gallery Screen - Grid view of images
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun GalleryScreen(
    onImageClick: (String) -> Unit,
    onUploadClick: () -> Unit,
    navController: NavHostController
) {
    val allImages = remember { ImageRepository.getImages().shuffled() }
    var imageCount by remember { mutableStateOf(12) }
    val displayedImages = remember(imageCount) { allImages.take(imageCount) }

    val scrollState = rememberScrollState()

    // Deteksi scroll ke bawah
    LaunchedEffect(scrollState.value) {
        if (scrollState.value == scrollState.maxValue && imageCount < allImages.size) {
            imageCount += 12
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp)
                        )
                        Text("KaryaKita")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // TODO: Implementasi fitur search jika diinginkan
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = onUploadClick) {
                        Icon(Icons.Default.Add, contentDescription = "Upload Image")
                    }
                }

            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
                .verticalScroll(scrollState)
        ) {
            val leftColumn = displayedImages.filterIndexed { index, _ -> index % 2 == 0 }
            val rightColumn = displayedImages.filterIndexed { index, _ -> index % 2 == 1 }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                leftColumn.forEach { image ->
                    ImageItem(image = image, onImageClick = onImageClick)
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rightColumn.forEach { image ->
                    ImageItem(image = image, onImageClick = onImageClick)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePostItem(
    image: ImageModel,
    onImageClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onImageClick(image.id) }
    ) {
        AsyncImage(
            model = image.imageUri,
            contentDescription = image.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp) // feel free to adjust height
        )

        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)) {
            Text(
                text = image.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = image.date,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = image.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(12.dp)) // space between posts
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageItem(
    image: ImageModel,
    onImageClick: (String) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onImageClick(image.id) }
    ) {
        // Gambar di dalam kartu
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            AsyncImage(
                model = image.imageUri,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Tombol titik tiga horizontal di bawah gambar, pojok kanan
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 4.dp, top = 4.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Default.MoreHoriz, // TITIK TIGA HORIZONTAL
                    contentDescription = "Options"
                )
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Share") },
                    onClick = {
                        showMenu = false
                        // TODO: implement share
                    }
                )
                DropdownMenuItem(
                    text = { Text("Download Image") },
                    onClick = {
                        showMenu = false
                        // TODO: implement download
                    }
                )
                DropdownMenuItem(
                    text = { Text("Pin") },
                    onClick = {
                        showMenu = false
                        // TODO: implement pin
                    }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentDestination = navController
        .currentBackStackEntryAsState().value?.destination?.route

    Surface(
        color = MaterialTheme.colorScheme.secondary,
        tonalElevation = 4.dp,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "Home",
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = {
                    Text("Home", fontSize = MaterialTheme.typography.labelSmall.fontSize)
                },
                selected = currentDestination == "gallery",
                onClick = {
                    if (currentDestination != "gallery") {
                        navController.navigate("gallery") {
                            launchSingleTop = true
                            popUpTo("gallery") { inclusive = false }
                        }
                    }
                }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pin),
                        contentDescription = "Pin",
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = {
                    Text("Pin", fontSize = MaterialTheme.typography.labelSmall.fontSize)
                },
                selected = currentDestination == "pin",
                onClick = {
                    if (currentDestination != "pin") {
                        navController.navigate("pin") {
                            launchSingleTop = true
                            popUpTo("gallery") { inclusive = false }
                        }
                    }
                }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Profile",
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = {
                    Text("Profile", fontSize = MaterialTheme.typography.labelSmall.fontSize)
                },
                selected = currentDestination == "profile",
                onClick = {
                    if (currentDestination != "profile") {
                        navController.navigate("profile") {
                            launchSingleTop = true
                            popUpTo("gallery") { inclusive = false }
                        }
                    }
                }
            )
        }
    }
}