package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceLocation;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class xy extends aca implements IReference, Comparable {
   public xy(ReferenceLocation var1, ReferenceLocation var2) {
      super(var1, var2);
   }

   public void pC(ReferenceType var1) {
      this.pC("attrRefType", var1);
   }

   @Override
   public ReferenceType getType() {
      ReferenceType var1 = (ReferenceType)this.pC("attrRefType");
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

   public void pC(int var1) {
      this.pC("attrRefFlags", var1);
   }

   @Override
   public int getFlags() {
      Integer var1 = (Integer)this.pC("attrRefFlags");
      return var1 == null ? 0 : var1;
   }

   @Override
   public ReferenceLocation getFrom() {
      return (ReferenceLocation)this.pC;
   }

   @Override
   public ReferenceLocation getTo() {
      return (ReferenceLocation)this.A;
   }

   public int pC(xy var1) {
      int var2;
      if (((ReferenceLocation)this.pC).isInternalAddress() && ((ReferenceLocation)var1.pC).isInternalAddress()) {
         var2 = ((ReferenceLocation)this.pC).getInternalAddress().compareTo(((ReferenceLocation)var1.pC).getInternalAddress());
      } else {
         if (((ReferenceLocation)this.pC).isInternalAddress() || ((ReferenceLocation)var1.pC).isInternalAddress()) {
            return ((ReferenceLocation)this.pC).isInternalAddress() ? -1 : 1;
         }

         var2 = ((ReferenceLocation)this.pC)
            .getExternalMethod()
            .getMemoryAddress()
            .compareTo(((ReferenceLocation)var1.pC).getExternalMethod().getMemoryAddress());
      }

      if (var2 != 0) {
         return var2;
      } else {
         if (((ReferenceLocation)this.A).isInternalAddress() && ((ReferenceLocation)var1.A).isInternalAddress()) {
            var2 = ((ReferenceLocation)this.A).getInternalAddress().compareTo(((ReferenceLocation)var1.A).getInternalAddress());
         } else {
            if (((ReferenceLocation)this.A).isInternalAddress() || ((ReferenceLocation)var1.A).isInternalAddress()) {
               return ((ReferenceLocation)this.A).isInternalAddress() ? -1 : 1;
            }

            var2 = ((ReferenceLocation)this.A)
               .getExternalMethod()
               .getMemoryAddress()
               .compareTo(((ReferenceLocation)var1.A).getExternalMethod().getMemoryAddress());
         }

         return var2;
      }
   }
}
