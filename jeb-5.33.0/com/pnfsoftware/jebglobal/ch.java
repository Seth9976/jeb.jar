package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstructionManager;
import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.HashMap;
import java.util.Map;

@SerDisabled
public abstract class ch extends AbstractInstructionManager {
   private Map pC = new HashMap();
   private Map A = new HashMap();
   private final int kS;

   public ch(int var1) {
      this.kS = var1;
   }

   protected jR pC(BytesBlock var1, tz var2) throws ProcessorException {
      return this.pC(var1, var2, null);
   }

   protected jR pC(BytesBlock var1, tz var2, zj var3) throws ProcessorException {
      byte[] var4 = var1.getBECode();

      var3 = switch (this.kS) {
         case 16 -> {
         }
         case 32 -> zj.pC((var4[0] & 240) >>> 4);
         case 64 -> var2.E(var4);
         default -> throw new IllegalArgumentException("Unexpected mode: " + this.kS);
      };
      if (var2.sY() != null) {
         for (NH var6 : var2.sY()) {
            if (var6.pC(var4)) {
               return this.pC(var1, var6.pC(), var3);
            }
         }
      }

      return new jR(var1, this.pC(var2, var4, var3), this.pC(var2, var4, this.kS), var2.UT(), var3, this.kS, var2.pC(var4), var2.wS(var4));
   }

   private NC pC(tz var1, byte[] var2, zj var3) {
      NC var4 = var1.pC(var2, var3);
      return this.pC(var4);
   }

   private NC pC(NC var1) {
      if (this.A == null) {
         return var1;
      } else {
         NC var2 = (NC)this.A.get(var1);
         if (var2 == null) {
            this.A.put(var1, var1);
         } else {
            var1 = var2;
         }

         return var1;
      }
   }

   private Yg[] pC(tz var1, byte[] var2, int var3) throws ProcessorException {
      Yg[] var4 = var1.pC(var2, var3);

      for (int var5 = 0; var5 < var4.length; var5++) {
         Yg var6 = var4[var5];
         if (var6 != null) {
            var4[var5] = this.pC(var6);
         }
      }

      return var4;
   }

   private Yg pC(Yg var1) {
      if (this.pC == null) {
         return var1;
      } else {
         Yg var2 = (Yg)this.pC.get(var1);
         if (var2 == null) {
            this.pC.put(var1, var1);
         } else {
            var1 = var2;
         }

         return var1;
      }
   }
}
