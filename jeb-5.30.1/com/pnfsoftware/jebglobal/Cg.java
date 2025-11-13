package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterData;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerThreadStatus;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractDebuggerThread;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import java.util.ArrayList;
import java.util.List;

public class Cg extends AbstractDebuggerThread {
   private static final ILogger q = GlobalLog.getLogger(Cg.class);
   private um RF;
   private Ht xK = null;

   public Cg(um var1, int var2, String var3) {
      super(var2, var3);
      this.RF = var1;
   }

   @Override
   public DebuggerThreadStatus getStatus() {
      return this.RF.q() ? DebuggerThreadStatus.PAUSED : DebuggerThreadStatus.UNKNOWN;
   }

   @Override
   public boolean resume() {
      q.error("Cannot perform operation");
      return false;
   }

   @Override
   public boolean suspend() {
      q.error("Cannot perform operation");
      return false;
   }

   public Ht q(boolean var1) {
      if (var1 || this.xK == null) {
         this.xK = this.RF.RF().Dw((int)this.getId());
      }

      return this.xK;
   }

   public boolean q(Ht var1) {
      boolean var2 = this.RF.RF().q((int)this.getId(), var1);
      this.xK = null;
      return var2;
   }

   @Override
   public boolean stepInto() {
      return Booleans.isTrue((Boolean)this.RF.q(new XC(this)));
   }

   @Override
   public boolean stepOver() {
      return Booleans.isTrue((Boolean)this.RF.q(new Oj(this)));
   }

   @Override
   public boolean stepOut() {
      q.error("Cannot perform operation");
      return false;
   }

   @Override
   public int getFrameCount() {
      return 1;
   }

   public he q(int var1) {
      if (var1 != 0) {
         return null;
      } else {
         Ht var2 = (Ht)this.RF.q(new Ff(this));
         return var2 == null ? null : new he((int)this.getId(), var2, this.RF);
      }
   }

   @Override
   public List getFrames() {
      he var1 = this.q(0);
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
      Ht var1 = (Ht)this.RF.q(new Yd(this));
      return var1 == null ? null : this.RF.q(var1.getProgramCounter());
   }

   @Override
   public IRegisterData getRegisters() {
      return this.q(false);
   }

   @Override
   public boolean setRegisters(IRegisterData var1) {
      return this.q((Ht)var1);
   }
}
