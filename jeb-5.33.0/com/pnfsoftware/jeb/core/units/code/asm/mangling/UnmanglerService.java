package com.pnfsoftware.jeb.core.units.code.asm.mangling;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.auu;
import com.pnfsoftware.jebglobal.avp;
import com.pnfsoftware.jebglobal.avq;
import com.pnfsoftware.jebglobal.avr;
import com.pnfsoftware.jebglobal.avt;
import com.pnfsoftware.jebglobal.avu;
import com.pnfsoftware.jebglobal.awk;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UnmanglerService {
   private static final ILogger logger = GlobalLog.getLogger(UnmanglerService.class);
   public static final int MANGLING_ENGINE_MSVC = 1;
   public static final int MANGLING_ENGINE_MSVCPP = 2;
   public static final int MANGLING_ENGINE_MSVCPP_V2 = 3;
   public static final int MANGLING_ENGINE_CXXA = 4;
   private Map enginesMap = new ConcurrentHashMap();
   private INativeCodeUnit codeUnit;

   public UnmanglerService(INativeCodeUnit var1) {
      this.codeUnit = var1;
   }

   public IManglingEngine getRegisteredEngine(int var1) {
      return (IManglingEngine)this.enginesMap.get(var1);
   }

   public synchronized boolean registerEngine(int var1) {
      if (this.enginesMap.containsKey(var1)) {
         return false;
      } else {
         Object var2;
         if (var1 == 1) {
            var2 = new avp();
         } else if (var1 == 3) {
            var2 = new avq();
         } else {
            if (var1 != 4) {
               return false;
            }

            var2 = new awk();
         }

         this.enginesMap.put(var1, var2);
         return true;
      }
   }

   public synchronized boolean unregisterEngine(int var1) {
      return this.enginesMap.remove(var1) != null;
   }

   public synchronized void unregisterAllEngines() {
      this.enginesMap.clear();
   }

   public boolean importUnmangledRoutineName(INativeMethodItem var1, String var2, IUnmangledRoutine var3, boolean var4) {
      if (var1 instanceof auu && var3 != null && var1.getName(true) != null) {
         String var5 = var3.getName();
         if (var5 != null) {
            ((auu)var1).pC(var2);
            ((auu)var1).kS(var5);
            var1.setName(var5);
            String var6 = var3.getFull();
            if (var6 != null) {
               ((auu)var1).A(var6);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean importUnmangledRoutinePrototype(INativeMethodItem var1, IUnmangledRoutine var2) {
      if (var1 instanceof auu && var2 != null) {
         String var3 = var2.getSignature(true, true);
         return var3 != null ? this.codeUnit.setRoutineSignature(var1, var3, true) : false;
      } else {
         return false;
      }
   }

   public IUnmangledRoutine unmangleRoutine(String var1, boolean var2) {
      if (this.enginesMap.isEmpty()) {
         return null;
      } else {
         Object var3 = null;

         for (IManglingEngine var5 : this.enginesMap.values()) {
            IUnmangledData var6 = var5.unmangle(var1);
            if (var6 instanceof IUnmangledRoutine var7) {
               if (var2) {
                  return var7;
               }

               if (var3 != null && !var7.getFull().equals(((IUnmangledRoutine)var3).getFull())) {
                  logger.warn("> " + S.L("conflicting results from unmangling engines (%s , %s)"), ((IUnmangledRoutine)var3).getFull(), var7.getFull());
                  return null;
               }

               var3 = var7;
            } else if (var3 == null && var6 instanceof IUnmangledData) {
               var3 = new avu(avr.pC(var6.getFull()), var6.getFull());
            }
         }

         return (IUnmangledRoutine)var3;
      }
   }

   public IUnmangledData unmangleData(String var1, boolean var2) {
      if (this.enginesMap.isEmpty()) {
         return null;
      } else {
         avt var3 = null;

         for (IManglingEngine var5 : this.enginesMap.values()) {
            if (var5.unmangle(var1) instanceof avt var7) {
               if (var2) {
                  return var7;
               }

               if (var3 != null && !var7.getFull().equals(var3.getFull())) {
                  logger.warn("> " + S.L("conflicting results from unmangling engines (%s , %s)"), var3.getFull(), var7.getFull());
                  return null;
               }

               var3 = var7;
            }
         }

         return var3;
      }
   }

   public IUnmangledData unmangle(String var1, boolean var2) {
      if (this.enginesMap.isEmpty()) {
         return null;
      } else {
         IUnmangledData var3 = null;

         for (IManglingEngine var5 : this.enginesMap.values()) {
            IUnmangledData var6 = var5.unmangle(var1);
            if (var6 != null) {
               if (var2) {
                  return var6;
               }

               if (var3 != null && !var6.getFull().equals(var3.getFull())) {
                  logger.warn("> " + S.L("conflicting results from unmangling engines (%s , %s)"), var3.getFull(), var6.getFull());
                  return null;
               }

               var3 = var6;
            }
         }

         return var3;
      }
   }
}
