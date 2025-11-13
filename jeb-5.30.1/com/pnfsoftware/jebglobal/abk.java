package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceLocation;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class abk extends ads implements IReference {
   private static final String q = "attrRefType";
   private static final String Uv = "attrRefFlags";

   public abk(ReferenceLocation var1, ReferenceLocation var2) {
      super(var1, var2);
   }

   public abk(ReferenceLocation var1, ReferenceLocation var2, ReferenceType var3) {
      super(var1, var2);
      this.q(var3);
   }

   public abk(ReferenceLocation var1, ReferenceLocation var2, ReferenceType var3, int var4) {
      super(var1, var2);
      this.q(var3);
      this.q(var4);
   }

   public void q(ReferenceType var1) {
      this.q("attrRefType", var1);
   }

   @Override
   public ReferenceType getType() {
      ReferenceType var1 = (ReferenceType)this.RF("attrRefType");
      return var1 == null ? ReferenceType.UNKNOWN : var1;
   }

   @Override
   public String getStringType() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.getType().getStrCode());
      int var2 = this.getFlags();
      if ((var2 & 1) != 0) {
         var1.append("-dyn");
      }

      if ((var2 & 2) != 0) {
         var1.append("-adv");
      }

      return var1.toString();
   }

   public void q(int var1) {
      this.q("attrRefFlags", var1);
   }

   @Override
   public int getFlags() {
      Integer var1 = (Integer)this.RF("attrRefFlags");
      return var1 == null ? 0 : var1;
   }

   @Override
   public ReferenceLocation getFrom() {
      return (ReferenceLocation)this.RF;
   }

   @Override
   public ReferenceLocation getTo() {
      return (ReferenceLocation)this.xK;
   }
}
