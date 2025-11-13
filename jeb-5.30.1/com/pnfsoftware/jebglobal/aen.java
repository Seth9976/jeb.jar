package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class aen implements IEventListener {
   aen(aem var1) {
      this.q = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getSource() == this.q.Uv) {
         JebEvent var2 = (JebEvent)var1;
         if (var2.getType() == J.UnitChange) {
            ICElement var3 = this.q.getASTItem();
            Object var4 = null;
            if (var3 instanceof afn) {
               int var5 = ((afn)var3).getIndex();
               var4 = this.q.Uv.RF(var5);
            } else if (var3 instanceof agd) {
               int var6 = ((agd)var3).getIndex();
               var4 = this.q.Uv.q(var6);
            } else if (var3 instanceof agp) {
               int var7 = ((agp)var3).getIndex();
               var4 = this.q.Uv.Uv(var7);
            }

            if (var4 != null) {
               aem.q(this.q, ((INativeItem)var4).getName(true));
            }

            this.q.notifyListeners(var2);
         }
      }
   }
}
