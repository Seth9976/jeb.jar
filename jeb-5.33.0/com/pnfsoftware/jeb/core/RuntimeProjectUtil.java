package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.Or;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuntimeProjectUtil {
   private static final ILogger logger = GlobalLog.getLogger(RuntimeProjectUtil.class);

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   public static IRuntimeProject findProject(IUnitCreator var0) {
      IUnitCreator var1 = var0;

      while (var1 instanceof IUnit) {
         var1 = ((IUnit)var1).getParent();
      }

      if (var1 == null) {
         logger.error(S.L("No parent artifact found for unit. Is the project being deserialized?"));
         return null;
      } else {
         IArtifact var2 = (IArtifact)var1;

         Iterator var3;
         try {
            var3 = JebCoreService.getInstance().listEnginesContexts().iterator();
         } catch (JebException var10) {
            return null;
         }

         while (true) {
            Iterator var5;
            try {
               if (!var3.hasNext()) {
                  break;
               }

               IEnginesContext var4 = (IEnginesContext)var3.next();
               var5 = var4.getProjects().iterator();
            } catch (JebException var9) {
               break;
            }

            while (true) {
               IRuntimeProject var6;
               Iterator var7;
               try {
                  if (!var5.hasNext()) {
                     break;
                  }

                  var6 = (IRuntimeProject)var5.next();
                  var7 = var6.getLiveArtifacts().iterator();
               } catch (JebException var12) {
                  return null;
               }

               while (true) {
                  try {
                     if (var7.hasNext()) {
                        ILiveArtifact var8 = (ILiveArtifact)var7.next();
                        if (var8.getArtifact() != var2) {
                           continue;
                        }

                        return var6;
                     }
                     break;
                  } catch (JebException var11) {
                     return null;
                  }
               }
            }
         }

         return null;
      }
   }

   public static List getAllUnits(IRuntimeProject var0) {
      ArrayList var1 = new ArrayList();

      for (ILiveArtifact var3 : var0.getLiveArtifacts()) {
         for (IUnit var5 : var3.getUnits()) {
            getAllUnitsRecurse(var1, var5);
         }
      }

      return var1;
   }

   private static void getAllUnitsRecurse(List var0, IUnit var1) {
      if (var1 != null) {
         var0.add(var1);

         for (IUnit var3 : var1.getChildren()) {
            getAllUnitsRecurse(var0, var3);
         }
      }
   }

   public static IUnit findFirstUnitByType(IRuntimeProject var0, Class var1, boolean var2) {
      if (var0 != null) {
         for (ILiveArtifact var4 : var0.getLiveArtifacts()) {
            for (IUnit var6 : var4.getUnits()) {
               IUnit var7 = findFirstUnitByTypeRecurse(var6, var1, var2);
               if (var7 != null) {
                  return var7;
               }
            }
         }
      }

      return null;
   }

   private static IUnit findFirstUnitByTypeRecurse(IUnit var0, Class var1, boolean var2) {
      if (var0 != null) {
         if (var1 == null || var1 == var0.getClass() || !var2 && var1.isInstance(var0)) {
            return var0;
         }

         for (IUnit var4 : var0.getChildren()) {
            IUnit var5 = findFirstUnitByTypeRecurse(var4, var1, var2);
            if (var5 != null) {
               return var5;
            }
         }
      }

      return null;
   }

   public static List findUnits(IRuntimeProject var0, Class var1) {
      return findUnitsByType(var0, var1, false);
   }

   public static List findUnitsByType(IRuntimeProject var0, Class var1, boolean var2) {
      ArrayList var3 = new ArrayList();
      if (var0 != null) {
         for (ILiveArtifact var5 : var0.getLiveArtifacts()) {
            for (IUnit var7 : var5.getUnits()) {
               findUnitsByTypeRecurse(var7, var1, var2, var3);
            }
         }
      }

      return var3;
   }

   private static void findUnitsByTypeRecurse(IUnit var0, Class var1, boolean var2, List var3) {
      if (var0 != null) {
         if (var1 == null || var1 == var0.getClass() || !var2 && var1.isInstance(var0)) {
            var3.add(var0);
         }

         for (IUnit var5 : var0.getChildren()) {
            findUnitsByTypeRecurse(var5, var1, var2, var3);
         }
      }
   }

   public static IUnit findUnitByUid(IRuntimeProject var0, long var1) {
      return (IUnit)Lists.getFirst(filterUnits(var0, new RuntimeProjectUtil$1(var1)));
   }

   public static List filterUnits(IRuntimeProject var0, IUnitFilter var1) {
      ArrayList var2 = new ArrayList();

      for (ILiveArtifact var4 : var0.getLiveArtifacts()) {
         for (IUnit var6 : var4.getUnits()) {
            filterUnitsRecurse(var6, var1, var2);
         }
      }

      return var2;
   }

   private static boolean filterUnitsRecurse(IUnit var0, IUnitFilter var1, List var2) {
      if (var0 != null) {
         if (var1 == null) {
            var2.add(var0);
         } else {
            int var3 = var1.check2(var0);
            if ((var3 & 1) != 0) {
               var2.add(var0);
            }

            if ((var3 & 2) != 0) {
               return false;
            }
         }

         for (IUnit var4 : var0.getChildren()) {
            if (!filterUnitsRecurse(var4, var1, var2)) {
               return false;
            }
         }
      }

      return true;
   }

   public static List findUnitContributions(IRuntimeProject var0, IUnit var1) {
      RuntimeProjectUtil$1Filter var2 = new RuntimeProjectUtil$1Filter(var1);
      filterUnits(var0, var2);
      return var2.getContributions();
   }

   public static boolean destroyLiveArtifact(ILiveArtifact var0) {
      if (var0.getRuntimeProject() instanceof Or) {
         Or var1 = (Or)var0.getRuntimeProject();
         return var1.pC(var0);
      } else {
         return false;
      }
   }

   public static boolean hasNotification(IRuntimeProject var0) {
      return hasNotification(var0, 0);
   }

   public static boolean hasNotification(IRuntimeProject var0, int var1) {
      for (ILiveArtifact var3 : var0.getLiveArtifacts()) {
         for (IUnit var5 : var3.getUnits()) {
            if (hasNotificationRecurse(var5, var1)) {
               return true;
            }
         }
      }

      return false;
   }

   private static boolean hasNotificationRecurse(IUnit param0, int param1) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 00: aload 0
      // 01: invokeinterface com/pnfsoftware/jeb/core/units/IUnit.getNotificationManager ()Lcom/pnfsoftware/jeb/core/units/IUnitNotificationManager; 1
      // 06: invokeinterface com/pnfsoftware/jeb/core/units/IUnitNotificationManager.getNotifications ()Ljava/util/List; 1
      // 0b: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 10: astore 2
      // 11: aload 2
      // 12: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 17: ifeq 36
      // 1a: aload 2
      // 1b: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 20: checkcast com/pnfsoftware/jeb/core/units/IUnitNotification
      // 23: astore 3
      // 24: aload 3
      // 25: invokeinterface com/pnfsoftware/jeb/core/units/IUnitNotification.getType ()Lcom/pnfsoftware/jeb/core/units/NotificationType; 1
      // 2a: invokevirtual com/pnfsoftware/jeb/core/units/NotificationType.getLevel ()I
      // 2d: iload 1
      // 2e: if_icmplt 33
      // 31: bipush 1
      // 32: ireturn
      // 33: goto 11
      // 36: goto 43
      // 39: astore 2
      // 3a: getstatic com/pnfsoftware/jeb/core/RuntimeProjectUtil.logger Lcom/pnfsoftware/jeb/util/logging/ILogger;
      // 3d: aload 2
      // 3e: invokeinterface com/pnfsoftware/jeb/util/logging/ILogger.catching (Ljava/lang/Throwable;)V 2
      // 43: aload 0
      // 44: invokeinterface com/pnfsoftware/jeb/core/units/IUnit.getChildren ()Ljava/util/List; 1
      // 49: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 4e: astore 2
      // 4f: aload 2
      // 50: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 55: ifeq 6f
      // 58: aload 2
      // 59: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 5e: checkcast com/pnfsoftware/jeb/core/units/IUnit
      // 61: astore 3
      // 62: aload 3
      // 63: iload 1
      // 64: invokestatic com/pnfsoftware/jeb/core/RuntimeProjectUtil.hasNotificationRecurse (Lcom/pnfsoftware/jeb/core/units/IUnit;I)Z
      // 67: ifeq 6c
      // 6a: bipush 1
      // 6b: ireturn
      // 6c: goto 4f
      // 6f: bipush 0
      // 70: ireturn
   }
}
