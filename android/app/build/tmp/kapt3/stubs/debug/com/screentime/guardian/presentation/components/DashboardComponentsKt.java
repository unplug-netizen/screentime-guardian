package com.screentime.guardian.presentation.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001aG\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\f\u0010\r\u001a<\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u0007H\u0007\u001a<\u0010\u0013\u001a\u00020\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u00192\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u001a2\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00032\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u001d2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007\u001a5\u0010 \u001a\u00020\u00012\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00010\u001d\u00a2\u0006\u0002\b\"2\u0006\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u001aQ\u0010%\u001a\u00020\u00012\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00010\u001d\u00a2\u0006\u0002\b\"2\u0006\u0010&\u001a\u00020\u00032\u0006\u0010\'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u001f2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u00192\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"AppUsageItem", "", "appName", "", "appInitial", "usageTime", "progress", "", "progressColor", "Landroidx/compose/ui/graphics/Color;", "modifier", "Landroidx/compose/ui/Modifier;", "AppUsageItem-jzV_Hc0", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FJLandroidx/compose/ui/Modifier;)V", "CircularProgressRing", "currentTime", "goalTime", "remainingTime", "size", "DurationSelector", "durations", "", "", "selectedDuration", "onDurationSelected", "Lkotlin/Function1;", "PrimaryActionButton", "text", "onClick", "Lkotlin/Function0;", "enabled", "", "StatCard", "icon", "Landroidx/compose/runtime/Composable;", "value", "label", "ToggleSettingRow", "title", "subtitle", "isEnabled", "onToggle", "app_debug"})
public final class DashboardComponentsKt {
    
    /**
     * Circular Progress Ring für Dashboard
     * Zeigt verbleibende Zeit / Ziel-Zeit an
     */
    @androidx.compose.runtime.Composable
    public static final void CircularProgressRing(float progress, @org.jetbrains.annotations.NotNull
    java.lang.String currentTime, @org.jetbrains.annotations.NotNull
    java.lang.String goalTime, @org.jetbrains.annotations.NotNull
    java.lang.String remainingTime, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier, float size) {
    }
    
    /**
     * Stat Card für Dashboard
     */
    @androidx.compose.runtime.Composable
    public static final void StatCard(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> icon, @org.jetbrains.annotations.NotNull
    java.lang.String value, @org.jetbrains.annotations.NotNull
    java.lang.String label, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Duration Selector Pills (für Focus-Modus)
     */
    @androidx.compose.runtime.Composable
    public static final void DurationSelector(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Integer> durations, int selectedDuration, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onDurationSelected, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Primary Action Button mit Play-Icon
     */
    @androidx.compose.runtime.Composable
    public static final void PrimaryActionButton(@org.jetbrains.annotations.NotNull
    java.lang.String text, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier, boolean enabled) {
    }
    
    /**
     * Toggle Setting Row (für Focus-Einstellungen)
     */
    @androidx.compose.runtime.Composable
    public static final void ToggleSettingRow(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> icon, @org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    java.lang.String subtitle, boolean isEnabled, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onToggle, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
}