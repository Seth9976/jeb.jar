package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.lang.invoke.StringConcatFactory;
import java.util.List;

@Ser
public class afj extends adn implements ICType {
   @SerId(1)
   private String pC;
   @SerId(2)
   private String A;
   @SerId(3)
   private int kS;

   afj(String var1, String var2, int var3) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Type;
   }

   public afj A() {
      return this;
   }

   @Override
   public String getSignature() {
      return this.pC;
   }

   @Override
   public String getBaseTypeSignature() {
      return this.A;
   }

   @Override
   public boolean isVoid() {
      return "void".equals(this.pC);
   }

   @Override
   public boolean isAlias() {
      return (this.kS & 2) != 0;
   }

   @Override
   public boolean isFunctionPointer() {
      return (this.kS & 8) != 0;
   }

   @Override
   public boolean isReference() {
      return (this.kS & 4) != 0;
   }

   @Override
   public boolean isSynthetic() {
      return (this.kS & 16) != 0;
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      String var2 = null;
      long var3 = 0L;
      IDynamicContentManager var5 = var1.getDynamicContentManager();
      if (var5 != null) {
         var2 = var5.getTypeSignature(this.pC);
         var3 = var5.getTypeItemId(this.A != null ? this.A : this.pC);
      }

      if (var2 == null) {
         var2 = this.pC;
      }

      var1.appendAndRecord(var2, ItemClassIdentifiers.CLASS_NAME, var3, 0);
      this.A(var1);
   }

   @Override
   public List getSubElements() {
      return afm.pC();
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      return false;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         afj var2 = (afj)var1;
         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.pC);
   }
}
