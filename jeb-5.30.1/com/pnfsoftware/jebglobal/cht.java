package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import java.util.concurrent.atomic.AtomicBoolean;

public class cht extends AbstractDOptimizer {
   private AtomicBoolean q = new AtomicBoolean();

   @Override
   public int perform() {
      if (this.q.getAndSet(true)) {
         return 0;
      } else if (!bto.q(this.ctx, false)) {
         return 0;
      } else {
         this.resetLocalFields();
         return 1;
      }
   }
}
