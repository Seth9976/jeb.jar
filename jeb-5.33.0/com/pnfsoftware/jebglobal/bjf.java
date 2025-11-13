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
public class bjf extends bin implements IJavaIdentifier {
   @SerId(1)
   private String kS;
   @SerId(2)
   private int wS;
   @SerId(3)
   private String UT;
   @SerId(4)
   private long E;
   @SerId(5)
   private String sY;
   @SerId(6)
   private IJavaType ys;
   @SerId(7)
   bja A;

   bjf(IJavaType var1, String var2, String var3, int var4) {
      if (var1 == null) {
         throw new IllegalArgumentException("An identifier must have a type");
      } else if (var2 == null) {
         throw new IllegalArgumentException("An identifier must have a name");
      } else {
         this.ys = var1;
         this.kS = var2;
         this.sY = var3;
         this.wS = var4;
      }
   }

   public bja A() {
      return this.A;
   }

   @Override
   public IJavaType getType() {
      return this.ys;
   }

   @Override
   public String getName() {
      return this.kS;
   }

   @Override
   public String getDebugName() {
      return this.sY;
   }

   @Override
   public String getGeneratedType(JavaOutputSink var1) {
      String var2 = this.ys.getSignature();
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
      if (var1.getDynamicContentManager() != null && this.A != null) {
         String var2 = var1.getDynamicContentManager().getIdentifierName(this.A.getCoordinates());
         if (var2 != null) {
            return var2;
         }
      }

      if (this.A == null && this.UT != null) {
         return this.UT;
      } else {
         return var1.getUseDebugInfoNames() && this.sY != null ? this.sY : this.kS;
      }
   }

   public long kS(JavaOutputSink var1) {
      if (var1.getDynamicContentManager() != null && this.A != null) {
         return var1.getDynamicContentManager().getIdentifierId(this.A.getCoordinates());
      } else {
         return this.A == null && this.E != 0L ? this.E : 0L;
      }
   }

   public int kS() {
      return this.wS;
   }

   @Override
   public List getSubElements() {
      return bhr.pC();
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
         this.pC(var1);
         if (this.kS.equals("this")) {
            var1.appendKeyword(JavaKeyword.THIS);
         } else {
            long var4 = this.kS(var1);
            if (var4 != 0L) {
               ICodeCoordinates var6 = var1.getCurrentMostPreciseCodeCoordinates();
               var1.recordIdentifierCoordinates(var4, var6);
            }

            var1.appendAndRecord(this.getGeneratedName(var1), ItemClassIdentifiers.IDENTIFIER, var4, var2 ? 1 : 0);
         }

         this.A(var1);
      }
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Identifier;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      return 31 * var1 + (this.ys == null ? 0 : this.ys.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1
         ? true
         : var1 instanceof bjf var2 && "this".equals(this.getName()) && this.getName().equals(var2.getName()) && this.getType() == var2.getType();
   }

   @Override
   public String toString() {
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.kS);
   }

   public bjf wS() {
      return this;
   }
}
