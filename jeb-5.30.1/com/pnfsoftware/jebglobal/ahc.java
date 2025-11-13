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
public class ahc extends afg implements ICType {
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 8;
   public static final int Uv = 16;
   @SerId(1)
   private String oW;
   @SerId(2)
   private String gO;
   @SerId(3)
   private int nf;

   ahc(String var1, String var2, int var3) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.oW = var1;
         this.gO = var2;
         this.nf = var3;
      }
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Type;
   }

   public ahc RF() {
      return this;
   }

   @Override
   public String getSignature() {
      return this.oW;
   }

   @Override
   public String getBaseTypeSignature() {
      return this.gO;
   }

   @Override
   public boolean isVoid() {
      return "void".equals(this.oW);
   }

   @Override
   public boolean isAlias() {
      return (this.nf & 2) != 0;
   }

   @Override
   public boolean isFunctionPointer() {
      return (this.nf & 8) != 0;
   }

   @Override
   public boolean isReference() {
      return (this.nf & 4) != 0;
   }

   @Override
   public boolean isSynthetic() {
      return (this.nf & 16) != 0;
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
      String var2 = null;
      long var3 = 0L;
      IDynamicContentManager var5 = var1.getDynamicContentManager();
      if (var5 != null) {
         var2 = var5.getTypeSignature(this.oW);
         var3 = var5.getTypeItemId(this.gO != null ? this.gO : this.oW);
      }

      if (var2 == null) {
         var2 = this.oW;
      }

      var1.appendAndRecord(var2, ItemClassIdentifiers.CLASS_NAME, var3, 0);
      this.RF(var1);
   }

   @Override
   public List getSubElements() {
      return ahf.q();
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      return false;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.oW == null ? 0 : this.oW.hashCode());
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
         ahc var2 = (ahc)var1;
         if (this.oW == null) {
            if (var2.oW != null) {
               return false;
            }
         } else if (!this.oW.equals(var2.oW)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.oW);
   }
}
