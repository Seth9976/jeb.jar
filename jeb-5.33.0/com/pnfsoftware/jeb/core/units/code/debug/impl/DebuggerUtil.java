package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerUnit;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.corei.debuggers.android.vm.Kr;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.rG;
import com.pnfsoftware.jebglobal.un;
import com.pnfsoftware.jebglobal.wX;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DebuggerUtil {
   private static final ILogger logger = GlobalLog.getLogger(DebuggerUtil.class);

   public static byte[] readMemoryStringSafe(IDebuggerUnit var0, long var1, int var3) {
      return readMemorySafe(var0, var1, var3);
   }

   public static byte[] readMemorySafe(IDebuggerUnit var0, long var1, int var3) {
      byte[] var4 = new byte[var3];

      try {
         int var5 = readMemory(var0, var1, var3, var4);
         if (var5 < 0) {
            logger.debug("Unable to read memory at %Xh (%d)", var1, var5);
            return null;
         } else {
            return var5 < var3 ? Arrays.copyOf(var4, var5) : var4;
         }
      } catch (Exception var6) {
         logger.catchingSilent(var6);
         return null;
      }
   }

   private static int readMemory(IDebuggerUnit var0, long var1, int var3, byte[] var4) {
      return var0.readMemory(var1, var3, var4, 0);
   }

   public static ICodeUnit getPotentialDebuggee(IDebuggerUnit var0, long var1) {
      List var3 = var0.getPotentialDebuggees();
      if (var3 != null && !var3.isEmpty()) {
         if (var3.size() == 1) {
            return (ICodeUnit)var3.get(0);
         } else {
            for (ICodeUnit var5 : var0.getPotentialDebuggees()) {
               if (var5 instanceof INativeCodeUnit var6) {
                  long var7 = var6.getVirtualImageBase() + var6.getPhysicalImageDelta();
                  long var9 = var7 + var6.getImageSize();
                  if (var7 <= var1 && var1 < var9) {
                     return var5;
                  }
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   public static boolean isNativeCodeDebugger(IDebuggerUnit var0) {
      for (ICodeUnit var2 : var0.getPotentialDebuggees()) {
         if (var2 instanceof INativeCodeUnit) {
            return true;
         }
      }

      return false;
   }

   public static boolean valueEquals(IDebuggerVariable var0, IDebuggerVariable var1) {
      if (var0 != var1 && !var0.equals(var1)) {
         try {
            if (var0 instanceof Kr && var1 instanceof Kr) {
               rG var5 = ((Kr)var0).pC();
               rG var6 = ((Kr)var1).pC();
               if (var5 != null && var6 != null) {
                  return var5.A == var6.A && isCompatibleJDWPTag(var5.pC, var6.pC);
               }

               return false;
            }
         } catch (IOException | wX var4) {
            return false;
         }

         ITypedValue var2 = var0.getTypedValue();
         ITypedValue var3 = var1.getTypedValue();
         return var2 != null && var2.getTypeName() != null && var3 != null && var3.getTypeName() != null && var2.getTypeName().equals(var3.getTypeName())
            ? var0.getTypedValue().getValue().equals(var1.getTypedValue().getValue())
            : false;
      } else {
         return true;
      }
   }

   private static boolean isCompatibleJDWPTag(byte var0, byte var1) {
      return var0 == var1 ? true : var0 == 76 && un.kS(var1) || var1 == 76 && un.kS(var0);
   }
}
