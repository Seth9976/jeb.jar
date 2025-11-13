package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.IMethodTable;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class avc implements IMethodTable {
   @SerId(1)
   private int pC;
   @SerId(2)
   private Long A;
   @SerId(3)
   private List kS = new ArrayList();

   public avc(long var1) {
      this.A = var1;
   }

   @Override
   public Long getAddress() {
      return this.A;
   }

   public void pC() {
      this.pC = 1;
   }

   public void pC(INativeMethodItem var1) {
      if (this.pC != 0) {
         throw new IllegalStateException("This concrete virtual table is locked");
      } else if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.kS.add((auu)var1);
      }
   }

   @Override
   public int size() {
      return this.kS.size();
   }

   public auu pC(int var1) {
      return (auu)this.kS.get(var1);
   }

   @Override
   public List getAll() {
      return Collections.unmodifiableList(this.kS);
   }

   @Override
   public int find(INativeMethodItem var1) {
      return this.kS.indexOf(var1);
   }

   public void A(int var1) {
      this.kS.set(var1, null);
   }
}
