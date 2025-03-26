@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BackgroundImage()
        LoginForm()
    }
}

@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(id = R.drawable.bg_image),
        contentDescription = "Background",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f))
    )
}

@Composable
fun LoginForm() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Campus Connect",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "Connect, Share, Succeed",
            fontSize = 16.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(40.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome Back",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Sign in to continue", fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(16.dp))

                InputField(
                    label = "Email Address",
                    icon = Icons.Default.Email
                )

                InputField(
                    label = "Password",
                    icon = Icons.Default.Lock,
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Forgot Password?",
                        fontSize = 14.sp,
                        color = Color.Blue,
                        modifier = Modifier.clickable { }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Handle login */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF5A4FCF)) // Purple color
                ) {
                    Text(text = "Sign In", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("OR", fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = { /* Google Sign-In */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Google")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Continue with Google", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text(text = "Don't have an account?", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Sign Up",
                        fontSize = 14.sp,
                        color = Color.Blue,
                        modifier = Modifier.clickable { }
                    )
                }
            }
        }
    }
}

@Composable
fun InputField(label: String, icon: ImageVector, isPassword: Boolean = false) {
    var text by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            }
        },
        visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    )
}