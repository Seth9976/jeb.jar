package com.pnfsoftware.jeb.core.units.code.asm.mangling;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.axp;
import com.pnfsoftware.jebglobal.ayl;
import com.pnfsoftware.jebglobal.ayn;
import com.pnfsoftware.jebglobal.ayo;
import com.pnfsoftware.jebglobal.ayq;
import com.pnfsoftware.jebglobal.ayr;
import com.pnfsoftware.jebglobal.azh;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UnmanglerService {
   private static final ILogger logger = GlobalLog.getLogger(UnmanglerService.class);
   public static final int MANGLING_ENGINE_MSVC = 1;
   public static final int MANGLING_ENGINE_MSVCPP = 2;
   public static final int MANGLING_ENGINE_MSVCPP_V2 = 3;
   public static final int MANGLING_ENGINE_CXXA = 4;
   private List engines = new CopyOnWriteArrayList();
   private INativeCodeUnit codeUnit;

   public UnmanglerService(INativeCodeUnit var1) {
      this.codeUnit = var1;
   }

   public void registerEngine(int var1) {
      if (var1 == 1) {
         this.engines.add(new ayl());
      } else {
         if (var1 == 2) {
            throw new IllegalArgumentException("Deprecated mangling engine id");
         }

         if (var1 == 3) {
            this.engines.add(new ayn());
         } else {
            if (var1 != 4) {
               throw new IllegalArgumentException("Unknown mangling engine id");
            }

            this.engines.add(new azh());
         }
      }
   }

   public boolean importUnmangledRoutineName(INativeMethodItem var1, String var2, IUnmangledRoutine var3, boolean var4) {
      if (var1 instanceof axp && var3 != null && var1.getName(true) != null) {
         String var5 = var3.getName();
         if (var5 != null) {
            ((axp)var1).q(var2);
            ((axp)var1).xK(var5);
            var1.setName(var5);
            String var6 = var3.getFull();
            if (var6 != null) {
               ((axp)var1).RF(var6);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean importUnmangledRoutinePrototype(INativeMethodItem var1, IUnmangledRoutine var2) {
      if (var1 instanceof axp && var2 != null) {
         String var3 = var2.getSignature(true, true);
         return var3 != null ? this.codeUnit.setRoutineSignature(var1, var3, true) : false;
      } else {
         return false;
      }
   }

   public IUnmangledRoutine unmangleRoutine(String var1, boolean var2) {
      if (this.engines.isEmpty()) {
         return null;
      } else {
         Object var3 = null;

         for (IManglingEngine var5 : this.engines) {
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
               var3 = new ayr(ayo.q(var6.getFull()), var6.getFull());
            }
         }

         return (IUnmangledRoutine)var3;
      }
   }

   public IUnmangledData unmangleData(String var1, boolean var2) {
      if (this.engines.isEmpty()) {
         return null;
      } else {
         ayq var3 = null;

         for (IManglingEngine var5 : this.engines) {
            if (var5.unmangle(var1) instanceof ayq var7) {
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
      if (this.engines.isEmpty()) {
         return null;
      } else {
         IUnmangledData var3 = null;

         for (IManglingEngine var5 : this.engines) {
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
