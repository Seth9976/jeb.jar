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
public abstract class axh extends axg implements INativeDataItem {
   public static final String q = "dataHints";
   @SerId(1)
   private final long Dw;
   @SerId(2)
   protected long RF;
   @SerId(3)
   private NumberFormatter Uv;
   @SerId(4)
   private axm zz;

   protected axh(long var1, long var3) {
      super(0);
      if (var3 < 0L) {
         throw new IllegalArgumentException(Strings.f("Illegal data @%xh with size %d", var1, var3));
      } else {
         this.Dw = var1;
         this.RF = var3;
      }
   }

   public void q(String var1) {
      this.gP = var1;
   }

   public abstract bbd Uv();

   @Override
   protected void RF() {
      super.RF();
      if (this.Uv() != null) {
         this.Uv().removeListener(this);
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() == this.Uv()) {
         if (var1.getType().isDeepChange()) {
            this.PV();
         } else {
            this.q(NativeItemEventType.UPDATED);
         }
      }
   }

   @Override
   public long getMemoryAddress() {
      return this.Dw;
   }

   @Override
   public long getMemorySize() {
      return this.RF;
   }

   public int oW() {
      return 0;
   }

   public axm gO() {
      return this.zz;
   }

   public NumberFormatter nf() {
      return this.q(true);
   }

   public synchronized NumberFormatter q(boolean var1) {
      if (this.Uv == null && var1) {
         this.Uv = new NumberFormatter();
      }

      return this.Uv;
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
