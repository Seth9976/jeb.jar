package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.List;

@SerDisabled
public abstract class bsq extends bml {
   IJavaStatement za;
   bsq.eo lm;
   bsq.CU zz;

   public bsq(bsq.eo var1, bsq.CU var2) {
      this.lm = var1;
      this.zz = var2;
   }

   public IJavaStatement nf() {
      return this.za;
   }

   public bsq.eo gP() {
      return this.lm;
   }

   public bsq.CU za() {
      return this.zz;
   }

   public static int q(List var0, int var1) {
      IJavaStatement var2 = (IJavaStatement)var0.get(var1);
      if (!(var2 instanceof bsq var3)) {
         throw new RuntimeException("Expected a pseudo statement");
      } else if (var3.zz != bsq.CU.xK && var3.zz != bsq.CU.RF) {
         throw new RuntimeException("Unexpected where: " + var3.zz);
      } else {
         int var4 = 0;

         for (int var5 = var1 - 1; var5 >= 0; var5--) {
            IJavaStatement var6 = (IJavaStatement)var0.get(var5);
            if (var6 instanceof bsq var7) {
               if (var7.lm == var3.lm) {
                  if (var7.zz == bsq.CU.xK) {
                     var4--;
                  } else if (var7.zz == bsq.CU.q) {
                     if (++var4 == 1) {
                        return var5;
                     }
                  }
               }
            } else if (var6 instanceof IJavaCompound) {
               throw new RuntimeException("Compound statements should not present in a flat list");
            }
         }

         throw new RuntimeException("Could not match a matching closing pseudo-statement");
      }
   }

   public static int RF(List var0, int var1) {
      IJavaStatement var2 = (IJavaStatement)var0.get(var1);
      if (!(var2 instanceof bsq var3)) {
         throw new RuntimeException("Expected a pseudo statement");
      } else if (var3.zz != bsq.CU.q && var3.zz != bsq.CU.RF) {
         throw new RuntimeException("Unexpected where: " + var3.zz);
      } else {
         int var4 = 1;

         for (int var5 = var1 + 1; var5 < var0.size(); var5++) {
            IJavaStatement var6 = (IJavaStatement)var0.get(var5);
            if (var6 instanceof bsq var7) {
               if (var7.lm == var3.lm) {
                  if (var7.zz == bsq.CU.q) {
                     var4++;
                  } else if (var7.zz == bsq.CU.xK) {
                     if (--var4 == 0) {
                        return var5;
                     }
                  }
               }
            } else if (var6 instanceof IJavaCompound) {
               throw new RuntimeException("Compound statements should not present in a flat list");
            }
         }

         throw new RuntimeException("Could not match a matching closing pseudo-statement");
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      throw new RuntimeException("Do not call: a pseudo-statement should not be generated");
   }

   @Override
   public JavaElementType getElementType() {
      return null;
   }

   @Override
   public String toString() {
      return this.getClass().getSimpleName() + "@" + this.hashCode();
   }

   public bsq lm() {
      throw new RuntimeException("Do not use");
   }

   protected void q(bsq var1) {
      super.q(var1);
      var1.za = this.za;
      var1.lm = this.lm;
      var1.zz = this.zz;
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
      oW,
      gO,
      nf,
      gP;
   }
}
