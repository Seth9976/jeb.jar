package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IArtifact;
import com.pnfsoftware.jeb.core.ICoreContext;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.ILiveArtifact;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.IUnitFilter;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.util.base.Throwables;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

public class UnitUtil {
   public static void notifyGenericChange(IUnit var0) {
      var0.notifyListeners(new JebEvent(J.UnitChange));
   }

   static void log(IUnit var0, String var1, boolean var2, ILogger var3, int var4, String var5, Object... var6) {
      if (var5 == null) {
         var5 = "";
         var6 = new Object[0];
      }

      try {
         if (var1 != null) {
            var5 = var1.replace("%", "%%") + ": " + var5;
         }

         if (var0 != null) {
            if (var2) {
               try {
                  String var7 = Strings.ff(var5, var6);

                  var0.getNotificationManager().addNotification(new UnitNotification(switch (var4) {
                     case 30 -> NotificationType.INFO;
                     case 40 -> NotificationType.WARNING;
                     case 50 -> NotificationType.ERROR;
                     default -> NotificationType.AREA_OF_INTEREST;
                  }, var7, var1));
               } catch (IllegalFormatException | NullPointerException var9) {
               }
            }

            String var11 = buildFullyQualifiedUnitPath(var0);
            if (var11 != null) {
               var5 = Strings.ff("{%s}: %s", var11.replace("%", "%%"), var5);
            }
         }
      } catch (Exception var10) {
         var3.catchingSilent(var10);
      }

      var3.log(var4, false, var5, var6);
   }

   public static void logI(IUnit var0, String var1, ILogger var2, String var3, Object... var4) {
   }

   public static void logTrace(IUnit var0, String var1, ILogger var2, String var3, Object... var4) {
      log(var0, var1, false, var2, 10, var3, var4);
   }

   public static void logInfo(IUnit var0, String var1, boolean var2, ILogger var3, String var4, Object... var5) {
      log(var0, var1, var2, var3, 30, var4, var5);
   }

   public static void logWarn(IUnit var0, String var1, boolean var2, ILogger var3, String var4, Object... var5) {
      log(var0, var1, var2, var3, 40, var4, var5);
   }

   public static void logError(IUnit var0, String var1, boolean var2, ILogger var3, String var4, Object... var5) {
      log(var0, var1, var2, var3, 50, var4, var5);
   }

   public static void logException(IUnit var0, ILogger var1, Exception var2) {
      Throwable var4 = Throwables.getRootCause(var2);
      String var3;
      if (var4 != null && var4 != var2 && var4.getMessage() != null) {
         var3 = Strings.ff(S.L("EXCEPTION: %s (cause: %s)"), var2.getMessage(), var4.getMessage());
      } else {
         var3 = Strings.ff(S.L("EXCEPTION: %s"), var2.getMessage());
      }

      logError(var0, null, false, var1, var3);
      var1.log(10, false, Throwables.formatStacktrace(var2));
   }

   public static List filterDescendants(IUnit var0, int var1, IUnitFilter var2) {
      ArrayList var3 = new ArrayList();

      for (IUnit var5 : var0.getChildren()) {
         filterDescendantsRecurse(var5, var1, var2, var3);
      }

      return var3;
   }

   private static boolean filterDescendantsRecurse(IUnit var0, int var1, IUnitFilter var2, List var3) {
      if (var0 != null && var1 != 0) {
         if (var2 == null) {
            var3.add(var0);
         } else {
            int var4 = var2.check2(var0);
            if ((var4 & 1) != 0) {
               var3.add(var0);
            }

            if ((var4 & 2) != 0) {
               return false;
            }
         }

         if (var1 - 1 != 0) {
            for (IUnit var5 : var0.getChildren()) {
               if (!filterDescendantsRecurse(var5, var1 - 1, var2, var3)) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   public static List findDescendantsByType(IUnit var0, int var1, Class var2, boolean var3) {
      return filterDescendants(var0, var1, new UnitUtil$1(var2, var3));
   }

   public static List findChildrenByType(IUnit var0, Class var1, boolean var2) {
      return findDescendantsByType(var0, 1, var1, var2);
   }

   public static IUnit findChildByType(IUnit var0, Class var1, boolean var2, int var3) {
      List var4 = findChildrenByType(var0, var1, var2);
      return var3 >= var4.size() ? null : (IUnit)var4.get(var3);
   }

   public static IUnit findFirstChildByType(IUnit var0, Class var1, boolean var2) {
      return findChildByType(var0, var1, var2, 0);
   }

   public static List findDescendantsByType(IUnit var0, Class var1, boolean var2) {
      return findDescendantsByType(var0, -1, var1, var2);
   }

   public static List findDescendantsByName(IUnit var0, int var1, String var2) {
      return filterDescendants(var0, var1, new UnitUtil$2(var2));
   }

   public static List findDescendantsByName(IUnit var0, String var1) {
      return findDescendantsByName(var0, -1, var1);
   }

   public static List findChildrenByName(IUnit var0, String var1) {
      return findDescendantsByName(var0, 1, var1);
   }

   public static IUnit findChildByName(IUnit var0, String var1, int var2) {
      List var3 = findChildrenByName(var0, var1);
      return var2 >= var3.size() ? null : (IUnit)var3.get(var2);
   }

   public static IUnit findFirstChildByName(IUnit var0, String var1) {
      return findChildByName(var0, var1, 0);
   }

   public static IUnit retrieve(IUnit var0, String... var1) {
      IUnit var2 = var0;

      for (String var6 : var1) {
         var2 = findFirstChildByName(var2, var6);
         if (var2 == null) {
            return null;
         }
      }

      return var2;
   }

   public static IUnit retrieveProcessed(boolean var0, IUnit var1, String... var2) {
      IUnit var3 = retrieve(var1, var2);
      if (var3 == null || !var0) {
         return null;
      } else {
         return !var3.isProcessed() && !var3.process() ? null : var3;
      }
   }

   public static List findDescendants(IUnit var0, int var1, String var2, Class var3, boolean var4) {
      return filterDescendants(var0, var1, new UnitUtil$3(var2, var3, var4));
   }

   public static List findChildren(IUnit var0, String var1, Class var2, boolean var3) {
      return findDescendants(var0, 1, var1, var2, var3);
   }

   public static IUnit findChild(IUnit var0, String var1, Class var2, boolean var3, int var4) {
      List var5 = findChildren(var0, var1, var2, var3);
      return var4 >= var5.size() ? null : (IUnit)var5.get(var4);
   }

   private static boolean isClassMatch(Object var0, Class var1, boolean var2) {
      return var0.getClass() == var1 || !var2 && var1.isInstance(var0);
   }

   public static List findDescendants(ILiveArtifact var0, int var1, String var2, Class var3, boolean var4) {
      UnitUtil$4 var5 = new UnitUtil$4(var2, var3, var4);
      ArrayList var6 = new ArrayList();

      for (IUnit var8 : var0.getUnits()) {
         filterDescendantsRecurse(var8, var1, var5, var6);
      }

      return var6;
   }

   public static List findAll(String var0, Class var1, boolean var2) {
      ArrayList var3 = new ArrayList();
      ICoreContext var4 = JebCoreService.getExistingInstance();
      if (var4 != null) {
         for (IEnginesContext var6 : var4.listEnginesContexts()) {
            for (IRuntimeProject var8 : var6.getProjects()) {
               for (ILiveArtifact var10 : var8.getLiveArtifacts()) {
                  List var11 = findDescendants(var10, -1, var0, var1, var2);
                  if (var11 != null) {
                     var3.addAll(var11);
                  }
               }
            }
         }
      }

      return var3;
   }

   public static List findDescendantsByFormatType(IUnit var0, int var1, String var2) {
      return filterDescendants(var0, var1, new UnitUtil$5(var2));
   }

   public static List findDescendantsByFormatType(IUnit var0, String var1) {
      return findDescendantsByFormatType(var0, -1, var1);
   }

   public static List findChildrenByFormatType(IUnit var0, String var1) {
      return findDescendantsByFormatType(var0, 1, var1);
   }

   public static IUnit findChildByFormatType(IUnit var0, String var1, int var2) {
      List var3 = findChildrenByFormatType(var0, var1);
      return var2 >= var3.size() ? null : (IUnit)var3.get(var2);
   }

   public static IUnit findFirstChildByFormatType(IUnit var0, String var1) {
      return findChildByFormatType(var0, var1, 0);
   }

   public static IUnitCreator findAncestor(IUnit var0, Class var1, boolean var2) {
      if (var0 != null && var1 != null) {
         for (IUnitCreator var3 = var0.getParent(); var3 != null; var3 = var3.getParent()) {
            if (isClassMatch(var3, var1, var2)) {
               return var3;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static boolean isAncestorOf(IUnit var0, IUnit var1) {
      return isAncestorOf(var0, var1, false);
   }

   public static boolean isAncestorOf(IUnit var0, IUnit var1, boolean var2) {
      if (var0 != null && var1 != null) {
         for (int var3 = 0; var0 != var1 || var2 && var3 <= 0; var3++) {
            if (!(var0.getParent() instanceof IUnit)) {
               return false;
            }

            var0 = (IUnit)var0.getParent();
         }

         return true;
      } else {
         return false;
      }
   }

   public static String buildFullyQualifiedCandidateUnitPath(String var0, IUnitCreator var1, boolean var2, String var3) {
      String var4 = var0;

      for (IUnitCreator var5 = var1; var5 != null && (!(var5 instanceof IArtifact) || var2); var5 = var5.getParent()) {
         var4 = var5.getName() + var3 + var4;
      }

      return var4;
   }

   public static String buildFullyQualifiedUnitPath(IUnit var0, boolean var1, String var2) {
      String var3 = var0.getName();

      for (IUnitCreator var4 = var0.getParent(); var4 != null && (!(var4 instanceof IArtifact) || var1); var4 = var4.getParent()) {
         var3 = var4.getName() + var2 + var3;
      }

      return var3;
   }

   public static List buildFullyQualifiedUnitPathList(IUnitCreator var0, boolean var1) {
      ArrayList var2 = new ArrayList();
      var2.add(var0.getName());

      for (IUnitCreator var3 = var0.getParent(); var3 != null && (!(var3 instanceof IArtifact) || var1); var3 = var3.getParent()) {
         var2.add(0, var3.getName());
      }

      return var2;
   }

   public static String buildFullyQualifiedUnitPath(IUnit var0) {
      return buildFullyQualifiedUnitPath(var0, true, " > ");
   }

   public static boolean isTopLevelUnit(IUnit var0) {
      return var0.getParent() instanceof IArtifact;
   }

   public static String unitProperty(IUnitIdentifier var0, String var1) {
      return var0.getPropertyDefinitionManager().getNamespace() + "." + var1;
   }

   public static String unitProperty(IUnit var0, String var1) {
      return var0.getPropertyDefinitionManager().getNamespace() + "." + var1;
   }

   public static String unitProperty(IEnginesContext var0, String var1, String var2) {
      for (IUnitIdentifier var4 : var0.getUnitIdentifiers()) {
         if (var4.getFormatType().equals(var1)) {
            return unitProperty(var4, var2);
         }
      }

      return null;
   }

   public static String decompilerProperty(IEnginesContext var0, String var1, String var2) {
      String var3 = "dcmp_" + var1;
      return unitProperty(var0, var3, var2);
   }

   public static String debuggerProperty(IEnginesContext var0, String var1, String var2) {
      String var3 = "dbug_" + var1;
      return unitProperty(var0, var3, var2);
   }

   public static List getUnitsFromPathList(IRuntimeProject var0, List var1) {
      if (var1.isEmpty()) {
         return null;
      } else {
         String var2 = (String)var1.get(0);
         ArrayList var3 = new ArrayList();

         for (ILiveArtifact var5 : var0.getLiveArtifacts()) {
            if (var2.equals(var5.getArtifact().getName())) {
               getUnitsFromPathList(var5.getUnits(), var1, 1, var3);
            }
         }

         return var3;
      }
   }

   private static void getUnitsFromPathList(List var0, List var1, int var2, List var3) {
      if (var1.size() > var2) {
         String var4 = (String)var1.get(var2);

         for (IUnit var6 : var0) {
            if (var4.equals(var6.getName()) || var4.equals(var6.getRealName())) {
               if (var1.size() == var2 + 1) {
                  var3.add(var6);
               } else {
                  getUnitsFromPathList(var6.getChildren(), var1, var2 + 1, var3);
               }
            }
         }
      }
   }
}
