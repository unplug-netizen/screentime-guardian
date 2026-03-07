package com.screentime.guardian.presentation.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u001a<\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u001aB\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f2\u001c\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u0007\u00a2\u0006\u0002\b\u000f\u00a2\u0006\u0002\b\u0010H\u0007\u001a2\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\f2\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007\u001aQ\u0010\u0016\u001a\u00020\u00012\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\f\u00a2\u0006\u0002\b\u000f2\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u00a8\u0006\u001c"}, d2 = {"DurationSelector", "", "durations", "", "", "selectedDuration", "onDurationSelected", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "GlassCard", "onClick", "Lkotlin/Function0;", "content", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "PrimaryActionButton", "text", "", "enabled", "", "ToggleSettingRow", "icon", "title", "subtitle", "isEnabled", "onToggle", "app_debug"})
public final class ButtonsKt {
    
    /**
     * Glass Card Component
     * Background: 5% opacity white
     * Border: 1px 10% white
     * Border-Radius: 16px
     */
    @androidx.compose.runtime.Composable
    public static final void GlassCard(@org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super androidx.compose.foundation.layout.BoxScope, kotlin.Unit> content) {
    }
    
    /**
     * Primary Action Button (Filled Pill)
     * Background: #34D399
     * Height: 56dp
     * Border-Radius: 28dp
     */
    @androidx.compose.runtime.Composable
    public static final void PrimaryActionButton(@org.jetbrains.annotations.NotNull
    java.lang.String text, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier, boolean enabled) {
    }
    
    /**
     * Duration Selector Pills
     */
    @androidx.compose.runtime.Composable
    public static final void DurationSelector(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Integer> durations, int selectedDuration, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onDurationSelected, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Toggle Setting Row
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