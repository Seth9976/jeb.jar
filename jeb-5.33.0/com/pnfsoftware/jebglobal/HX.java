package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterData;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerThreadStatus;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractDebuggerThread;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import java.util.ArrayList;
import java.util.List;

public class HX extends AbstractDebuggerThread {
   private static final ILogger pC = GlobalLog.getLogger(HX.class);
   private ia A;
   private LD kS = null;

   public HX(ia var1, int var2, String var3) {
      super(var2, var3);
      this.A = var1;
   }

   @Override
   public DebuggerThreadStatus getStatus() {
      return this.A.pC() ? DebuggerThreadStatus.PAUSED : DebuggerThreadStatus.UNKNOWN;
   }

   @Override
   public boolean resume() {
      pC.error("Cannot perform operation");
      return false;
   }

   @Override
   public boolean suspend() {
      pC.error("Cannot perform operation");
      return false;
   }

   public LD pC(boolean var1) {
      if (var1 || this.kS == null) {
         this.kS = this.A.A().kS((int)this.getId());
      }

      return this.kS;
   }

   public boolean pC(LD var1) {
      boolean var2 = this.A.A().pC((int)this.getId(), var1);
      this.kS = null;
      return var2;
   }

   @Override
   public boolean stepInto() {
      return Booleans.isTrue((Boolean)this.A.pC(new tc(this)));
   }

   @Override
   public boolean stepOver() {
      return Booleans.isTrue((Boolean)this.A.pC(new fq(this)));
   }

   @Override
   public boolean stepOut() {
      pC.error("Cannot perform operation");
      return false;
   }

   @Override
   public int getFrameCount() {
      return 1;
   }

   public HF pC(int var1) {
      if (var1 != 0) {
         return null;
      } else {
         LD var2 = (LD)this.A.pC(new wM(this));
         return var2 == null ? null : new HF((int)this.getId(), var2, this.A);
      }
   }

   @Override
   public List getFrames() {
      HF var1 = this.pC(0);
      if (var1 == null) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();
         var2.add(var1);
         return var2;
      }
   }

   @Override
   public String getLocation() {
      LD var1 = (LD)this.A.pC(new fD(this));
      return var1 == null ? null : this.A.pC(var1.getProgramCounter());
   }

   @Override
   public IRegisterData getRegisters() {
      return this.pC(false);
   }

   @Override
   public boolean setRegisters(IRegisterData var1) {
      return this.pC((LD)var1);
   }
}
