package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCompound;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.List;

@SerDisabled
public abstract class aid extends afh {
   ICStatement oW;
   aid.eo gO;
   aid.CU nf;

   public aid(aid.eo var1, aid.CU var2) {
      this.gO = var1;
      this.nf = var2;
   }

   public aid.eo Dw() {
      return this.gO;
   }

   public aid.CU Uv() {
      return this.nf;
   }

   public static int q(List var0, int var1) {
      ICStatement var2 = (ICStatement)var0.get(var1);
      if (!(var2 instanceof aid var3)) {
         throw new RuntimeException();
      } else if (var3.nf != aid.CU.q && var3.nf != aid.CU.RF) {
         throw new RuntimeException();
      } else {
         int var4 = 1;

         for (int var5 = var1 + 1; var5 < var0.size(); var5++) {
            ICStatement var6 = (ICStatement)var0.get(var5);
            if (var6 instanceof aid var7) {
               if (var7.gO == var3.gO) {
                  if (var7.nf == aid.CU.q) {
                     var4++;
                  } else if (var7.nf == aid.CU.xK) {
                     if (--var4 == 0) {
                        return var5;
                     }
                  }
               }
            } else if (var6 instanceof ICCompound) {
               throw new RuntimeException();
            }
         }

         throw new RuntimeException();
      }
   }

   @Override
   public void generate(COutputSink var1) {
      throw new RuntimeException();
   }

   @Override
   public String toString() {
      return "PseudoStatement [originalStatement=" + this.oW + ", type=" + this.gO + ", where=" + this.nf + "]";
   }

   @Override
   public ICStatement duplicate() {
      throw new RuntimeException("deep duplication not implemented for pseudo-statements");
   }

   @Override
   public CElementType getElementType() {
      return null;
   }

   public static enum CU {
      q,
      RF,
      xK;
   }

   public static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW;
   }
}
