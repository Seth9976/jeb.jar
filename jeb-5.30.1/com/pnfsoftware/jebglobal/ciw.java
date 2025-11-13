package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.tree.INode;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIEAttribute;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class ciw implements INode {
   @SerId(1)
   private IDIEAttribute q;

   public ciw(IDIEAttribute var1) {
      this.q = var1;
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
      int var1 = (this.q.getRawForm() != null ? 1 : 0) + (this.q.getForm() != null ? 1 : 0);
      if (var1 == 2 && this.q.getRawForm() == this.q.getForm()) {
         var1 = 1;
      }

      String[] var2 = new String[1 + var1];
      var2[0] = this.q.getName();
      int var3 = 1;
      if (this.q.getForm() != null) {
         if (this.q.getForm() instanceof Long) {
            var2[var3] = Long.toHexString((Long)this.q.getForm()) + "h";
         } else {
            var2[var3] = this.q.getForm().toString();
         }

         var3++;
         if (var1 == 1) {
            return var2;
         }
      }

      if (this.q.getRawForm() != null) {
         var2[var3] = Long.toHexString(this.q.getRawForm()) + "h";
      }

      return var2;
   }

   public IDIEAttribute q() {
      return this.q;
   }
}
