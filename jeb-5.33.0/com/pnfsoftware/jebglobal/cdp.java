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
public class cdp implements INode {
   @SerId(1)
   private IDIE pC;
   @SerTransient
   private List A;
   @SerTransient
   private String kS;

   public cdp(IDIE var1) {
      this.pC = var1;
   }

   @Override
   public List getChildren() {
      if (this.A == null) {
         this.A = new ArrayList();

         for (IDIEAttribute var2 : this.pC.getAttributes()) {
            this.A.add(new cdo(var2));
         }

         for (IDIE var4 : this.pC.getChildren()) {
            this.A.add(new cdp(var4));
         }
      }

      return this.A;
   }

   @Override
   public String getLabel() {
      return Long.toHexString(this.pC.getOffset()) + "h";
   }

   @Override
   public String[] getAdditionalLabels() {
      if (this.kS == null) {
         this.kS = this.pC.getAttributeName();
         if (this.kS == null) {
            this.kS = "";
         }
      }

      return new String[]{this.pC.getTagName(), this.kS};
   }

   public IDIE pC() {
      return this.pC;
   }
}
