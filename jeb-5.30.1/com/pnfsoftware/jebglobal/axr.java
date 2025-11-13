package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class axr extends axh {
   @SerId(1)
   private bbd Dw;
   @SerId(2)
   private List Uv;

   axr(long var1, long var3, bbd var5) {
      super(var1, var3);
      if (var5 == null) {
         throw new NullPointerException();
      } else {
         this.Dw = var5;
         this.Dw.addListener(this);
         bbv var10000 = (bbv)TypeUtil.getNonAlias(var5, bbv.class);
         this.Uv = new ArrayList();
      }
   }

   @Override
   public bbd Uv() {
      return this.Dw;
   }

   public void q(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException(Strings.f("Illegal size %d for %s", var1, this));
      } else {
         this.RF = var1;
      }
   }

   public boolean cC() {
      return this.getMemorySize() != this.Dw.getSize();
   }

   public List sH() {
      return this.Uv;
   }

   @Override
   public List getChildren() {
      return this.Uv;
   }

   void q(INativeDataItem var1) {
      this.Uv.add(var1);
   }

   void CE() {
      this.Uv = new CopyOnWriteArrayList(this.Uv);
   }

   @Override
   protected void RF() {
      super.RF();

      for (INativeDataItem var2 : this.Uv) {
         var2.dispose(true);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("ComplexItem@%X(type:%s)", this.getMemoryAddress(), this.Uv());
   }
}
