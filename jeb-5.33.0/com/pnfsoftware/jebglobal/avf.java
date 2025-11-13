package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class avf extends auo {
   private ayv pC;
   private ayu A;

   public avf(long var1, IStructureType var3, IStructureTypeField var4) {
      super(0, null);
      this.UT(3);
      this.pC(var1);
      this.pC = (ayv)var3;
      this.A = (ayu)var4;
   }

   @Override
   public String getName(boolean var1) {
      return this.A.getName();
   }

   @Override
   public void setName(String var1) {
      this.pC.pC(this.A, var1);
   }

   @Override
   public String toString() {
      return Strings.ff("PseudoField(\"%s.%s\")", this.pC.getName(true), this.A.getName());
   }
}
