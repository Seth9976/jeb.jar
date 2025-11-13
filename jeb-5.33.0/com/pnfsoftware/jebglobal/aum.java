package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.DataHints;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public abstract class aum extends aul implements INativeDataItem {
   @SerId(1)
   private final long kS;
   @SerId(2)
   protected long pC;
   @SerId(3)
   private NumberFormatter wS;
   @SerId(4)
   private aur UT;

   protected aum(long var1, long var3) {
      super(0);
      if (var3 < 0L) {
         throw new IllegalArgumentException(Strings.f("Illegal data @%xh with size %d", var1, var3));
      } else {
         this.kS = var1;
         this.pC = var3;
      }
   }

   public void pC(String var1) {
      this.E = var1;
   }

   public abstract aye UT();

   @Override
   protected void A() {
      super.A();
      if (this.UT() != null) {
         this.UT().removeListener(this);
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() == this.UT()) {
         if (var1.getType().isDeepChange()) {
            this.xC();
         } else {
            this.pC(NativeItemEventType.UPDATED);
         }
      }
   }

   @Override
   public long getMemoryAddress() {
      return this.kS;
   }

   @Override
   public long getMemorySize() {
      return this.pC;
   }

   public int E() {
      return 0;
   }

   public aur sY() {
      return this.UT;
   }

   public NumberFormatter ys() {
      return this.pC(true);
   }

   public synchronized NumberFormatter pC(boolean var1) {
      if (this.wS == null && var1) {
         this.wS = new NumberFormatter();
      }

      return this.wS;
   }

   @Override
   public DataHints getHints(boolean var1) {
      DataHints var2 = (DataHints)this.getAttribute("dataHints", DataHints.class);
      if (var2 == null) {
         if (!var1) {
            return null;
         }

         var2 = new DataHints();
         this.setAttribute("dataHints", var2);
      }

      return var2;
   }

   @Override
   public List getChildren() {
      return null;
   }
}
