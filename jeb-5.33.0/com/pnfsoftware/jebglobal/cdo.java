package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.tree.INode;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIEAttribute;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class cdo implements INode {
   @SerId(1)
   private IDIEAttribute pC;

   public cdo(IDIEAttribute var1) {
      this.pC = var1;
   }

   @Override
   public List getChildren() {
      return null;
   }

   @Override
   public String getLabel() {
      return "";
   }

   @Override
   public String[] getAdditionalLabels() {
      int var1 = (this.pC.getRawForm() != null ? 1 : 0) + (this.pC.getForm() != null ? 1 : 0);
      if (var1 == 2 && this.pC.getRawForm() == this.pC.getForm()) {
         var1 = 1;
      }

      String[] var2 = new String[1 + var1];
      var2[0] = this.pC.getName();
      int var3 = 1;
      if (this.pC.getForm() != null) {
         if (this.pC.getForm() instanceof Long) {
            var2[var3] = Long.toHexString((Long)this.pC.getForm()) + "h";
         } else {
            var2[var3] = this.pC.getForm().toString();
         }

         var3++;
         if (var1 == 1) {
            return var2;
         }
      }

      if (this.pC.getRawForm() != null) {
         var2[var3] = Long.toHexString(this.pC.getRawForm()) + "h";
      }

      return var2;
   }

   public IDIEAttribute pC() {
      return this.pC;
   }
}
