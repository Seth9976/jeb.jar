package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.tree.INode;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIE;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIEAttribute;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.List;

@Ser
public class cix implements INode {
   @SerId(1)
   private IDIE q;
   @SerTransient
   private List RF;
   @SerTransient
   private String xK;

   public cix(IDIE var1) {
      this.q = var1;
   }

   @Override
   public List getChildren() {
      if (this.RF == null) {
         this.RF = new ArrayList();

         for (IDIEAttribute var2 : this.q.getAttributes()) {
            this.RF.add(new ciw(var2));
         }

         for (IDIE var4 : this.q.getChildren()) {
            this.RF.add(new cix(var4));
         }
      }

      return this.RF;
   }

   @Override
   public String getLabel() {
      return Long.toHexString(this.q.getOffset()) + "h";
   }

   @Override
   public String[] getAdditionalLabels() {
      if (this.xK == null) {
         this.xK = this.q.getAttributeName();
         if (this.xK == null) {
            this.xK = "";
         }
      }

      return new String[]{this.q.getTagName(), this.xK};
   }

   public IDIE q() {
      return this.q;
   }
}
