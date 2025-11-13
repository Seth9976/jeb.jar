package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.List;

@Ser
public abstract class bml extends bmj implements IJavaStatement {
   @SerId(1)
   int Dw = -1;
   @SerTransient
   String Uv;

   @Override
   public void setIntermediateOffset(int var1) {
      this.Dw = var1;
   }

   @Override
   public int getIntermediateOffset() {
      return this.Dw;
   }

   public static boolean q(List var0, int var1, IJavaStatement var2) {
      for (int var3 = 0; var3 < var0.size(); var3++) {
         IJavaStatement var4 = (IJavaStatement)var0.get(var3);
         if (var4.getIntermediateOffset() == var1) {
            if (var0.get(var3) != var2) {
               var0.add(var3, var2);
            }

            return true;
         }

         if (var4 instanceof bmi && ((bmi)var4).insertAt(var1, var2)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public List getSubElements() {
      throw new RuntimeException("Unexpected: missing implementation: class " + this.getClass().getSimpleName());
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      throw new RuntimeException("Unexpected: missing implementation: class " + this.getClass().getSimpleName());
   }

   protected void q(bml var1) {
      super.q(var1);
      var1.Dw = this.Dw;
      var1.Uv = this.Uv;
   }

   @Override
   public void xK() {
   }

   private static void RF(bml var0) {
   }
}
