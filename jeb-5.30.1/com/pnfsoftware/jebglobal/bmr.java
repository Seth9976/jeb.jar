package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bmr extends bml {
   @SerId(1)
   private bmx oW;
   @SerId(2)
   private bnc gO;

   public bmr(IJavaExpression var1) {
      if (var1 instanceof bmx) {
         this.oW = (bmx)var1;
      } else {
         if (!(var1 instanceof bnc)) {
            throw new RuntimeException();
         }

         this.gO = (bnc)var1;
      }
   }

   public bmx Dw() {
      return this.oW;
   }

   public IJavaIdentifier Uv() {
      return (IJavaIdentifier)(this.oW != null ? this.oW.getIdentifier() : this.gO);
   }

   @Override
   public void generate(JavaOutputSink var1) {
      throw new RuntimeException("Do not use Catch.generate()");
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Catch;
   }

   @Override
   public String toString() {
      return "catch(" + (this.oW != null ? this.oW : this.gO) + ")";
   }

   public bmr oW() {
      throw new RuntimeException("Do not use Catch.duplicate()");
   }
}
