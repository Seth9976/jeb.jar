package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class biu extends bio {
   @SerId(1)
   private bja wS;
   @SerId(2)
   private bjf UT;

   public biu(IJavaExpression var1) {
      if (var1 instanceof bja) {
         this.wS = (bja)var1;
      } else {
         if (!(var1 instanceof bjf)) {
            throw new RuntimeException();
         }

         this.UT = (bjf)var1;
      }
   }

   public bja A() {
      return this.wS;
   }

   public IJavaIdentifier kS() {
      return (IJavaIdentifier)(this.wS != null ? this.wS.getIdentifier() : this.UT);
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
      return "catch(" + (this.wS != null ? this.wS : this.UT) + ")";
   }

   public biu wS() {
      throw new RuntimeException("Do not use Catch.duplicate()");
   }
}
