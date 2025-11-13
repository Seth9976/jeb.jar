package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RegisterLayoutBridge {
   private static final ILogger logger = GlobalLog.getLogger(RegisterLayoutBridge.class);
   private IRegisterBank srcLayout;
   private IRegisterBank dstLayout;
   private Map srcToDst = new HashMap();
   private Map dstToSrc = new HashMap();

   public RegisterLayoutBridge(IRegisterBank var1, IRegisterBank var2) {
      this.srcLayout = var1;
      this.dstLayout = var2;

      for (RegisterDescriptionEntry var4 : var1.getDescriptionEntries()) {
         RegisterDescriptionEntry var5 = var2.getDescriptionEntryByName(var4.getNames());
         if (var5 == null) {
            logger.debug("Register not found: %s", var4);
         } else {
            if (var5.isRegisterSlice()) {
               if (var5.getBitstart() != 0) {
                  logger.debug("Forbidding register partial match: %s <> %s (full: %s)", var4, var5, var5.getContainer());
                  continue;
               }

               logger.debug("Register partial match: %s <> %s (full: %s)", var4, var5, var5.getContainer());
            }

            if (!this.srcToDst.containsKey(var4.getNumber()) && !this.dstToSrc.containsKey(var5.getNumber())) {
               this.srcToDst.put(var4.getNumber(), var5.getNumber());
               this.dstToSrc.put(var5.getNumber(), var4.getNumber());
            } else {
               logger.debug("Register already registered: %s <> %s", var4, var5);
            }
         }
      }
   }

   public Integer convertSource(int var1) {
      return (Integer)this.srcToDst.get(var1);
   }

   public Integer convertDestination(int var1) {
      return (Integer)this.dstToSrc.get(var1);
   }

   public byte[] convertSourceValue(int var1, int var2, byte[] var3) {
      int var4 = this.srcLayout.getDescriptionEntry(var1).getSize();
      Assert.a(var4 == var3.length);
      int var5 = this.dstLayout.getDescriptionEntry(var2).getSize();
      if (var4 == var5) {
         return var3;
      } else if (var4 < var5) {
         byte[] var6 = new byte[var5];
         System.arraycopy(var3, 0, var6, 0, var4);
         return var6;
      } else {
         return Arrays.copyOf(var3, var5);
      }
   }

   public byte[] convertDestinationValue(int var1, int var2, byte[] var3) {
      int var4 = this.dstLayout.getDescriptionEntry(var1).getSize();
      Assert.a(var4 == var3.length);
      int var5 = this.srcLayout.getDescriptionEntry(var2).getSize();
      if (var4 == var5) {
         return var3;
      } else if (var4 < var5) {
         byte[] var6 = new byte[var5];
         System.arraycopy(var3, 0, var6, 0, var4);
         return var6;
      } else {
         return Arrays.copyOf(var3, var5);
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();

      for (int var3 : this.srcToDst.keySet()) {
         if (var1.length() > 0) {
            var1.append(", ");
         }

         int var4 = (Integer)this.srcToDst.get(var3);
         Strings.ff(var1, "%d>%d", var3, var4);
      }

      return var1.toString();
   }
}
