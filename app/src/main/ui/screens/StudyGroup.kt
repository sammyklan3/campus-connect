package com.example.campusconnect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campusconnect.R
import com.example.campusconnect.model.StudyNote

@Composable
fun StudyNotesScreen(viewModel: StudyNotesViewModel, onUploadClick: () -> Unit) {
    val studyNotes by viewModel.studyNotes.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TopBar(onUploadClick)
        SearchAndFilters()
        StudyNotesList(studyNotes)
    }
}

@Composable
fun TopBar(onUploadClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Study Notes", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        IconButton(onClick = onUploadClick) {
            Icon(painter = painterResource(id = R.drawable.ic_upload), contentDescription = "Upload")
        }
    }
}

@Composable
fun SearchAndFilters() {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        TextField(value = "", onValueChange = {}, placeholder = { Text("Search notes...") }, modifier = Modifier.weight(1f))
        IconButton(onClick = { /* Open filter dialog */ }) {
            Icon(painter = painterResource(id = R.drawable.ic_filter), contentDescription = "Filter")
        }
    }
}

@Composable
fun StudyNotesList(notes: List<StudyNote>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(notes) { note ->
            StudyNoteItem(note)
        }
    }
}

@Composable
fun StudyNoteItem(note: StudyNote) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable
        elevation = 4.dp
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.ic_document), contentDescription = "Note Image", modifier = Modifier.size(50.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(note.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(note.subject, color = Color.Gray, fontSize = 14.sp)
                Text("${note.downloads} downloads • ${note.rating} ⭐", fontSize = 12.sp, color = Color.Gray)
            }
            IconButton(onClick = { /* Download note */ }) {
                Icon(painter = painterResource(id = R.drawable.ic_download), contentDescription = "Download")
            }
        }
    }
}
