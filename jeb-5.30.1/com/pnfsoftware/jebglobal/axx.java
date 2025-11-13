package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.IMethodTable;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class axx implements IMethodTable {
   @SerId(1)
   private int q;
   @SerId(2)
   private Long RF;
   @SerId(3)
   private List xK = new ArrayList();

   public axx(long var1) {
      this.RF = var1;
   }

   @Override
   public Long getAddress() {
      return this.RF;
   }

   public void q() {
      this.q = 1;
   }

   public void q(INativeMethodItem var1) {
      if (this.q != 0) {
         throw new IllegalStateException("This concrete virtual table is locked");
      } else if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.xK.add((axp)var1);
      }
   }

   @Override
   public int size() {
      return this.xK.size();
   }

   public axp q(int var1) {
      return (axp)this.xK.get(var1);
   }

   @Override
   public List getAll() {
      return Collections.unmodifiableList(this.xK);
   }

   @Override
   public int find(INativeMethodItem var1) {
      return this.xK.indexOf(var1);
   }

   public void RF(int var1) {
      this.xK.set(var1, null);
   }
}
