package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class ayb extends axj implements axy {
   private bbv q;
   private bbu RF;

   public ayb(long var1, IStructureType var3, IStructureTypeField var4) {
      super(0, null);
      this.Uv(3);
      this.q(var1);
      this.q = (bbv)var3;
      this.RF = (bbu)var4;
   }

   public IStructureType xK() {
      return this.q;
   }

   public IStructureTypeField Dw() {
      return this.RF;
   }

   @Override
   public String getName(boolean var1) {
      return this.RF.getName();
   }

   @Override
   public void setName(String var1) {
      this.q.q(this.RF, var1);
   }

   @Override
   public String toString() {
      return Strings.ff("PseudoField(\"%s.%s\")", this.q.getName(true), this.RF.getName());
   }
}
