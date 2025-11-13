package com.pnfsoftware.jeb.core.events;

import com.pnfsoftware.jeb.util.events.IEvent;

public enum J {
   CoreError,
   Notification,
   FloatingNotification,
   ContextInitialized,
   ContextClosed,
   ProjectLoaded,
   ProjectSaved,
   ProjectUnloaded,
   ProjectClosing,
   ProjectPropertyChanged,
   ArtifactProcessed,
   ArtifactDestroyed,
   ArtifactPropertyChanged,
   UnitCreated,
   UnitDisposed,
   UnitDestroyed,
   UnitProcessed,
   UnitChange,
   UnitPropertyChanged,
   UnitStatusChanged,
   CodeAnalysisCompleted,
   DbgClientNotification,
   DbgAttach,
   DbgDetach,
   DbgRun,
   DbgPause,
   DbgThreadDefault,
   DbgThreadResumed,
   DbgThreadSuspended,
   DbgBreakpointSet,
   DbgBreakpointUnset,
   DbgTargetEvent,
   DecompClientNotification,
   DecompSrcUnitResetEvent,
   PropertyChange;

   public static boolean isContextEvent(IEvent var0) {
      return isEvent(var0, "Context");
   }

   public static boolean isProjectEvent(IEvent var0) {
      return isEvent(var0, "Project");
   }

   public static boolean isArtifactEvent(IEvent var0) {
      return isEvent(var0, "Artifact");
   }

   public static boolean isUnitEvent(IEvent var0) {
      return isEvent(var0, "Unit");
   }

   public static boolean isDebuggerEvent(IEvent var0) {
      return isEvent(var0, "Dbg");
   }

   public static boolean isDecompilerEvent(IEvent var0) {
      return isEvent(var0, "Decomp");
   }

   private static boolean isEvent(IEvent var0, String var1) {
      Object var2 = var0.getType();
      return var2 instanceof J && ((J)var2).toString().startsWith(var1);
   }
}
