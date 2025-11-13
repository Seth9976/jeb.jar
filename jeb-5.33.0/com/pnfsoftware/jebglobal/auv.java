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
public class auv extends aum {
   @SerId(1)
   private aye kS;
   @SerId(2)
   private ayg wS;
   @SerId(3)
   private List UT;

   auv(long var1, long var3, aye var5) {
      super(var1, var3);
      if (var5 == null) {
         throw new NullPointerException();
      } else {
         this.kS = var5;
         this.kS.addListener(this);
         this.wS = (ayg)TypeUtil.getNonAlias(var5, ayg.class);
         this.UT = new ArrayList();
      }
   }

   public List UO() {
      return this.UT;
   }

   @Override
   public List getChildren() {
      return this.UT;
   }

   void pC(INativeDataItem var1) {
      this.UT.add(var1);
   }

   void Ab() {
      this.UT = new CopyOnWriteArrayList(this.UT);
   }

   @Override
   public aye UT() {
      return this.kS;
   }

   public int rl() {
      return this.wS.getElementCount();
   }

   public aye z() {
      return this.wS.E();
   }

   @Override
   protected void A() {
      super.A();

      for (INativeDataItem var2 : this.UT) {
         var2.dispose(true);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("ArrayItem@%X(type:%s)", this.getMemoryAddress(), this.UT());
   }
}
