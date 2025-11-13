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
public class auw extends aum {
   @SerId(1)
   private aye kS;
   @SerId(2)
   private List wS;

   auw(long var1, long var3, aye var5) {
      super(var1, var3);
      if (var5 == null) {
         throw new NullPointerException();
      } else {
         this.kS = var5;
         this.kS.addListener(this);
         ayv var10000 = (ayv)TypeUtil.getNonAlias(var5, ayv.class);
         this.wS = new ArrayList();
      }
   }

   @Override
   public aye UT() {
      return this.kS;
   }

   public void pC(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException(Strings.f("Illegal size %d for %s", var1, this));
      } else {
         this.pC = var1;
      }
   }

   public List UO() {
      return this.wS;
   }

   @Override
   public List getChildren() {
      return this.wS;
   }

   void pC(INativeDataItem var1) {
      this.wS.add(var1);
   }

   void Ab() {
      this.wS = new CopyOnWriteArrayList(this.wS);
   }

   @Override
   protected void A() {
      super.A();

      for (INativeDataItem var2 : this.wS) {
         var2.dispose(true);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("ComplexItem@%X(type:%s)", this.getMemoryAddress(), this.UT());
   }
}
