package com.safesteps.app.ui.theme

import androidx.compose.ui.graphics.Color

// SafeSteps Color Palette - Safety-focused colors
val EmergencyRed = Color(0xFFE53935)
val EmergencyRedDark = Color(0xFFB71C1C)
val SafetyBlue = Color(0xFF1976D2)
val SafetyBlueDark = Color(0xFF0D47A1)
val CalmTeal = Color(0xFF00897B)
val CalmTealDark = Color(0xFF004D40)
val WarningAmber = Color(0xFFFF8F00)
val SuccessGreen = Color(0xFF43A047)

// Light Theme Colors
val PrimaryLight = SafetyBlue
val OnPrimaryLight = Color.White
val PrimaryContainerLight = Color(0xFFBBDEFB)
val OnPrimaryContainerLight = SafetyBlueDark

val SecondaryLight = CalmTeal
val OnSecondaryLight = Color.White
val SecondaryContainerLight = Color(0xFFB2DFDB)
val OnSecondaryContainerLight = CalmTealDark

val TertiaryLight = Color(0xFF7C4DFF)
val OnTertiaryLight = Color.White

val BackgroundLight = Color(0xFFF5F5F5)
val OnBackgroundLight = Color(0xFF212121)
val SurfaceLight = Color.White
val OnSurfaceLight = Color(0xFF212121)
val SurfaceVariantLight = Color(0xFFE0E0E0)
val OnSurfaceVariantLight = Color(0xFF616161)

val ErrorLight = EmergencyRed
val OnErrorLight = Color.White
val ErrorContainerLight = Color(0xFFFFCDD2)
val OnErrorContainerLight = EmergencyRedDark

// Dark Theme Colors
val PrimaryDark = Color(0xFF90CAF9)
val OnPrimaryDark = SafetyBlueDark
val PrimaryContainerDark = SafetyBlue
val OnPrimaryContainerDark = Color(0xFFBBDEFB)

val SecondaryDark = Color(0xFF80CBC4)
val OnSecondaryDark = CalmTealDark
val SecondaryContainerDark = CalmTeal
val OnSecondaryContainerDark = Color(0xFFB2DFDB)

val TertiaryDark = Color(0xFFB388FF)
val OnTertiaryDark = Color(0xFF311B92)

val BackgroundDark = Color(0xFF121212)
val OnBackgroundDark = Color(0xFFE0E0E0)
val SurfaceDark = Color(0xFF1E1E1E)
val OnSurfaceDark = Color(0xFFE0E0E0)
val SurfaceVariantDark = Color(0xFF2C2C2C)
val OnSurfaceVariantDark = Color(0xFFBDBDBD)

val ErrorDark = Color(0xFFEF9A9A)
val OnErrorDark = EmergencyRedDark
val ErrorContainerDark = EmergencyRed
val OnErrorContainerDark = Color(0xFFFFCDD2)