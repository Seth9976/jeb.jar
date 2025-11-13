package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.dwarf.Dwarf;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIE;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIEAttribute;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class cdn implements IDIEAttribute {
   @SerId(1)
   private final String pC;
   @SerId(2)
   private final Long A;
   @SerId(3)
   private final Object kS;
   @SerId(4)
   private final String wS;
   @SerId(5)
   private final IDIE UT;

   public cdn(String var1, Long var2, Object var3, String var4, IDIE var5) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
   }

   @Override
   public String getName() {
      return this.pC;
   }

   @Override
   public Long getRawForm() {
      return this.A;
   }

   @Override
   public Object getForm() {
      return this.kS != null ? this.kS : this.A;
   }

   @Override
   public IDIE getReference() {
      return this.UT.getReference(this);
   }

   @Override
   public Dwarf.DwarfFormType getType() {
      return this.wS != null ? Dwarf.DwarfFormType.valueOf(this.wS) : null;
   }

   @Override
   public String toString() {
      return "DIEAttribute [" + this.getType() + " " + this.pC + "(" + this.getForm() + ")]";
   }
}
