package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.java.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bnp extends bml implements IJavaLabel {
   @SerId(1)
   private int oW;
   @SerId(2)
   private String gO;

   bnp(int var1, String var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Label name cannot be null");
      } else {
         this.oW = var1;
         this.gO = var2;
      }
   }

   @Override
   public int getOffset() {
      return this.oW;
   }

   @Override
   public String getName() {
      return this.gO;
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
   public void generate(JavaOutputSink var1, boolean var2) {
      this.q(var1);
      String var3 = this.gO;
      long var4 = 0L;
      IDynamicContentManager var6 = var1.getDynamicContentManager();
      if (var6 != null) {
         var3 = var6.getLabelName(var1.getCurrentCoordinates());
         var4 = var6.getLabelId(var1.getCurrentCoordinates());
         if (var3 == null) {
            var3 = this.gO;
         }
      }

      if (var2) {
         var3 = var3 + ":";
      }

      var1.appendAndRecord(var3, ItemClassIdentifiers.LABEL, var4, var2 ? 1 : 0);
      this.RF(var1);
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.generate(var1, true);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Label;
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return var1 == this;
   }

   @Override
   public String toString() {
      return this.getPhysicalOffset() >= 0 ? Strings.ff("%s: // 0x%X", this.getName(), this.getPhysicalOffset()) : this.getName() + ":";
   }

   public bnp Dw() {
      return this;
   }
}
