package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.format.Strings;

public class VirtualMethodCoordinates {
   IClassType classtype;
   int tableIndex;
   int methodIndex;

   public VirtualMethodCoordinates(IClassType var1, int var2, int var3) {
      this.classtype = var1;
      this.tableIndex = var2;
      this.methodIndex = var3;
   }

   public IClassType getClasstype() {
      return this.classtype;
   }

   public int getTableIndex() {
      return this.tableIndex;
   }

   public int getMethodIndex() {
      return this.methodIndex;
   }

   @Override
   public String toString() {
      return Strings.ff("%s:[%d,%d]", this.classtype, this.tableIndex, this.methodIndex);
   }
}
