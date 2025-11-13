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
public class axq extends axh {
   @SerId(1)
   private bbd Dw;
   @SerId(2)
   private bbf Uv;
   @SerId(3)
   private List zz;

   axq(long var1, long var3, bbd var5) {
      super(var1, var3);
      if (var5 == null) {
         throw new NullPointerException();
      } else {
         this.Dw = var5;
         this.Dw.addListener(this);
         this.Uv = (bbf)TypeUtil.getNonAlias(var5, bbf.class);
         this.zz = new ArrayList();
      }
   }

   public List cC() {
      return this.zz;
   }

   @Override
   public List getChildren() {
      return this.zz;
   }

   void q(INativeDataItem var1) {
      this.zz.add(var1);
   }

   void sH() {
      this.zz = new CopyOnWriteArrayList(this.zz);
   }

   @Override
   public bbd Uv() {
      return this.Dw;
   }

   public int CE() {
      return this.Uv.getElementCount();
   }

   public bbd wF() {
      return this.Uv.oW();
   }

   @Override
   protected void RF() {
      super.RF();

      for (INativeDataItem var2 : this.zz) {
         var2.dispose(true);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("ArrayItem@%X(type:%s)", this.getMemoryAddress(), this.Uv());
   }
}
