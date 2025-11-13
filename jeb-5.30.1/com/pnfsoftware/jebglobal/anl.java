package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class anl {
   @SerId(1)
   AddressableInstruction q;
   @SerId(2)
   INativeMethodItem RF;
   @SerId(3)
   List xK;

   public anl(AddressableInstruction var1, INativeMethodItem var2, List var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = (List)(var3 == null ? new ArrayList() : var3);
   }

   public IEStatement q() {
      return (IEStatement)this.q.getInstruction();
   }

   public long RF() {
      return this.q.getOffset();
   }

   public INativeMethodItem xK() {
      return this.RF;
   }

   public List Dw() {
      return this.xK;
   }

   @Override
   public String toString() {
      return Strings.ff("{%s}=>%s(dyn:%s)", this.q, this.RF, this.xK);
   }
}
