import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(userId: String) {
    val userProfile = remember { mutableStateOf(UserProfile()) }
    val coroutineScope = rememberCoroutineScope()
    
    LaunchedEffect(userId) {
        coroutineScope.launch {
            userProfile.value = fetchUserProfile(userId)
        }
    }
    
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        ProfileHeader(userProfile.value)
        ProfileStats(userProfile.value)
        ProfileInterests(userProfile.value.interests)
        ProfileTabs()
    }
}

@Composable
fun ProfileHeader(profile: UserProfile) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = profile.profileImage),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp).background(Color.Gray, CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(profile.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("${profile.course} • ${profile.year}", fontSize = 14.sp, color = Color.Gray)
            Text(profile.bio, fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}

@Composable
fun ProfileStats(profile: UserProfile) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatItem(profile.posts, "Posts")
        StatItem(profile.notes, "Notes")
        StatItem(profile.groups, "Groups")
        StatItem(profile.connections, "Connections")
    }
}

@Composable
fun StatItem(value: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(label, fontSize = 14.sp, color = Color.Gray)
    }
}

@Composable
fun ProfileInterests(interests: List<String>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Interests", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Row(modifier = Modifier.fillMaxWidth()) {
            interests.forEach {
                Chip(text = it)
            }
        }
    }
}

@Composable
fun Chip(text: String) {
    Box(
        modifier = Modifier.padding(4.dp).background(Color.LightGray, RoundedCornerShape(16.dp)).padding(8.dp)
    ) {
        Text(text, fontSize = 12.sp, color = Color.Black)
    }
}

@Composable
fun ProfileTabs() {
    val tabTitles = listOf("Activity", "Notes", "Groups", "Events")
    var selectedTab by remember { mutableStateOf(0) }
    
    Column {
        TabRow(selectedTabIndex = selectedTab) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }
    }
}

data class UserProfile(
    val name: String = "",
    val course: String = "",
    val year: String = "",
    val bio: String = "",
    val profileImage: Int = 0,
    val posts: Int = 0,
    val notes: Int = 0,
    val groups: Int = 0,
    val connections: Int = 0,
    val interests: List<String> = emptyList()
)

suspend fun fetchUserProfile(userId: String): UserProfile {
    return UserProfile(
        name = "sam k",
        course = "Computer Science",
        year = "3rd Year",
        bio = "Passionate about AI and ML.",
        profileImage = 0,
        posts = 24,
        notes = 8,
        groups = 5,
        connections = 42,
        interests = listOf("Machine Learning", "Web Development", "Data Science")
    )
}