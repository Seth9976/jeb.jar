package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterData;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerThreadStatus;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractDebuggerThread;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.Fx;
import com.pnfsoftware.jebglobal.Jz;
import com.pnfsoftware.jebglobal.LC;
import com.pnfsoftware.jebglobal.NB;
import com.pnfsoftware.jebglobal.Ux;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class kY extends AbstractDebuggerThread {
   private static final ILogger q = GlobalLog.getLogger(kY.class);
   private CI RF;
   private Map xK = new HashMap();

   public kY(CI var1, long var2) {
      super(var2, null);
      this.RF = var1;
   }

   public CI q() {
      return this.RF;
   }

   @Override
   public String getName() {
      if (!this.RF.q(true, false)) {
         return null;
      } else {
         if (this.name == null) {
            this.name = this.RF.oW().Dw(this.getId());
         }

         return this.name;
      }
   }

   @Override
   public DebuggerThreadStatus getStatus() {
      return !this.RF.q(true, false) ? DebuggerThreadStatus.UNKNOWN : this.RF.oW().Uv(this.getId());
   }

   @Override
   public boolean suspend() {
      return this.RF.RF(this.id);
   }

   @Override
   public boolean resume() {
      return this.RF.xK(this.id);
   }

   @Override
   public boolean stepInto() {
      return this.RF(0);
   }

   @Override
   public boolean stepOver() {
      return this.RF(1);
   }

   @Override
   public boolean stepOut() {
      return this.RF(2);
   }

   public boolean RF() {
      return this.getStatus() == DebuggerThreadStatus.PAUSED;
   }

   private boolean RF(int var1) {
      if (!this.RF.q(true, false)) {
         return false;
      } else {
         LC var2 = this.RF.oW();
         if (var2 == null) {
            return false;
         } else if (!this.RF()) {
            return false;
         } else {
            List var3 = null;
            if (var1 == 0) {
               var3 = this.RF.Dw(this.id);
               if (var3 != null) {
                  var1 = 1;
               }
            }

            if (!var2.RF(this.id, var1)) {
               return false;
            } else {
               if (var3 != null) {
                  this.RF.q(var3);
               }

               return var2.xK(this.id);
            }
         }
      }
   }

   @Override
   public int getFrameCount() {
      if (!this.RF.q(true, false)) {
         return -1;
      } else if (!(this.RF.oW() instanceof Ux var2)) {
         return -1;
      } else if (!this.RF()) {
         return -1;
      } else {
         try {
            return var2.zz().io(this.id);
         } catch (Fx | IOException var4) {
            q.catching(var4);
            return -1;
         }
      }
   }

   public Xa q(int var1) {
      if (!this.RF.q(true, false)) {
         return null;
      } else if (!(this.RF.oW() instanceof Ux var3)) {
         return null;
      } else if (!this.RF()) {
         return null;
      } else {
         try {
            NB var4 = var3.zz().q(this.id, var1, 1);
            if (var4.q.length <= 0) {
               return null;
            } else {
               Jz var5 = var4.q[0];
               return this.q(var5);
            }
         } catch (Fx | IOException var6) {
            q.catching(var6);
            return null;
         }
      }
   }

   private Xa q(Jz var1) {
      Jz.eo var2 = var1.q();
      Xa var3 = (Xa)this.xK.get(var2);
      if (var3 == null) {
         String var4 = this.RF.q(var1.RF);
         var3 = new Xa(this, var1.q, var4);
         this.xK.put(var2, var3);
      }

      return var3;
   }

   @Override
   public List getFrames() {
      return this.xK(-1);
   }

   public Xa xK() {
      List var1 = this.xK(1);
      return (Xa)Lists.getFirst(var1);
   }

   private List xK(int var1) {
      if (!this.RF.q(true, false)) {
         return null;
      } else {
         LC var2 = this.RF.oW();
         if (var2 == null) {
            return null;
         } else if (!this.RF()) {
            return null;
         } else {
            List var3 = var2.q(this.id, var1);
            if (var3 == null) {
               return null;
            } else {
               ArrayList var4 = new ArrayList();

               for (Jz var6 : var3) {
                  var4.add(this.q(var6));
               }

               return var4;
            }
         }
      }
   }

   @Override
   public String getLocation() {
      Xa var1 = this.xK();
      return var1 == null ? null : var1.getAddress();
   }

   @Override
   public IRegisterData getRegisters() {
      return null;
   }

   @Override
   public boolean setRegisters(IRegisterData var1) {
      return false;
   }
}
