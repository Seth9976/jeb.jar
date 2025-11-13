package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.dwarf.Dwarf;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIE;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.IDIEAttribute;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class civ implements IDIEAttribute {
   @SerId(1)
   private final String q;
   @SerId(2)
   private final Long RF;
   @SerId(3)
   private final Object xK;
   @SerId(4)
   private final String Dw;
   @SerId(5)
   private final IDIE Uv;

   public civ(String var1, Long var2, Object var3, String var4, IDIE var5) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = var5;
   }

   @Override
   public String getName() {
      return this.q;
   }

   @Override
   public Long getRawForm() {
      return this.RF;
   }

   @Override
   public Object getForm() {
      return this.xK != null ? this.xK : this.RF;
   }

   @Override
   public IDIE getReference() {
      return this.Uv.getReference(this);
   }

   @Override
   public Dwarf.DwarfFormType getType() {
      return this.Dw != null ? Dwarf.DwarfFormType.valueOf(this.Dw) : null;
   }

   @Override
   public String toString() {
      return "DIEAttribute [" + this.getType() + " " + this.q + "(" + this.getForm() + ")]";
   }
}
