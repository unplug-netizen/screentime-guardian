package com.screentime.guardian.presentation.navigation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u000f\u0010\u0011\u0012B\'\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r\u0082\u0001\u0004\u0013\u0014\u0015\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/screentime/guardian/presentation/navigation/BottomNavItem;", "", "route", "", "label", "selectedIcon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "unselectedIcon", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;Landroidx/compose/ui/graphics/vector/ImageVector;)V", "getLabel", "()Ljava/lang/String;", "getRoute", "getSelectedIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "getUnselectedIcon", "Badges", "Dashboard", "Focus", "Social", "Lcom/screentime/guardian/presentation/navigation/BottomNavItem$Badges;", "Lcom/screentime/guardian/presentation/navigation/BottomNavItem$Dashboard;", "Lcom/screentime/guardian/presentation/navigation/BottomNavItem$Focus;", "Lcom/screentime/guardian/presentation/navigation/BottomNavItem$Social;", "app_debug"})
public abstract class BottomNavItem {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String route = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String label = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.ui.graphics.vector.ImageVector selectedIcon = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.ui.graphics.vector.ImageVector unselectedIcon = null;
    
    private BottomNavItem(java.lang.String route, java.lang.String label, androidx.compose.ui.graphics.vector.ImageVector selectedIcon, androidx.compose.ui.graphics.vector.ImageVector unselectedIcon) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.ui.graphics.vector.ImageVector getSelectedIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.ui.graphics.vector.ImageVector getUnselectedIcon() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/screentime/guardian/presentation/navigation/BottomNavItem$Badges;", "Lcom/screentime/guardian/presentation/navigation/BottomNavItem;", "()V", "app_debug"})
    public static final class Badges extends com.screentime.guardian.presentation.navigation.BottomNavItem {
        @org.jetbrains.annotations.NotNull
        public static final com.screentime.guardian.presentation.navigation.BottomNavItem.Badges INSTANCE = null;
        
        private Badges() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/screentime/guardian/presentation/navigation/BottomNavItem$Dashboard;", "Lcom/screentime/guardian/presentation/navigation/BottomNavItem;", "()V", "app_debug"})
    public static final class Dashboard extends com.screentime.guardian.presentation.navigation.BottomNavItem {
        @org.jetbrains.annotations.NotNull
        public static final com.screentime.guardian.presentation.navigation.BottomNavItem.Dashboard INSTANCE = null;
        
        private Dashboard() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/screentime/guardian/presentation/navigation/BottomNavItem$Focus;", "Lcom/screentime/guardian/presentation/navigation/BottomNavItem;", "()V", "app_debug"})
    public static final class Focus extends com.screentime.guardian.presentation.navigation.BottomNavItem {
        @org.jetbrains.annotations.NotNull
        public static final com.screentime.guardian.presentation.navigation.BottomNavItem.Focus INSTANCE = null;
        
        private Focus() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/screentime/guardian/presentation/navigation/BottomNavItem$Social;", "Lcom/screentime/guardian/presentation/navigation/BottomNavItem;", "()V", "app_debug"})
    public static final class Social extends com.screentime.guardian.presentation.navigation.BottomNavItem {
        @org.jetbrains.annotations.NotNull
        public static final com.screentime.guardian.presentation.navigation.BottomNavItem.Social INSTANCE = null;
        
        private Social() {
        }
    }
}