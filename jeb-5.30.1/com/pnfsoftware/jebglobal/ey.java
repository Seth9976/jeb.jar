package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstructionManager;
import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.HashMap;
import java.util.Map;

@SerDisabled
public abstract class ey extends AbstractInstructionManager {
   private Map q = new HashMap();
   private Map RF = new HashMap();
   private final int xK;

   public ey(int var1) {
      this.xK = var1;
   }

   protected p q(BytesBlock var1, Je var2) throws ProcessorException {
      return this.q(var1, var2, null);
   }

   protected p q(BytesBlock var1, Je var2, mZ var3) throws ProcessorException {
      byte[] var4 = var1.getBECode();

      var3 = switch (this.xK) {
         case 16 -> {
         }
         case 32 -> mZ.q((var4[0] & 240) >>> 4);
         case 64 -> var2.oW(var4);
         default -> throw new IllegalArgumentException("Unexpected mode: " + this.xK);
      };
      if (var2.gO() != null) {
         for (ag var6 : var2.gO()) {
            if (var6.q(var4)) {
               return this.q(var1, var6.q(), var3);
            }
         }
      }

      return new p(var1, this.q(var2, var4, var3), this.q(var2, var4, this.xK), var2.Uv(), var3, this.xK, var2.q(var4), var2.Dw(var4));
   }

   private Op q(Je var1, byte[] var2, mZ var3) {
      Op var4 = var1.q(var2, var3);
      return this.q(var4);
   }

   private Op q(Op var1) {
      if (this.RF == null) {
         return var1;
      } else {
         Op var2 = (Op)this.RF.get(var1);
         if (var2 == null) {
            this.RF.put(var1, var1);
         } else {
            var1 = var2;
         }

         return var1;
      }
   }

   private CW[] q(Je var1, byte[] var2, int var3) throws ProcessorException {
      CW[] var4 = var1.q(var2, var3);

      for (int var5 = 0; var5 < var4.length; var5++) {
         CW var6 = var4[var5];
         if (var6 != null) {
            var4[var5] = this.q(var6);
         }
      }

      return var4;
   }

   private CW q(CW var1) {
      if (this.q == null) {
         return var1;
      } else {
         CW var2 = (CW)this.q.get(var1);
         if (var2 == null) {
            this.q.put(var1, var1);
         } else {
            var1 = var2;
         }

         return var1;
      }
   }
}
