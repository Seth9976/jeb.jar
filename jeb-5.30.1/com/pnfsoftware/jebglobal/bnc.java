package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.lang.invoke.StringConcatFactory;
import java.util.List;

@Ser
public class bnc extends bmk implements IJavaIdentifier {
   @SerId(1)
   private String Uv;
   @SerId(2)
   private int oW;
   @SerId(3)
   private String gO;
   @SerId(4)
   private long nf;
   @SerId(5)
   private String gP;
   @SerId(6)
   private IJavaType za;
   @SerId(7)
   bmx Dw;

   bnc(IJavaType var1, String var2, String var3, int var4) {
      if (var1 == null) {
         throw new IllegalArgumentException("An identifier must have a type");
      } else if (var2 == null) {
         throw new IllegalArgumentException("An identifier must have a name");
      } else {
         this.za = var1;
         this.Uv = var2;
         this.gP = var3;
         this.oW = var4;
      }
   }

   public bmx xK() {
      return this.Dw;
   }

   @Override
   public IJavaType getType() {
      return this.za;
   }

   @Override
   public String getName() {
      return this.Uv;
   }

   @Override
   public String getDebugName() {
      return this.gP;
   }

   @Override
   public String getGeneratedType(JavaOutputSink var1) {
      String var2 = this.za.getSignature();
      if (var2 != null) {
         IDexType var3 = var1.getDynamicContentManager().getDexType(var2);
         if (var3 != null) {
            return var3.getSignature(true, false, true);
         }
      }

      return var2;
   }

   @Override
   public String getGeneratedName(JavaOutputSink var1) {
      if (var1.getDynamicContentManager() != null && this.Dw != null) {
         String var2 = var1.getDynamicContentManager().getIdentifierName(this.Dw.getCoordinates());
         if (var2 != null) {
            return var2;
         }
      }

      if (this.Dw == null && this.gO != null) {
         return this.gO;
      } else {
         return var1.getUseDebugInfoNames() && this.gP != null ? this.gP : this.Uv;
      }
   }

   public long xK(JavaOutputSink var1) {
      if (var1.getDynamicContentManager() != null && this.Dw != null) {
         return var1.getDynamicContentManager().getIdentifierId(this.Dw.getCoordinates());
      } else {
         return this.Dw == null && this.nf != 0L ? this.nf : 0L;
      }
   }

   public int Dw() {
      return this.oW;
   }

   @Override
   public List getSubElements() {
      return blo.q();
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      return false;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.generate(var1, false);
   }

   @Override
   public void generate(JavaOutputSink var1, boolean var2) {
      IJavaExpression var3 = var1.getReplacementFor(this);
      if (var3 != null && var3 != this && var1.requestGeneratingReplacement()) {
         var3.generate(var1);
         var1.doneGeneratingReplacement();
      } else {
         this.q(var1);
         if (this.Uv.equals("this")) {
            var1.appendKeyword(JavaKeyword.THIS);
         } else {
            long var4 = this.xK(var1);
            if (var4 != 0L) {
               ICodeCoordinates var6 = var1.getCurrentMostPreciseCodeCoordinates();
               var1.recordIdentifierCoordinates(var4, var6);
            }

            var1.appendAndRecord(this.getGeneratedName(var1), ItemClassIdentifiers.IDENTIFIER, var4, var2 ? 1 : 0);
         }

         this.RF(var1);
      }
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Identifier;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.Uv == null ? 0 : this.Uv.hashCode());
      return 31 * var1 + (this.za == null ? 0 : this.za.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1
         ? true
         : var1 instanceof bnc var2 && "this".equals(this.getName()) && this.getName().equals(var2.getName()) && this.getType() == var2.getType();
   }

   @Override
   public String toString() {
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.Uv);
   }

   public bnc Uv() {
      return this;
   }
}
